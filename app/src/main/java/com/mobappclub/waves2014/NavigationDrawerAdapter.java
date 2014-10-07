package com.mobappclub.waves2014;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Ayush on 07-10-14.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> items = null;

    public NavigationDrawerAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        Typeface tfc = Typeface.createFromAsset(context.getAssets(), "fonts/roboto-regular.ttf");

        if (position == 0){
            LayoutInflater mInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.home_menu_item, null);

            TextView txtEmail1 = (TextView)convertView.findViewById(R.id.txtEmailId);
            final TextView txtDevicename = (TextView)convertView.findViewById(R.id.txtDeviceName);
            txtEmail1.setTypeface(tfc);
            txtDevicename.setTypeface(tfc);

            return convertView;
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        final TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        //Set image icon
        //Set text for titles
        txtTitle.setText(items.get(position));
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/roboto-thin.ttf");
        txtTitle.setTypeface(tf);

        return convertView;
    }
}
