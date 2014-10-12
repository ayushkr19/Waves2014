package com.mobappclub.waves2014;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 12-10-14.
 */
public class SponsorsFragment extends Fragment {
    private ArrayList<SponsorObject> listData;

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
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<SponsorObject> sponsor_details = getListData();


        ListView listView = (ListView) getActivity().findViewById(android.R.id.list);
        listView.setEmptyView(getActivity().findViewById(android.R.id.empty));
        listView.setAdapter(new Sponsor_custom_list(getActivity(), sponsor_details));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DatabaseHandler db = new DatabaseHandler(getActivity());
                List<SponsorObject> sponsors = db.getAllSponsors();
                SponsorObject obj = sponsors.get(position);
                String url = obj.getLink();
                if (url!=null) {

                    if (url.startsWith("http://")||(url.startsWith("www"))|| (url.startsWith("https://"))) {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        try{
                            startActivity(i);
                        }catch (ActivityNotFoundException e)		{
                            Toast.makeText(getActivity(), "Invalid sponsor website link", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Malformed sponsor website URL", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public ArrayList<SponsorObject> getListData() {
        DatabaseHandler db = new DatabaseHandler(getActivity());

        ArrayList<SponsorObject> results = new ArrayList<SponsorObject>();

        List<SponsorObject> sponsors = db.getAllSponsors();

        for (SponsorObject sp : sponsors) {
            results.add(sp);
        }
        return results;
    }

}
