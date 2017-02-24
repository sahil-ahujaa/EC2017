package dev.elementsculmyca.ec2017.DatabaseHandlers;

/**
 * Created by hemba on 2/4/2017.
 */

public class DbUtils {
    private static final String COMMA_SEP=",";
    public static final String CREATE_TABLE="CREATE TABLE "+Schema.DbEntry.EVENT_LIST_TABLE_NAME+"( "
            +Schema.DbEntry.EVENT_ID_COLUMN_NAME+" TEXT PRIMARY KEY"+COMMA_SEP
            +Schema.DbEntry.EVENT_NAME_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_CLUB_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_CATEGORY_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_DESCRIPTION_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_RULES_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_VENUE_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_FEE_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_START_TIME_COLUMN_NAME+" TEXT"+COMMA_SEP
            +Schema.DbEntry.EVENT_END_TIME_COLUMN_NAME+" TEXT"+")";

}
