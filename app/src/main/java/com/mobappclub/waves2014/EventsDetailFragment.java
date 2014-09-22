package com.mobappclub.waves2014;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Ayush on 21-09-14.
 */
public class EventsDetailFragment extends Fragment {

    public int position_check = -1; // For loading currently selected event's
    // image in EventDetailFragment's ImageView
    public int category_check = -1; // For loading currently selected event's
    // image in EventDetailFragment's ImageView
    int share_check = 0;
    public int final_id_check = -1;

    public EventsDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events_detail,container,false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void updateEventView(int position, int category) {
        Toast.makeText(getActivity(),"Pos ; Cat : " + position + category,Toast.LENGTH_SHORT).show();
    }

    public int getEventDetailPosition() {
        return position_check;
    }

    public int getEventDetailCategory() {
        return category_check;
    }

    public int getEventDetailFinalID() {
        return final_id_check;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        position_check = -1; // Default values
        category_check = -1;
        final_id_check = -1;
        share_check = 0;
    }

}
