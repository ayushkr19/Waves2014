package com.mobappclub.waves2014;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.EventObject;
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


    // Events table name
    private static final String TABLE_EVENTS = "events";

    // Events Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DESC = "desc";
    private static final String KEY_IMAGE_URL_EVENTS = "imageurlevents";
    private static final String KEY_NO_Part = "no_of_participants";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_ROUTE_URL = "url";
    private static final String KEY_EM1_EVENT_NAME = "emoneeventname";
    private static final String KEY_EM1_EVENT_NO = "emoneeventno";
    private static final String KEY_EM2_EVENT_NAME = "emtwoeventname";
    private static final String KEY_EM2_EVENT_NO = "emtwoeventno";
    private static final String KEY_ID = "id";
    private static final String KEY_START_TIME = "starttimeofevent";
    private static final String KEY_END_TIME = "endtimeofevent";
    private static final String KEY_DAY_NO = "dayofevent";
    private static final String KEY_FOLLOWED="follow";
      
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
            // Create events table


        String CREATE_EVENTS_TABLE = "CREATE TABLE "
                + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_IMAGE_URL_EVENTS + " TEXT,"
                + KEY_NO_Part + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_ROUTE_URL + " TEXT,"
                +KEY_START_TIME + " TEXT,"
                + KEY_END_TIME + " TEXT,"
                + KEY_DAY_NO + " INTEGER,"
                + KEY_EM1_EVENT_NAME + " TEXT,"
                + KEY_EM1_EVENT_NO + " TEXT,"
                + KEY_EM2_EVENT_NAME + " TEXT,"
                + KEY_EM2_EVENT_NO + " TEXT,"
                + KEY_FOLLOWED + " INTEGER"
                + ")" ;
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Event
    void addEvent(EventsObject event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_START_TIME,event.getStart_time() );
        values.put(KEY_END_TIME, event.getEnd_time());
        values.put(KEY_DAY_NO, event.getDay_no());
        // Event time and date
        values.put(KEY_NAME, event.getName()); // Event Name
        values.put(KEY_TYPE, event.getType()); // Event Type
        values.put(KEY_DESC, event.getDesc()); // Event Description
        values.put(KEY_IMAGE_URL_EVENTS, event.getImageUrlEvent()); // Event Phone
        values.put(KEY_NO_Part, event.getNumberofParticipants());//No of participants for the event
        values.put(KEY_LOCATION, event.getLocation()); // Event Location
        values.put(KEY_ROUTE_URL, event.getReg_url());// Event registration url
        values.put(KEY_EM1_EVENT_NAME, event.getEm1_event_name());//Event em1 name
        values.put(KEY_EM1_EVENT_NO, event.getEm1_event_number());//Event em1 name
        values.put(KEY_EM2_EVENT_NAME, event.getEm2_event_name());//Event em1 name
        values.put(KEY_EM2_EVENT_NO, event.getEm2_event_number());//Event em1 name
        values.put(KEY_FOLLOWED, event.getFollowed());
        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection

    }

     // Getting All Events for a particular day
     public List<EventsObject> getAllEvents() {
         List<EventsObject> eventList = new ArrayList<EventsObject>();
         // Select All Query
         String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);

         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             do {
                 EventsObject event = new EventsObject();
                 event.setID(Integer.parseInt(cursor.getString(0)));
                 event.setName(cursor.getString(1));
                 event.setType(cursor.getString(2));
                 event.setDesc(cursor.getString(3));
                 event.setImageUrlEvent(cursor.getString(4));
                 event.setNumberofParticipants(cursor.getString(5));
                 event.setLocation(cursor.getString(6));
                 event.setReg_url(cursor.getString(7));
                 event.setStart_time(cursor.getString(8));
                 event.setEnd_time(cursor.getString(9));
                 event.setDay_no(Integer.parseInt(cursor.getString(10)));
                 event.setEm1_event_name(cursor.getString(11));
                 event.setEm1_event_number(cursor.getString(12));
                 event.setEm2_event_name(cursor.getString(13));
                 event.setEm2_event_number(cursor.getString(14));
                 event.setFollowed(Integer.parseInt(cursor.getString(15)));
                 // Adding event to list
                 eventList.add(event);
             } while (cursor.moveToNext());
         }
         db.close();
         cursor.close();
         // return event list
         return eventList;
     }

    public List<EventsObject> getDayEvents(int day_no) {
        List<EventsObject> eventList = new ArrayList<EventsObject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS+ " WHERE "+KEY_DAY_NO +" = " +day_no+ " ORDER BY "+KEY_START_TIME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EventsObject event = new EventsObject();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setName(cursor.getString(1));
                event.setType(cursor.getString(2));
                event.setDesc(cursor.getString(3));
                event.setImageUrlEvent(cursor.getString(4));
                event.setNumberofParticipants(cursor.getString(5));
                event.setLocation(cursor.getString(6));
                event.setReg_url(cursor.getString(7));
                event.setStart_time(cursor.getString(8));
                event.setEnd_time(cursor.getString(9));
                event.setDay_no(Integer.parseInt(cursor.getString(10)));
                event.setEm1_event_name(cursor.getString(11));
                event.setEm1_event_number(cursor.getString(12));
                event.setEm2_event_name(cursor.getString(13));
                event.setEm2_event_number(cursor.getString(14));
                event.setFollowed(Integer.parseInt(cursor.getString(15)));
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
    public int updateEvent(EventsObject event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName());
        values.put(KEY_TYPE, event.getType());
        values.put(KEY_DESC, event.getDesc());
        values.put(KEY_IMAGE_URL_EVENTS, event.getImageUrlEvent());
        values.put(KEY_NO_Part, event.getNumberofParticipants());
        values.put(KEY_LOCATION, event.getLocation());
        values.put(KEY_ROUTE_URL, event.getReg_url());
        values.put(KEY_START_TIME,event.getStart_time() );
        values.put(KEY_END_TIME, event.getEnd_time());
        values.put(KEY_DAY_NO, event.getDay_no());
        values.put(KEY_EM1_EVENT_NAME, event.getEm1_event_name());
        values.put(KEY_EM1_EVENT_NO, event.getEm1_event_number());
        values.put(KEY_EM2_EVENT_NAME, event.getEm2_event_name());
        values.put(KEY_EM2_EVENT_NO, event.getEm2_event_number());
        values.put(KEY_FOLLOWED, event.getFollowed());
        // updating row
        int update = db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
        db.close();
        return update;
    }

    public void deleteEvent(EventsObject event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
        db.close();
    }

   
    // Getting event Count
    public int getEventsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EVENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        // return count

        cursor.close();
        db.close();
        return count;
    }

    

    

   
}
