package com.mobappclub.waves2014;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Akshay on 24-09-2014.
 */

public class SponsorsActivity extends Activity implements AdapterView.OnItemClickListener {
    String [] s={"bournbon","askme","time","engage","bombay trooper"};
      @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsors_fragment);
        ListView l= (ListView) findViewById(R.id.list);
       ArrayAdapter la=new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                s);
        l.setAdapter(la);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast t=Toast.makeText(getApplicationContext(),"position:"+i,Toast.LENGTH_SHORT);



    }
}
