package com.mobappclub.waves2014;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 03-10-2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
       // Database Name
    private static final String DATABASE_NAME = "MainDatabases";

    // Schedule table name
    private static final String TABLE_SCHEDULE = "Schedule";

    
    // Schedule Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_START_TIME = "starttimeofevent";
    private static final String KEY_END_TIME = "endtimeofevent";
    private static final String KEY_DAY_NO = "dayofevent";
    private static final String KEY_EVENT_NAME = "eventname";
      
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
            // Create events table

            String CREATE_SCHEDULE_TABLE = "CREATE TABLE "
                    + TABLE_SCHEDULE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_START_TIME + " TEXT,"
                    + KEY_END_TIME + " TEXT,"
                    + KEY_DAY_NO + " INTEGER,"
                    + KEY_EVENT_NAME + " TEXT"
                    + ")" ;
            db.execSQL(CREATE_SCHEDULE_TABLE);

            InitializeSchedule p=new InitializeSchedule(this);
             p.initialize();
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
      
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Event
    void addEvent(ScheduleObject event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_START_TIME, event.getStart_time());
        values.put(KEY_END_TIME, event.getEnd_time());
        values.put(KEY_DAY_NO, event.getDay_no());
        // Event time and date
        values.put(KEY_EVENT_NAME, event.getEvent_name());//Event em1 name
        // Inserting Row
        db.insert(TABLE_SCHEDULE, null, values);
        db.close(); // Closing database connection

    }

     // Getting All Events for a particular day
    public List<ScheduleObject> getAllEvents(int day) {
        List<ScheduleObject> eventList = new ArrayList<ScheduleObject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE +" WHERE "+KEY_DAY_NO+"="+day+ " SORT BY "+KEY_START_TIME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ScheduleObject event = new ScheduleObject();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setStart_time(cursor.getString(1));
                event.setEnd_time(cursor.getString(2));
                event.setDay_no(cursor.getInt(3));
                event.setEvent_name(cursor.getString(4));
                // Adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        // return event list
        return eventList;
    }

 

    // Updating single Event
    public int updateEvent(ScheduleObject event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_START_TIME, event.getStart_time());
        values.put(KEY_END_TIME, event.getEnd_time());
        values.put(KEY_EVENT_NAME, event.getEvent_name());
        values.put(KEY_DAY_NO, event.getDay_no());
        // updating row
        int update = db.update(TABLE_SCHEDULE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
        db.close();
        return update;
    }


    // Deleting single event
    public void deleteEvent(ScheduleObject event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHEDULE, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
        db.close();
    }

   
    // Getting event Count
    public int getEventsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SCHEDULE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        // return count

        cursor.close();
        db.close();
        return count;
    }

    

    

   
}
