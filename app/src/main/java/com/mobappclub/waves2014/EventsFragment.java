package com.mobappclub.waves2014;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayush on 19-09-14.
 */
public class EventsFragment extends Fragment {

    public EventsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events_gridview, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.grid);
        // Initialize the adapter with all the Menu_Items. Set the adapter on the {@link GridView}.
        gridView.setAdapter(new Menu_ItemAdapter(inflater,createAllMenu_Items()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent i = new Intent(getActivity(), EventsActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent f = new Intent(getActivity(), EventsActivity.class);
                        startActivity(f);
                        break;
                    case 2:
                        Intent g = new Intent(getActivity(), EventsActivity.class);
                        startActivity(g);
                        break;
                    case 3:
                        Intent h = new Intent(getActivity(), EventsActivity.class);
                        startActivity(h);
                        break;
                    case 4:
                        Intent j = new Intent(getActivity(), EventsActivity.class);
                        startActivity(j);
                        break;
                    case 5:
                        Intent k = new Intent(getActivity(), EventsActivity.class);
                        startActivity(k);
                        break;
                    case 6:
                        Intent l = new Intent(getActivity(), EventsActivity.class);
                        startActivity(l);
                    default:
                        break;
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Generate the list of all Menu_Items.
     * @return The list of Menu_Items.
     */
    public List<Menu_Item> createAllMenu_Items() {
        List<Menu_Item> Menu_Items = new ArrayList<Menu_Item>();
        Menu_Items.add(new Menu_Item("Dance","",R.drawable.bits));
        Menu_Items.add(new Menu_Item("Big 3","Info about big3",R.drawable.bits));
        Menu_Items.add(new Menu_Item("Drama","",R.drawable.ic_launcher));
        Menu_Items.add(new Menu_Item("Music","",R.drawable.ic_launcher));
        Menu_Items.add(new Menu_Item("Fine Arts","",R.drawable.ic_launcher));
        Menu_Items.add(new Menu_Item("Literacy","",R.drawable.ic_launcher));
        Menu_Items.add(new Menu_Item("Quiz","",R.drawable.ic_launcher));
        Menu_Items.add(new Menu_Item("Miscellaneous","",R.drawable.ic_launcher));
        Menu_Items.add(new Menu_Item("Moot Court","",R.drawable.ic_launcher));
        return Menu_Items;
    }


    /**
     * Adapter for grid of Menu_Items.
     */
    private static class Menu_ItemAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<Menu_Item> mAllMenu_Items;

        /**
         * Constructs a new {@link Menu_ItemAdapter}.
         *
         * @param inflater to create new views
         * @param allMenu_Items for list of all Menu_Items to be displayed
         */
        public Menu_ItemAdapter(LayoutInflater inflater, List<Menu_Item> allMenu_Items) {
            if (allMenu_Items == null) {
                throw new IllegalStateException("Can't have null list of Menu_Items");
            }
            mAllMenu_Items = allMenu_Items;
            mInflater = inflater;
        }

        @Override
        public int getCount() {
            return mAllMenu_Items.size();
        }

        @Override
        public Menu_Item getItem(int position) {
            return mAllMenu_Items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = convertView;
            if (result == null) {
                result = mInflater.inflate(R.layout.eventsfragment_grid_item, parent, false);
            }

            // Try to get view cache or create a new one if needed
            ViewCache viewCache = (ViewCache) result.getTag();
            if (viewCache == null) {
                viewCache = new ViewCache(result);
                result.setTag(viewCache);
            }

            // Fetch item
            Menu_Item Menu_Item = getItem(position);

            // Bind the data
            viewCache.mTitleView.setText(Menu_Item.mTitle);
            viewCache.mSubtitleView.setText(Menu_Item.mSubtitle);
            viewCache.mImageView.setImageResource(Menu_Item.mImage);

            return result;
        }
    }

    /**
     * Cache of views in the grid item view to make recycling of views quicker. This avoids
     * additional {@link View#findViewById(int)} calls after the {@link ViewCache} is first
     * created for a view. See
     * {@link Menu_ItemAdapter#getView(int position, View convertView, ViewGroup parent)}.
     */
    private static class ViewCache {

        /** View that displays the title of the Menu_Item */
        private final TextView mTitleView;

        /** View that displays the subtitle of the Menu_Item */
        private final TextView mSubtitleView;

        /** View that displays the image associated with the Menu_Item */
        private final ImageView mImageView;

        /**
         * Constructs a new {@link ViewCache}.
         *
         * @param view which contains children views that should be cached.
         */
        private ViewCache(View view) {
            mTitleView = (TextView) view.findViewById(R.id.title);
            mSubtitleView = (TextView) view.findViewById(R.id.subtitle);
            mImageView = (ImageView) view.findViewById(R.id.image);
        }
    }

    /**
     * Class of Menu Items
     */
    private static class Menu_Item {

        /** Title of the Menu_Item. */
        private final String mTitle;

        /** Description of the Menu_tem. */
        private final String mSubtitle;

        /** Image for the Menu_Item. */
        private final int mImage;

        /**
         * Constructs a new Menu_Item.
         *
         * @param titleString is the title
         * @param subtitleString is the description
         * @param imageFilePath is the file path from the application's drawable folder for
         *                           the image associated with this Menu_Item
         */
        private Menu_Item(String titleString, String subtitleString, int imageFilePath) {
            mTitle = titleString;
            mSubtitle = subtitleString;
            mImage = imageFilePath;
        }
    }
}
