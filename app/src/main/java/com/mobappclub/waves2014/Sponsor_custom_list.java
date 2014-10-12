package com.mobappclub.waves2014;

import android.widget.BaseAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Sponsor_custom_list extends BaseAdapter {
    private ArrayList listData;
    private Context context;
    private LayoutInflater layoutInflater;


    public Sponsor_custom_list(Context context, ArrayList listData) {

        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listData.size();

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        //final ViewHolder holder;


        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.sponsor_list_items,
                    null);
            /*holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.txt);
			holder.imageView = (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(holder);*/

        } else {

			/*holder = (ViewHolder) convertView.getTag();*/


        }

        TextView title = (TextView) convertView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);

        SponsorObject sponsor = (SponsorObject) listData.get(position);

        title.setText(sponsor.getNameofSponsor());


        if (imageView != null) {
            if (imageView.getDrawable() == null) {
                Picasso pic = Picasso.with(context);
                pic.load(sponsor.getImgUrl()).error(R.drawable.error).into(imageView);
                //Log.d("Details", sponsor.getNameofSponsor() + " " + sponsor.getImgUrl());

            }
        }

        return convertView;

    }

	/*static class ViewHolder {

		TextView title;
		ImageView imageView;

	}*/

}
