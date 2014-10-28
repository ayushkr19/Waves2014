package com.mobappclub.waves2014;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class FetchDataFirstRun extends Activity {
	Context context = this;
	JSONParser jParser = new JSONParser();
	TextView tv;
	
	// Url for updates
	private static String url_updates = "http://www.bits-waves.org/wavesapp/updates.php";
	//Url for event locations
 	private static String url_event_locations = "http://www.bits-waves.org/wavesapp/events.php";
 	//Url for workshop locations
 	private static String url_workshop_locations = "http://www.bits-waves.org/wavesapp/workshops.php";
	// Url for sponsors

	private static String url_sponsors = "http://www.bits-waves.org/wavesapp/sponsors.php";

	boolean ActivityRunning = true;
	// products JSONArray
	JSONArray updates = null;
	JSONArray locations = null;
	JSONArray sponsors = null;
	JSONArray event_locations = null ;
 	JSONArray workshop_locations = null ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fetch_data);

		tv = (TextView) findViewById(R.id.fetch_tv);

		new LoadDatabase().execute();

	}

	private void setActivityRunning(Boolean bool) {
		ActivityRunning = bool;
	}
	
	private Boolean getActivityRunning(){
		return ActivityRunning;
	}
	
	
	

	@Override
	protected void onPause() {
		super.onPause();
		
		setActivityRunning(false);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		setActivityRunning(true);
	}




	class LoadDatabase extends AsyncTask<String, String, String> {
		DatabaseHandler db = new DatabaseHandler(context);

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (getActivityRunning()) {
				Intent intent = new Intent(FetchDataFirstRun.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}

		}

		@Override
		protected String doInBackground(String... args) {
			//Log.i("FetchDataFirstRun", "Fetching Data for first run.");

			publishProgress("25");

			if (db.getUpdatesCount() == 0)
				fetchUpdates();

			publishProgress("50");

			if (db.getSponsorCount() == 0)
				fetchSponsors();

			publishProgress("75");
			fetchEvents();
			


			publishProgress("100");

			return null;

		}

		@Override
		protected void onProgressUpdate(String... values) {
			int progress = Integer.parseInt(values[0]);
			switch (progress) {
			case 25:
				tv.setText("Fetching Updates...");
				break;
			case 50:
				tv.setText("Fetching Sponsors...");
				break;
			case 75:
				tv.setText("Fetching Event Locations...");
				break;
			case 85:
				tv.setText("Fetching Workshop Locations...");
			case 100:
				tv.setText("Done");
				break;
			}
			super.onProgressUpdate(values);

		}

		//Fetch event locations from server
		private void fetchEvents() {
			// TODO Auto-generated method stub
			//Log.i("FetchDataFirstRun", "Fetching event locations");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			event_locations = jParser.makeHttpRequestArray(url_event_locations, "POST", params);
			
			if (event_locations!=null) {
				DatabaseHandler db = new DatabaseHandler(
						getApplicationContext());
				//Log.d("Check", "Reached fetch locations");
				try {

					// looping through All Products
					for (int i = 0; i < event_locations.length(); i++) {

						JSONObject c = event_locations.getJSONObject(i);

						// Storing each json item in variable
						String event_id = c.getString("id");
						String location = c.getString("venue");

						
						if ((event_id!=null)&&(location!=null)) {
							List<EventsObject> event_list = db.getAllEvents();
							int ev = Integer.parseInt(event_id);
							try {
								for (EventsObject e : event_list) {
									if (e.getID() == ev) {
										e.setLocation(location);
										db.updateEvent(e);

										//Log.d("Event Location", location + e.getName());

									}

								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
		}
		



		// Method for fetching sponsors list from database
		private void fetchSponsors() {

			//Log.i("FetchDataFirstRun", "Fetching Sponsors");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			sponsors = jParser.makeHttpRequestArray(url_sponsors, "POST",
					params);
			if (sponsors!=null) {
				try {

					// looping through All Products
					for (int i = 0; i < sponsors.length(); i++) {

						JSONObject c = sponsors.getJSONObject(i);

						// Storing each json item in variable
						String sponsor_name = c.getString("name");
						String image_url = c.getString("image");
						String link = c.getString("website");
						if (sponsor_name!=null) {
							try {

								db.addSponsor(new SponsorObject(sponsor_name,
										image_url, link));
								/*Log.i("FetchDataFirstRun", "Added sponsor : "
										+ sponsor_name + " " + image_url + " "
										+ link);
*/
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		}

		// Method for fetching updates from database
		private void fetchUpdates() {
			//Log.i("FetchDataFirstRun", "Fetching Updates");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
	 updates = jParser.makeHttpRequestArray(url_updates,
					"POST", params);

			if (updates!=null) {
				try {

					// looping through All Products
					for (int i = 0; i < updates.length(); i++) {
						JSONObject c = updates.getJSONObject(i);

						// Storing each json item in variable
						String category_id = c.getString("for_ev_ws");

						if( (Integer.parseInt(category_id) == 1) || (Integer.parseInt(category_id) == 2)){

						
							String head = c.getString("update_head");
							String body = c.getString("update_body");
							String timestamp = c.getString("timestamp");
							String event_id = c.getString("ev_ws");
							
							head = GcmIntentService.unescape(head);
							body = GcmIntentService.unescape(body);

							Long time = Long.parseLong(timestamp);		        	
		        	Date date1 = new Date(time * 1000L - 45000000) ; 
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // the format of your date
		        	String fmdate = sdf1.format(date1);
		       
					
					db.addUpdate(new UpdateObject( category_id , event_id, fmdate, head, body));
							
						}

						else if (Integer.parseInt(category_id) == 0) {
							String head = c.getString("update_head");
							String body = c.getString("update_body");
							String timestamp = c.getString("timestamp");
							String event_id = "0";
							
							head = GcmIntentService.unescape(head);
							body = GcmIntentService.unescape(body);

							long time = Long.parseLong(timestamp);
			        			        	       	
			        	Date date1 = new Date(time * 1000L - 45000000) ; 
			        	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // the format of your date
			        	String fmdate = sdf1.format(date1);
			        	
			        	
			        	db.addUpdate(new UpdateObject( category_id , event_id , fmdate, head, body));
						}
						
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		}

		
		
	}

}
