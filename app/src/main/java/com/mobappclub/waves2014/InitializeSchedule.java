package com.mobappclub.waves2014;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Akshay on 05-10-2014.
 */
public class InitializeSchedule {
    DatabaseHandler db;
    ScheduleObject ob1,ob2,ob3,ob4,ob5;

public InitializeSchedule(DatabaseHandler db)  {
       this.db=db;
    }
public void initialize()
{
    ob1=new ScheduleObject("15:00","17:00","Event1",0);
    ob2=new ScheduleObject("16:00","18:00","Event2",1);
    ob3=new ScheduleObject("13:00","15:00","Event3",2);
    ob4=new ScheduleObject("19:00","20:00","Event4",3);
    ob5=new ScheduleObject("13:00","18:00","Event5",0);

    db.addEvent(ob1);
    db.addEvent(ob2);
    db.addEvent(ob3);
    db.addEvent(ob4);
    db.addEvent(ob5);
 }

}
