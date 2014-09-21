package com.mobappclub.waves2014;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Ayush on 21-09-14.
 */
public class EventsListFragment extends ListFragment {

    OnItemSelectedListener mCallback;
    ArrayAdapter<String> adapter;
    String category_title = "Events";

    // Container Activity must implement this interface
    public interface OnItemSelectedListener {
        public void onEventSelected(int position, int category);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] items = {"Test 1", "Test 2", "Test 3", "test 4"};
        setListAdapter(new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,items));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        int category = getActivity().getIntent().getIntExtra("Category", 1);
        mCallback.onEventSelected(position, category);
    }
}
