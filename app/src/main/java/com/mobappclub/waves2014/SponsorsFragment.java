package com.mobappclub.waves2014;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Ayush on 12-10-14.
 */
public class SponsorsFragment extends Fragment {
    public SponsorsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);

        ListView listView = (ListView) getActivity().findViewById(android.R.id.list);
        listView.setEmptyView(getActivity().findViewById(R.id.sponsorsEmpty));
        return rootView;
    }
}
