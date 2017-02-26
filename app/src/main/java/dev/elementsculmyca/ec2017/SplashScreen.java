package dev.elementsculmyca.ec2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import dev.elementsculmyca.ec2017.DatabaseHandlers.DbHelper;
import dev.elementsculmyca.ec2017.DatabaseHandlers.EventDetails;

public class SplashScreen extends AppCompatActivity {
    static final String TAG="eventList";
    Button saveBtn,getBtn,nextBtn;
    final DbHelper dbHelper=new DbHelper(SplashScreen.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen); final DbHelper dbHelper=new DbHelper(SplashScreen.this);
        saveBtn=(Button)findViewById(R.id.save_list_btn);
        getBtn=(Button)findViewById(R.id.get_list_btn);
        nextBtn=(Button)findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SplashScreen.this,EventCategoryActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left_slide,R.anim.activity_close_translate);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  saveList();
            }
        });
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> categories=dbHelper.retriveCategory();
                int s=categories.size();
                for(int i=0;i<s;i++){
                    Log.d("Category",categories.get(i));
                    ArrayList<EventDetails> eventList=dbHelper.retrieveEventsByCategory(categories.get(i));
                    int l=eventList.size();
                    for (int k=0;k<l;k++) {
                        Log.d(TAG, eventList.get(k).getEventName());
                    }
                }
            }
        });
    }
}
