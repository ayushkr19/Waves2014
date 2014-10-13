package com.mobappclub.waves2014;

/**
 * Created by Akshay on 13-10-2014.
 */
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
        import android.graphics.Color;
        import android.os.Build;
        import android.os.Bundle;

        import android.text.SpannableString;
        import android.text.style.ForegroundColorSpan;
        import android.view.View;
        import android.view.ViewTreeObserver.OnGlobalLayoutListener;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
        import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
        import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.LatLngBounds;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity
        implements OnMarkerClickListener, OnInfoWindowClickListener, OnMarkerDragListener{

    // Latlong objects of markers for adding to map.
    private static final LatLng CENTRALLAWN = new LatLng(15.39163,73.879207);
    private static final LatLng AUDITORIUM  = new LatLng(15.392983,73.880476);
    private static final LatLng LT1  = new LatLng(15.392406,73.88109);
    private static final LatLng LT2 = new LatLng(15.392711,73.881055);
    private static final LatLng LT3  = new LatLng(15.393529,73.880208);
    private static final LatLng LT4  = new LatLng(15.393611,73.879862);
    private static final LatLng CC  = new LatLng(15.39163,73.880994);
    private static final LatLng AWIng  = new LatLng(15.393066,73.879765);
    private static final LatLng CWIng  = new LatLng(15.392303,73.88057);
    private static final LatLng Library  = new LatLng(15.391457,73.880436);
    private static final LatLng LibraryLawn  = new LatLng(15.391749,73.880146);
    private static final LatLng Wkshp = new LatLng(15.393684,73.879038);
    private static final LatLng Sac = new LatLng(15.392073,73.875466);

    //Inatantiate Google map
    private GoogleMap mMap;

    //Markers to add to map
    private Marker centlaw ;
    private Marker audi ;
    private Marker lt1 ;
    private Marker lt2 ;
    private Marker lt3 ;
    private Marker lt4 ;
    private Marker cc ;
    private Marker awing ;
    private Marker cwing ;
    private Marker lib ;
    private Marker liblawn ;
    private Marker wkshp ;
    private Marker sac;



    /** Demonstrates customizing the info window and/or its contents. */
    class CustomInfoWindowAdapter implements InfoWindowAdapter {
        // These a both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        private final View mWindow;
        private final View mContents ;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
            mContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {

            render(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {

            render(marker, mContents);
            return null;
        }

        private void render(Marker marker, View view) {
            int badge;
            badge = 0;

            ((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);

            String title = marker.getTitle();
            TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                // Spannable string allows us to edit the formatting of the text.
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.BLUE), 0, titleText.length(), 0);
                titleUi.setText(titleText);
            } else {
                titleUi.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
            if (snippet != null && snippet.length() > 12) {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
                snippetUi.setText(snippetText);
            } else {
                snippetUi.setText("");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedinstancestate) {
        super.onCreate(savedinstancestate);
        setContentView(R.layout.map_layout);


        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }




    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }

    }





    private void setUpMap() {
        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Add lots of markers to the map.
        addMarkersToMap();

        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);


        // Pan to see all markers in view.
        // Cannot zoom to bounds until the map has a size.
        final View mapView = getFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(AUDITORIUM)
                            .include(CENTRALLAWN)
                            .include(AWIng)
                            .include(CC)
                            .include(CENTRALLAWN)
                            .include(CWIng)
                            .include(LT1)
                            .include(LT2)
                            .include(LT3)
                            .include(LT4)
                            .include(Library)
                            .include(LibraryLawn)
                            .include(Wkshp)
                            .build();

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(AUDITORIUM) // Sets the center of the map to
                            .zoom(18)                   // Sets the zoom
                            .tilt(45)    // Sets the tilt of the camera to 30 degrees
                            .build();    // Creates a CameraPosition from the builder

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            cameraPosition));
                }
            });
        }


    }

    //Method for adding markers to map.
    private void addMarkersToMap() {
        centlaw = mMap.addMarker(new MarkerOptions()
                .position(CENTRALLAWN)
                .title("Central Lawns"));

        audi = mMap.addMarker(new MarkerOptions()
                .position(AUDITORIUM)
                .title("Auditorium"));

        lt1 = mMap.addMarker(new MarkerOptions()
                        .position(LT1)
                        .title("Lecture Theatre 1")
        );

        lt2 = mMap.addMarker(new MarkerOptions()
                        .position(LT2)
                        .title("Lecture Theatre 2")
        );

        lt3 = mMap.addMarker(new MarkerOptions()
                        .position(LT3)
                        .title("Lecture Theatre 3")
        );

        lt4 = mMap.addMarker(new MarkerOptions()
                        .position(LT4)
                        .title("Lecture Theatre 4")
        );

        cc = mMap.addMarker(new MarkerOptions()
                        .position(CC)
                        .title("Computer Centre")
        );

        sac = mMap.addMarker(new MarkerOptions()
                        .position(Sac)
                        .title("Student Activity Centre")
        );

        awing = mMap.addMarker(new MarkerOptions()
                        .position(AWIng)
                        .title("A-Wing")
        );

        cwing = mMap.addMarker(new MarkerOptions()
                        .position(CWIng)
                        .title("C-Wing")
        );

        lib = mMap.addMarker(new MarkerOptions()
                        .position(Library)
                        .title("Library")
        );

        liblawn = mMap.addMarker(new MarkerOptions()
                        .position(LibraryLawn)
                        .title("Library Lawns")
        );

        wkshp = mMap.addMarker(new MarkerOptions()
                        .position(Wkshp)
                        .title("Workshop")
        );

    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this,"Map not ready", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /** Called when the Clear button is clicked. */
    public void onClearMap(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.clear();
    }

    /** Called when the Reset button is clicked. */
    public void onResetMap(View view) {
        if (!checkReady()) {
            return;
        }
        // Clear the map because we don't want duplicates of the markers.
        mMap.clear();
        addMarkersToMap();
    }

    @Override
    public void onMarkerDrag(Marker arg0) {

    }

    @Override
    public void onMarkerDragEnd(Marker arg0) {

    }

    @Override
    public void onMarkerDragStart(Marker arg0) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String loc = marker.getTitle().toString();
        int id = 0;
        if(loc.equals("A-Wing")){
            id = 1 ;
        }

        else if(loc.equals("C-Wing")){
            id = 2 ;
        }
        Intent i = new Intent(MapActivity.this , MapLocClickList.class);
        i.putExtra("Location", loc);
        i.putExtra("Id", id);
        startActivity(i);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }




}
