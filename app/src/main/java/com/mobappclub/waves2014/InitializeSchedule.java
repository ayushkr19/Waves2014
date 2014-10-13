package com.mobappclub.waves2014;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Akshay on 05-10-2014.
 */
public class InitializeSchedule {
    DatabaseHandler db;
    EventsObject ob1,ob2,ob3,ob4,ob5;

public InitializeSchedule(DatabaseHandler db)  {
       this.db=db;
    }
public void initialize()
{

    ob1 = new EventsObject(1,"Test1","Dance","Description","http://www.dddd.com","500","A-506","http://www.dddd.com","16:00","23:00",0,"asa","342","asa","111",0);
    ob2 = new EventsObject(2,"Test2","Drama","Description","http://www.dddd.com","500","A-506","http://www.dddd.com","17:00","23:00",1,"asa","342","asa","111",0);
    ob3 = new EventsObject(3,"Test3","Literary","Description","http://www.dddd.com","500","A-506","http://www.dddd.com","14:00","15:00",2,"asa","342","asa","111",0);
    ob4 = new EventsObject(4,"Test4","Music","Description","http://www.dddd.com","500","A-506","http://www.dddd.com","15:00","18:00",3,"asa","342","asa","111",0);
    ob5 = new EventsObject(5,"Test5","Misc","Description","http://www.dddd.com","500","A-506","http://www.dddd.com","13:00","17:00",3,"asa","342","asa","111",0);
    db.addEvent(ob1);
    db.addEvent(ob2);
    db.addEvent(ob3);
    db.addEvent(ob4);
    db.addEvent(ob5);


 }


}
