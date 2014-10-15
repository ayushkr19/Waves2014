package com.mobappclub.waves2014;

/**
 * Created by Ayush on 15-10-14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UpdateCustomList extends BaseAdapter {

    Context context;
    String name[];
    String timestamp[];
    String head[];
    String body[];

    private LayoutInflater layoutInflater;


    public UpdateCustomList(Context context, String[] name, String[] timestamp, String[] head, String[] body) {
        super();
        this.context = context;
        this.name = name;
        this.timestamp = timestamp;
        this.head = head;
        this.body = body;

        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView == null) {
            //Add the name of the custom listview
            convertView = layoutInflater.inflate(R.layout.single_list_updates, null);
            holder = new ViewHolder();

            //Instantiate four textviews
            holder.txtViewName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.txtViewTime = (TextView) convertView.findViewById(R.id.tv_timestamp);
            holder.txtViewHead = (TextView) convertView.findViewById(R.id.tv_head);
            holder.txtViewBody = (TextView) convertView.findViewById(R.id.tv_body);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        holder.txtViewName.setText(name[position]);
        holder.txtViewTime.setText(timestamp[position]);
        holder.txtViewHead.setText(head[position]);
        holder.txtViewBody.setText(body[position]);

        return convertView;
    }

    private class ViewHolder {
        TextView txtViewName;
        TextView txtViewTime;
        TextView txtViewHead;
        TextView txtViewBody;
    }

}
