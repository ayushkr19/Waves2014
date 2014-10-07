package com.mobappclub.waves2014;

/**
 * Created by Akshay on 03-10-2014.
 */
public class ScheduleObject {
    int id;
    String event_name;
    private String start_time;
    private String end_time;
    private  int day_no;

    // Empty constructor
    public ScheduleObject() {

        // TODO Auto-generated constructor stub
    }

    // constructor
    /***
     *
     * @param id
     * @param start_time
     * @param end_time
     * @param day_no
     * @param event_name
     */
    public ScheduleObject(int id, String start_time, String end_time , String event_name ,int day_no) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.day_no = day_no;
        this.event_name = event_name;
    }
    public ScheduleObject(String start_time, String end_time , String event_name ,int day_no) {
       this.start_time = start_time;
        this.end_time = end_time;
        this.day_no = day_no;
        this.event_name = event_name;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

     // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // getting name

    // Get time and date of event


    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getDay_no() {
        return day_no;
    }

    public void setDay_no(int day_no) {
        this.day_no = day_no;
    }
}
