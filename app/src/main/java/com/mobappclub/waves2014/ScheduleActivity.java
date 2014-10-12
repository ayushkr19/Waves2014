package com.mobappclub.waves2014;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ScheduleActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_activity);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        ActionBar.Tab tab[] = new ActionBar.Tab[4];
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.

            tab[i] = actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this);
            actionBar.addTab(tab[i]);
        }
        int currentDay;
        Calendar rightnow = Calendar.getInstance();
        if (rightnow.get(Calendar.DAY_OF_MONTH) == Constants.DAY_0 && rightnow.get(Calendar.MONTH) == Constants.MONTH_0) {
            currentDay = 0;
        } else if(rightnow.get(Calendar.DAY_OF_MONTH) == Constants.DAY_1 && rightnow.get(Calendar.MONTH) == Constants.MONTH_1){
            currentDay = 1;
        }else if(rightnow.get(Calendar.DAY_OF_MONTH) == Constants.DAY_2 && rightnow.get(Calendar.MONTH) == Constants.MONTH_2){
            currentDay = 2;
        }else{
            currentDay = 3;
        }
        int hours = rightnow.get(Calendar.HOUR_OF_DAY);
        int minutes = rightnow.get(Calendar.MINUTE);
        Toast.makeText(this, "Hrs : " + hours + " min: " + minutes, Toast.LENGTH_SHORT).show();
        tab[currentDay].select();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return new PlaceholderFragment().newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "DAY 0";
                case 1:
                    return "DAY 1";
                case 2:
                    return "DAY 2";
                case 3:
                    return "DAY 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String DAY_NUMBER = "day_number";
        List<EventsObject> list1;
        MyAdapter adapt1;
        protected DatabaseHandler db;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int dayNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(DAY_NUMBER, dayNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_schedule_activity2, container, false);
            Bundle args = getArguments();
            int dayNumber = args.getInt(DAY_NUMBER);
            db = new DatabaseHandler(getActivity());
            list1 = db.getDayEvents(dayNumber);
            adapt1 = new MyAdapter(getActivity(), R.layout.schedule_list_inner_view, list1);
            ListView listTask1 = (ListView) rootView.findViewById(R.id.listView1);
            listTask1.setAdapter(adapt1);
            return rootView;

        }
    }

    public static class MyAdapter extends ArrayAdapter<EventsObject> {
        Context context;
        List<EventsObject> scheduleList = new ArrayList<EventsObject>();
        int layoutResourceId;

        public MyAdapter(Context context, int layoutResourceId,
                         List<EventsObject> objects) {
            super(context, layoutResourceId, objects);
            this.layoutResourceId = layoutResourceId;
            this.scheduleList = objects;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView startTime = null;
            TextView endTime = null;
            TextView eventName = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.schedule_list_inner_view,
                        parent, false);
                startTime = (TextView) convertView.findViewById(R.id.startTime_tv);
                endTime = (TextView) convertView.findViewById(R.id.endTime_tv);
                eventName = (TextView) convertView.findViewById(R.id.eventName_tv);

                convertView.setTag(startTime);
                convertView.setTag(endTime);
                convertView.setTag(eventName);

            } else {
                startTime = (TextView) convertView.getTag();
                endTime = (TextView) convertView.getTag();
                eventName = (TextView) convertView.getTag();
            }
            EventsObject current = scheduleList.get(position);
            startTime.setText(current.getStart_time());
            startTime.setTag(current);

            endTime.setText(current.getEnd_time());
            endTime.setTag(current);

            eventName.setText(current.getName());
            eventName.setTag(current);

            Calendar rightnow = Calendar.getInstance();
            int hours = rightnow.get(Calendar.HOUR_OF_DAY);
            int minutes = rightnow.get(Calendar.MINUTE);
            String time = String.valueOf(hours) +":" + String.valueOf(minutes);

            if(time.compareTo(current.getStart_time()) > 0 && time.compareTo(current.getEnd_time())< 0){
                convertView.findViewById(R.id.ongoing).setVisibility(View.VISIBLE);
            }else {
                convertView.findViewById(R.id.ongoing).setVisibility(View.INVISIBLE);
            }

            return convertView;

        }

    }

}
