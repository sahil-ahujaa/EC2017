package dev.elementsculmyca.ec2017.DatabaseHandlers;

import android.provider.BaseColumns;

/**
 * Created by hemba on 2/4/2017.
 */

public class Schema {
    public Schema() {
    }
    public static class DbEntry implements BaseColumns {
        public static final String EVENT_LIST_TABLE_NAME="ECevents";
        public static final String EVENT_ID_COLUMN_NAME="EventId";
        public static final String EVENT_NAME_COLUMN_NAME="eventName";
        public static final String EVENT_CLUB_COLUMN_NAME="club";
        public static final String EVENT_CATEGORY_COLUMN_NAME="category";
        public static final String EVENT_DESCRIPTION_COLUMN_NAME="description";
        public static final String EVENT_RULES_COLUMN_NAME="rules";
        public static final String EVENT_VENUE_COLUMN_NAME="venue";
        public static final String EVENT_FEE_COLUMN_NAME="fee";
        public static final String EVENT_START_TIME_COLUMN_NAME="startTime";
        public static final String EVENT_END_TIME_COLUMN_NAME="endTime";

    }
}
