package com.mobappclub.waves2014;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.View;

/**
 * Created by Ayush on 21-09-14.
 */
public class EventsActivity extends Activity implements EventsListFragment.OnItemSelectedListener,SlidingPaneLayout.PanelSlideListener{

    SlidingPaneLayout mSlidingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanelayout);

        mSlidingLayout.setPanelSlideListener(this);
        mSlidingLayout.openPane();
        mSlidingLayout.setSliderFadeColor(getResources().getColor(
                R.color.sliding_pane_content_fade));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onEventSelected(int position, int category) {
        EventsDetailFragment eventsDetailFragment = (EventsDetailFragment) getFragmentManager().findFragmentById(R.id.fragment_events_details);
        if(eventsDetailFragment != null){
            mSlidingLayout.closePane();
            eventsDetailFragment.updateEventView(position,category);
        }
    }

    @Override
    public void onBackPressed() {
        // Handling back button press
        // If SlidingPane is open,then close it.
        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanelayout);
        if (!mSlidingLayout.isOpen()) {
            mSlidingLayout.openPane();
        } else {
            super.onBackPressed();
        }

    }

    public boolean isSlidingPaneOpen() {
        mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanelayout);
        if (mSlidingLayout != null) {

            if (mSlidingLayout.isOpen()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // To save the position and category of EventsDetailFragment during
        // Configuration change
        EventsDetailFragment detailFrag = (EventsDetailFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_events_details);
        if (detailFrag != null) {
            int position = detailFrag.getEventDetailPosition();
            int category = detailFrag.getEventDetailCategory();

            outState.putInt("position", position);
            outState.putInt("category", category);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // To restore position and category of EventsDetailFragment after
        // Configuration change.
        EventsDetailFragment detailFrag = (EventsDetailFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_events_details);
        if (detailFrag != null) {

            int position = savedInstanceState.getInt("position");
            int category = savedInstanceState.getInt("category");

            if ((position != -1) && (category != -1)) {
                detailFrag.updateEventView(position, category);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onPanelSlide(View view, float v) {

    }

    @Override
    public void onPanelOpened(View view) {

    }

    @Override
    public void onPanelClosed(View view) {

    }


}
