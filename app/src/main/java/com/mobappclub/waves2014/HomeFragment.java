package com.mobappclub.waves2014;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshay on 10-10-2014.
 */
public class HomeFragment extends Fragment {
    public HomeFragment() {
        super();
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

        View rootView = inflater.inflate(R.layout.fragment_home_new, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_home);

        Menu_ItemAdapter menu_itemAdapter = new Menu_ItemAdapter(inflater, createAllMenu_Items(),getActivity());

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(menu_itemAdapter);
        swingBottomInAnimationAdapter.setAbsListView(gridView);
        /*AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(menu_itemAdapter);
        alphaInAnimationAdapter.setAbsListView(gridView);
        gridView.setAdapter(alphaInAnimationAdapter);*/
        gridView.setAdapter(swingBottomInAnimationAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        DrawerLayout drawerLayout = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);

                        if(drawerLayout != null)
                            drawerLayout.openDrawer(Gravity.LEFT);
                        break;
                    case 1:
                        Intent f = new Intent(getActivity(), ScheduleActivity.class);
                        startActivity(f);
                        break;
                    case 2:
                        Intent g = new Intent(getActivity(), MapActivity.class);
                        startActivity(g);
                        break;

                    default:
                        break;
                }
            }
        });
        return rootView;
    }

    public List<Menu_Item> createAllMenu_Items() {
        List<Menu_Item> Menu_Items = new ArrayList<Menu_Item>();
        Menu_Items.add(new Menu_Item("Home",R.drawable.schedule , R.color.md_yellow_800));
        Menu_Items.add(new Menu_Item("Schedule",R.drawable.schedule, R.color.md_blue_100));
        Menu_Items.add(new Menu_Item("Map",R.drawable.map2 , R.color.md_red_800));
        /*Menu_Items.add(new Menu_Item("Schedule", "", R.color.md_green_800));
        Menu_Items.add(new Menu_Item("Map", "", R.color.md_grey_800));
        Menu_Items.add(new Menu_Item("Schedule", "", R.color.md_blue_800));
        Menu_Items.add(new Menu_Item("Map", "", R.color.md_red_800));*/
        return Menu_Items;
    }

    /**
     * Adapter for grid of Menu_Items.
     */
    private static class Menu_ItemAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<Menu_Item> mAllMenu_Items;
        private Typeface typeface;

        /**
         * Constructs a new {@link Menu_ItemAdapter}.
         *
         * @param inflater      to create new views
         * @param allMenu_Items for list of all Menu_Items to be displayed
         */
        public Menu_ItemAdapter(LayoutInflater inflater, List<Menu_Item> allMenu_Items, Context context) {
            if (allMenu_Items == null) {
                throw new IllegalStateException("Can't have null list of Menu_Items");
            }
            mAllMenu_Items = allMenu_Items;
            mInflater = inflater;
            typeface = Typeface.createFromAsset(context.getAssets(),"fonts/roboto-thin.ttf");
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

            if (position == 0) {
                result = mInflater.inflate(R.layout.homefragment_grid_item, parent, false);
                return result;
            } else {
                result = mInflater.inflate(R.layout.homefragment_schedule_grid_item, parent, false);

                // Fetch item
                Menu_Item menu_Item = getItem(position);
                RelativeLayout relativeLayout = (RelativeLayout)result.findViewById(R.id.homefragment_rl);
                relativeLayout.setBackgroundColor(menu_Item.mImage);
                TextView textView = (TextView)result.findViewById(R.id.homefragment_option_tv);
                textView.setText(menu_Item.mTitle);
                textView.setTypeface(typeface);
                ImageView imageView = (ImageView)result.findViewById(R.id.homefragment_icon_iv);
                imageView.setImageResource(menu_Item.mIcon);
            }


           /* // Try to get view cache or create a new one if needed
            ViewCache viewCache = (ViewCache) result.getTag();
            if (viewCache == null) {
                viewCache = new ViewCache(result);
                result.setTag(viewCache);
            }

            // Fetch item
            Menu_Item Menu_Item = getItem(position);

            // Bind the data
           *//* viewCache.mTitleView.setText(Menu_Item.mTitle);
            viewCache.mSubtitleView.setText(Menu_Item.mSubtitle);
            viewCache.mImageView.setImageResource(Menu_Item.mImage);*/

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

        /**
         * View that displays the title of the Menu_Item
         */
//        private final TextView mTitleView;
//
//        /**
//         * View that displays the subtitle of the Menu_Item
//         */
//        private final TextView mSubtitleView;
//
//        /**
//         * View that displays the image associated with the Menu_Item
//         */
//        private final ImageView mImageView;

        /**
         * Constructs a new {@link ViewCache}.
         *
         * @param view which contains children views that should be cached.
         */
        private ViewCache(View view) {
           /* mTitleView = (TextView) view.findViewById(R.id.title);
            mSubtitleView = (TextView) view.findViewById(R.id.subtitle);
            mImageView = (ImageView) view.findViewById(R.id.image);*/
        }
    }

    /**
     * Class of Menu Items
     */
    private static class Menu_Item {

        /**
         * Title of the Menu_Item.
         */
        private final String mTitle;

        /**
         * Description of the Menu_tem.
         */
        private final int mIcon;

        /**
         * Image for the Menu_Item.
         */
        private final int mImage;

        /**
         * Constructs a new Menu_Item.
         *
         * @param titleString    is the title
         * @param icon is the icon
         * @param imageFilePath  is the file path from the application's drawable folder for
         *                       the image associated with this Menu_Item
         */
        private Menu_Item(String titleString, int icon, int imageFilePath) {
            mTitle = titleString;
            mIcon = icon;
            mImage = imageFilePath;
        }
    }
}




