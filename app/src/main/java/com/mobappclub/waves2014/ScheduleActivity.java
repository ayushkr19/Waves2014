package com.mobappclub.waves2014;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Akshay on 03-10-2014.
 */
public class ScheduleActivity extends Activity
{
    List<ScheduleObject> list1,list2,list3,list4;
    MyAdapter adapt1,adapt2,adapt3,adapt4;
    protected DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);

        TabHost host = (TabHost) findViewById(R.id.tabhost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("DAY 0");
        host.addTab(spec);

        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("DAY 1");
        host.addTab(spec);

        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab3);
        spec.setIndicator("DAY 2");
        host.addTab(spec);


        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab4);
        spec.setIndicator("DAY 3");
        host.addTab(spec);
        host.setCurrentTab(1);

        db = new DatabaseHandler(this);
        list1 = db.getAllEvents(0);
        adapt1 = new MyAdapter(this, R.layout.schedule_list_inner_view, list1);
        ListView listTask1 = (ListView) findViewById(R.id.listView1);
        listTask1.setAdapter(adapt1);

        list2 = db.getAllEvents(1);
        adapt2 = new MyAdapter(this, R.layout.schedule_list_inner_view, list2);
        ListView listTask2 = (ListView) findViewById(R.id.listView2);
        listTask2.setAdapter(adapt2);


        list3 = db.getAllEvents(2);
        adapt3 = new MyAdapter(this, R.layout.schedule_list_inner_view, list3);
        ListView listTask3 = (ListView) findViewById(R.id.listView3);
        listTask3.setAdapter(adapt3);


        list4 = db.getAllEvents(3);
        adapt4 = new MyAdapter(this, R.layout.schedule_list_inner_view, list4);
        ListView listTask4 = (ListView) findViewById(R.id.listView4);
        listTask4.setAdapter(adapt4);




    }

    private class MyAdapter extends ArrayAdapter<ScheduleObject> {
        Context context;
        List<ScheduleObject> scheduleList = new ArrayList<ScheduleObject>();
        int layoutResourceId;

        public MyAdapter(Context context, int layoutResourceId,
                         List<ScheduleObject> objects) {
            super(context, layoutResourceId, objects);
            this.layoutResourceId = layoutResourceId;
            this.scheduleList = objects;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView chk = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.schedule_list_inner_view,
                        parent, false);
                chk = (TextView) convertView.findViewById(R.id.textView1);
                convertView.setTag(chk);

            } else {
                chk = (TextView) convertView.getTag();
            }
            ScheduleObject current=scheduleList.get(position);
            chk.setText(current.getStart_time()+" "+current.getEnd_time()+" "+current.getEvent_name());
            chk.setTag(current);
            return convertView;

        }

    }
}



