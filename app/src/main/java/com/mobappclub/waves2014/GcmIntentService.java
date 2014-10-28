package com.mobappclub.waves2014;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

@SuppressLint("SimpleDateFormat")
public class GcmIntentService extends IntentService {
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	SharedPreferences prefs;

	// Google project id
	static final String SENDER_ID = "599449460569";

	JSONObject json;

	JSONArray jarray = null;

	public GcmIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {

			// has effect of unparcelling Bundle
			/*
			 * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */

			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Deleted messages on server: "
						+ extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {
				// This loop represents the service doing some work.
				//Log.i(TAG, "Received a GCM message! ");

				sendNotification(extras);

				//Log.i(TAG, "Received: " + extras.toString());
			}
		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.
	
	public static String unescape(String text){
		
		char[] arr = text.toCharArray();
		for(int i = 0 ; i < arr.length ; i ++ ){
			if(arr[i] == '\\'){
				
				if(arr[i + 1] == '\\'){
					arr[i] = ' ' ; 
					arr[i + 1] = '\\' ; 
					i++;
					
				}
				
				else if(arr[i+1] == '"' ){

					arr[i] = ' ' ;
					arr[i+1] = '\"';
						
				}else if(arr[i+1] == '\'' ){

					arr[i] = ' ' ;
					arr[i+1] = '\'';
						
				}
				
			}
		}
		
		text = new String(arr) ; 
		return text;
		
	}
	
	
	
	
	private void sendNotification(String msg) {
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("from_update", true);	
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		PendingIntent contentIntent = PendingIntent.getActivity(
				getApplicationContext(), 0,
				intent, 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Quark")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg).setAutoCancel(true);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}

	// Handle Gcm messages stuff here

	private void sendNotification(Bundle bundle) {

		String feature = bundle.getString("feature");

		//Log.i("GCMIntentService - feature", feature);

		if (feature.equalsIgnoreCase("update")) {

			// Post notification of received message.
			prefs = getSharedPreferences("notifications_prefs", MODE_PRIVATE);
			boolean notification_check = prefs
					.getBoolean("notifications", true);
			/*Log.i("GCMIntentService", "User wants Notifications? : "
					+ notification_check);*/
			if (notification_check)
				sendNotification("Update received.");

			String type = bundle.getString("for_ev_ws");

			DatabaseHandler db = new DatabaseHandler(this);

			if ((Integer.parseInt(type) == 1) || (Integer.parseInt(type) == 2)) {

				String head = bundle.getString("update_head");

				head = unescape(head);
				String body = bundle.getString("update_body");

				body = unescape(body);
				String timestamp = bundle.getString("timestamp");
				String event_id = bundle.getString("ev_ws");

				long time = Long.parseLong(timestamp);

				Date date1 = new Date(time * 1000L - 45000000);
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // the
																					// format
																					// of
																					// your
																					// date
				String fmdate = sdf1.format(date1);

				db.addUpdate(new UpdateObject(type, event_id, fmdate, head,
						body));

			} else if (Integer.parseInt(type) == 0) {
				String head = bundle.getString("update_head");
				head = unescape(head);
				String body = bundle.getString("update_body");
				body = unescape(body);
				String timestamp = bundle.getString("timestamp");
				String event_id = bundle.getString("ev_ws");

				long time = Long.parseLong(timestamp);

				Date date1 = new Date(time * 1000L - 45000000);
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // the
																		// format
																		// of
																		// your
																		// date
				String fmdate = sdf1.format(date1);

				db.addUpdate(new UpdateObject(type, event_id, fmdate, head,
						body));

			}

		}

		// Fetch Locations for events
		else if (feature.equalsIgnoreCase("eventVenue")) {

			String event_id = bundle.getString("id");
			String location = bundle.getString("venue");

			if ((event_id!=null)&&(location!=null)) {
				DatabaseHandler db = new DatabaseHandler(this);
				List<EventsObject> events = db.getAllEvents();
				for (EventsObject event : events) {

					if (event.getID() == Integer.parseInt(event_id)) {

						event.setLocation(location);
						db.updateEvent(event);

					}

				}
				db.close();
			}

		}

		else if (feature.equalsIgnoreCase("addSponsor")) {

			// Get name and image url from intent
			String sponsor_name = bundle.getString("name");
			String url = bundle.getString("image");
			String link = bundle.getString("website");

			// Save in database
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			if (sponsor_name != null)
				db.addSponsor(new SponsorObject(sponsor_name, url, link));
			db.close();

		}


		else if (feature.equalsIgnoreCase("removeSponsor")) {

			// Get name of sponsor to delete
			String sponsor_name = bundle.getString("name");
			// Delete Sponsor
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			if (sponsor_name != null)
				db.removeSponsor(sponsor_name);
			db.close();

		}
		
		else if(feature.equalsIgnoreCase("removeUpdate")){
			// Get name of sponsor to delete
			String update_head = bundle.getString("update_head");
			String update_body = bundle.getString("update_body");
			String timestamp = bundle.getString("timestamp");
			
			update_head = unescape(update_head);
			update_body = unescape(update_body);
			
			long time = Long.parseLong(timestamp);

			Date date1 = new Date(time * 1000L - 45000000);
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // the
																	// format
																	// of
																	// your
																	// date
			String fmdate = sdf1.format(date1);
			// Delete Sponsor
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			if ((update_head != null)&&(update_body != null))
				db.removeUpdate(update_head, update_body,fmdate);
			db.close();

		}

	}

}