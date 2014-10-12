package com.mobappclub.waves2014;

/**
 * Created by Akshay on 03-10-2014.
 */
public class EventsObject {


    private String start_time;
    private String end_time;
    private  int day_no;
    // private variables
        int id;
        String type;
        String name;
        String desc;
        String img_url_events;
        String no_part;
        String location;
        String route_url;
        String em1_event_name;
        String em1_event_number;
        String em2_event_name;
        String em2_event_number;
        int followed;
        // Empty constructor
        public EventsObject() {

            // TODO Auto-generated constructor stub
        }

        // constructor
        /***
         *
         * @param id
         * @param name
         * @param type
         * @param desc
         * @param img_url_events
         * @param no_part
         * @param location
         * @param route_url
         * @param start_time
         * @param end_time
         * @param day_no
         * @param em1_event_name
         * @param em1_event_number
         * @param em2_event_name
         * @param em2_event_number
         *  @param followed
         */
        public EventsObject(int id, String name, String type, String desc,
                           String img_url_events, String no_part, String location,
                           String route_url, String start_time,String end_time,int day_no, String em1_event_name,
                           String em1_event_number, String em2_event_name,
                           String em2_event_number,int followed) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.desc = desc;
            this.img_url_events = img_url_events;
            this.no_part = no_part;
            this.route_url = route_url;
            this.location = location;
           this.start_time=start_time;
            this.end_time=end_time;
            this.day_no=day_no;
            this.em1_event_name = em1_event_name;
            this.em1_event_number = em1_event_number;
            this.em2_event_name = em2_event_name;
            this.em2_event_number = em2_event_number;
            this.followed=followed;

        }


        public String getEm1_event_name() {
            return em1_event_name;
        }

        public void setEm1_event_name(String em1_event_name) {
            this.em1_event_name = em1_event_name;
        }

        public String getEm1_event_number() {
            return em1_event_number;
        }

        public void setEm1_event_number(String em1_event_number) {
            this.em1_event_number = em1_event_number;
        }

        public String getEm2_event_name() {
            return em2_event_name;
        }

        public void setEm2_event_name(String em2_event_name) {
            this.em2_event_name = em2_event_name;
        }

        public String getEm2_event_number() {
            return em2_event_number;
        }

        public void setEm2_event_number(String em2_event_number) {
            this.em2_event_number = em2_event_number;
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
        public String getName() {
            return this.name;
        }

        // setting name
        public void setDesc(String desc) {
            this.desc = desc;
        }

        // getting Description
        public String getDesc() {
            return this.desc;
        }

        // setting name
        public void setName(String name) {
            this.name = name;
        }

        // getting phone number
        public String getImageUrlEvent() {
            return this.img_url_events;
        }

        // setting phone number
        public void setImageUrlEvent(String img_url_events) {
            this.img_url_events = img_url_events;
        }

        // getting number of participants
        public String getNumberofParticipants() {
            return this.no_part;
        }

        // setting number of participants
        public void setNumberofParticipants(String no_part) {
            this.no_part = no_part;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        // Get registration url
        public String getReg_url() {
            return this.route_url;
        }

        // Set registration url
        public void setReg_url(String route_url) {
            this.route_url = route_url;
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

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }
}

