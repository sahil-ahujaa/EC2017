package dev.elementsculmyca.ec2017.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by hemba on 2/4/2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="ElementsCulmyca.db";
    private static final String TAG="DbHelper";
    private Context context;
    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbUtils.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addEntryToDb(String eventId,String eventName,String club,String category,
                             String description,String rules,String venue,
                             String fee,String startTime,String endTime){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Schema.DbEntry.EVENT_ID_COLUMN_NAME,eventId);
        values.put(Schema.DbEntry.EVENT_NAME_COLUMN_NAME,eventName);
        values.put(Schema.DbEntry.EVENT_CLUB_COLUMN_NAME,club);
        values.put(Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME,category);
        values.put(Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME,description);
        values.put(Schema.DbEntry.EVENT_RULES_COLUMN_NAME,rules);
        values.put(Schema.DbEntry.EVENT_VENUE_COLUMN_NAME,venue);
        values.put(Schema.DbEntry.EVENT_FEE_COLUMN_NAME,fee);
        values.put(Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME,startTime);
        values.put(Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME,endTime);
        db.insert(Schema.DbEntry.EVENT_LIST_TABLE_NAME,null,values);

    }

    public ArrayList<EventDetails> retrieveEventListFromDb(){
        SQLiteDatabase db=getReadableDatabase();
        String[] projection={
                Schema.DbEntry.EVENT_ID_COLUMN_NAME,Schema.DbEntry.EVENT_NAME_COLUMN_NAME
                ,Schema.DbEntry.EVENT_CLUB_COLUMN_NAME,Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME
                ,Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME,Schema.DbEntry.EVENT_RULES_COLUMN_NAME
                ,Schema.DbEntry.EVENT_VENUE_COLUMN_NAME,Schema.DbEntry.EVENT_FEE_COLUMN_NAME
                ,Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME,Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME};
        Cursor readCursor=db.query(Schema.DbEntry.EVENT_LIST_TABLE_NAME,projection
                ,null,null,null,null,null,null);
        readCursor.moveToFirst();
        int totalRows=readCursor.getCount();
        ArrayList<EventDetails> eventList=new ArrayList<>();
        while (totalRows > 0) {
            totalRows--;
            String eventId=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_ID_COLUMN_NAME));
            String eventName=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_NAME_COLUMN_NAME));
            String club=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_CLUB_COLUMN_NAME));
            String category=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME));
            String description=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME));
            String rules=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_RULES_COLUMN_NAME));
            String venue=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_VENUE_COLUMN_NAME));
            String fee=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_FEE_COLUMN_NAME));
            String startTime=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME));
            String endTime=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME));
            eventList.add(new EventDetails(eventId,eventName,club,category,description,rules,venue,fee
                    ,startTime,endTime));
            readCursor.moveToNext();
        }
        readCursor.close();
        return eventList;
    }
    public ArrayList<String> retriveCategory(){
        SQLiteDatabase db=getReadableDatabase();
        String[] projection={Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME};
        Cursor readCursor=db.query(Schema.DbEntry.EVENT_LIST_TABLE_NAME,projection,null,null,Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME,null,null);
        readCursor.moveToFirst();
        int totalRows=readCursor.getCount();
        ArrayList<String> categoryList=new ArrayList<>();
        while (totalRows > 0) {
            totalRows--;
            String category=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME));
            categoryList.add(category);
            readCursor.moveToNext();
        }
        readCursor.close();
        return categoryList;
    }

    public ArrayList<EventDetails> retrieveEventsByCategory(String categoryName){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<EventDetails> eventList=new ArrayList<>();
        String[] projection={ Schema.DbEntry.EVENT_ID_COLUMN_NAME,Schema.DbEntry.EVENT_NAME_COLUMN_NAME
                ,Schema.DbEntry.EVENT_CLUB_COLUMN_NAME,Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME
                ,Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME,Schema.DbEntry.EVENT_RULES_COLUMN_NAME
                ,Schema.DbEntry.EVENT_VENUE_COLUMN_NAME,Schema.DbEntry.EVENT_FEE_COLUMN_NAME
                ,Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME,Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME};
        Cursor readCursor=db.query(Schema.DbEntry.EVENT_LIST_TABLE_NAME,projection,Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME+" = ?",new String[]{categoryName} ,null,null,null);
        readCursor.moveToFirst();
        int totalEvents=readCursor.getCount();
        while(totalEvents>0){
            totalEvents--;
            String eventId=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_ID_COLUMN_NAME));
            String eventName=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_NAME_COLUMN_NAME));
            String club=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_CLUB_COLUMN_NAME));
            String category=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME));
            String description=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME));
            String rules=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_RULES_COLUMN_NAME));
            String venue=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_VENUE_COLUMN_NAME));
            String fee=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_FEE_COLUMN_NAME));
            String startTime=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME));
            String endTime=readCursor.getString(readCursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME));
            eventList.add(new EventDetails(eventId,eventName,club,category,description,rules,venue,fee
                    ,startTime,endTime));
            readCursor.moveToNext();

        }
        readCursor.close();
        return eventList;
    }
    public String getEventNameById(String eventId){
        SQLiteDatabase db=getReadableDatabase();
        String eventName;
        String[] projection={Schema.DbEntry.EVENT_NAME_COLUMN_NAME};
        Cursor cursor=db.query(Schema.DbEntry.EVENT_LIST_TABLE_NAME,projection,Schema.DbEntry.EVENT_ID_COLUMN_NAME+" =?",new String[]{eventId},null,null,null);
        cursor.moveToFirst();
        eventName=cursor.getString(cursor.getColumnIndexOrThrow(Schema.DbEntry.EVENT_NAME_COLUMN_NAME));
        cursor.close();
        return eventName;
    }
    public void updateEvents(String eventName,String category,String description,String rules,String venue,String fee,String startTime,String endTime){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME,category);
        cv.put(Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME,description);
        cv.put(Schema.DbEntry.EVENT_RULES_COLUMN_NAME,rules);
        cv.put(Schema.DbEntry.EVENT_VENUE_COLUMN_NAME,venue);
        cv.put(Schema.DbEntry.EVENT_FEE_COLUMN_NAME,fee);
        cv.put(Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME,startTime);
        cv.put(Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME,endTime);
        db.update(Schema.DbEntry.EVENT_LIST_TABLE_NAME,cv,Schema.DbEntry.EVENT_NAME_COLUMN_NAME+"=?",new String[]{eventName});

    }
    public int getCount(){
        int i=0;
        SQLiteDatabase db=getReadableDatabase();
        String[] projection={Schema.DbEntry.EVENT_ID_COLUMN_NAME};
        Cursor cursor=db.query(Schema.DbEntry.EVENT_LIST_TABLE_NAME,projection,null,null,null,null,null);
        i=cursor.getCount();
        cursor.close();
        return i;
    }
}