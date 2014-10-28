package com.mobappclub.waves2014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class Splash_Activity extends Activity {
	private static int SPLASH_TIME_OUT = 4000;
	ConnectionDetector cd;
	boolean check = true;

	int i = 0;

	/**
	 * Substitute you own sender ID here. This is the project number you got
	 * from the API Console, as described in "Getting Started."
	 */
	static String SENDER_ID = "877747638048";

	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	/**
	 * Default lifespan (7 days) of a reservation until it is considered
	 * expired.
	 */
	//public static final long REGISTRATION_EXPIRY_TIME_MS = 1000 * 3600 * 24 * 7;

	/**
	 * Tag used on log messages.
	 */
	static final String TAG = "SplashActivity";

	GoogleCloudMessaging gcm;
	AtomicInteger msgId = new AtomicInteger();
	SharedPreferences prefs;
	Context context;
	private static final String OPENED_KEY = "OPENED_KEY";
	
	int QueryLength;
	int status;
	String response = null;

	public static String regid;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());

		cd = new ConnectionDetector(getApplicationContext());

		context = getApplicationContext();

		// GCM registration.
		gcm = GoogleCloudMessaging.getInstance(this);
		regid = getRegistrationId(context);
    Log.i("SplashActivity",regid);
		/*if (db.getEventsCount() == 0) {

			String init_ill = getResources().getString(R.string.ev_illum);
			String bitsmun_bits = getResources().getString(R.string.ev_bitsmun);
			String prog_app = getResources().getString(R.string.ev_applif);
			String prog_binar = getResources().getString(R.string.ev_binary);
			String special_paper = getResources().getString(R.string.ev_paper);
			String special_matka = getResources().getString(R.string.ev_matka);
			String special_school = getResources()
					.getString(R.string.ev_school);
			String special_open = getResources()
					.getString(R.string.ev_openshow);
			String design_mortar = getResources().getString(R.string.ev_mortar);
			String design_trail = getResources().getString(R.string.ev_trail);
			String design_burnout = getResources().getString(
					R.string.ev_burnout);
			String electrify_mat = getResources().getString(
					R.string.ev_matmania);
			String electrify_emb = getResources().getString(R.string.ev_emb);
			String electrify_ard = getResources()
					.getString(R.string.ev_arduino);
			String electrify_digi = getResources().getString(
					R.string.ev_digilogica);
			String elixir_quark = getResources().getString(
					R.string.ev_national_quiz);
			String elixir_numbers = getResources().getString(
					R.string.ev_numbers);
			String elixir_amazing = getResources().getString(
					R.string.ev_amazing_race);
			String elixir_mad = getResources().getString(R.string.ev_mad);
			String corporate_g20 = getResources().getString(R.string.ev_g20);
			String corporate_quest = getResources()
					.getString(R.string.ev_quest);
			String corporate_dalal = getResources()
					.getString(R.string.ev_dalal);
			String corporate_blue = getResources().getString(
					R.string.ev_bluechip);
			String corporate_intel = getResources().getString(
					R.string.ev_intellect);
			String robo_vir = getResources().getString(R.string.ev_virtua);
			String robo_proj = getResources().getString(
					R.string.ev_project_recon);
			String robo_savi = getResources().getString(R.string.ev_saviors);
			String robo_wrong = getResources().getString(R.string.ev_wrong);
			String robo_cod = getResources().getString(R.string.ev_call);
			String robo_armor = getResources().getString(R.string.ev_armor);
			String startup = getResources().getString(R.string.ev_startup);

			// Adding test events
			//Log.i("SplashActivity", "Adding events & workshops");
			db.addEvent(new EventObject(1, "Illumination", "Initiatives",
					init_ill, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/illumination/", "", "", "", "", ""));
			db.addEvent(new EventObject(2, "BITSMUN 2014", "BITSMUN",
					bitsmun_bits, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/bitsmun-2014/", "", "", "", "", ""));
			db.addEvent(new EventObject(3, "Applification",
					"Programmer's Inc.", prog_app, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/applification/", "", "Kunal Bajpai", "+91 94231 74875",
					"", ""));
			db.addEvent(new EventObject(4, "Binary Pirates",
					"Programmer's Inc.", prog_binar, "url", "",
					"TBA", "http://www.bits-quark.org/2014/main_site/portfolio/binary-pirates/", "", "Abhishek Kumar",
					"+91 96370 17844", "Vishal Kodia", "+91 96370 18366"));
			db.addEvent(new EventObject(5, "Paper Presentation", "Specials",
					special_paper, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/paper-presentation/", "", "Saransh Varshneya,CS\nsaranshvarshneya@gmail.com\n\nAbhishek Patria,CHE\npatriaabhishek@gmail.com\n", "Vamsi Kaja,EEE\nvamsi.kaja41@gmail.com", "Tanay Choudhary,ME\ntanayc9@gmail.com\n", "Raj Kunkolienkar,PHY\nrajkunkolienkar@gmail.com"));
			db.addEvent(new EventObject(6, "MATKA", "Specials", special_matka,
					"url", "", "TBA", "http://www.bits-quark.org/2014/main_site/portfolio/matka/",
					"", "Sachin Paryani", "+91 75075 52059 \nsachin.paryani@gmail.com", "", ""));
			db.addEvent(new EventObject(7, "School Bag", "Specials",
					special_school, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/school-bag/", "", "PraharshPaiRaikar", "+91 94032 71188 \npraharsh.raikar@gmail.com", "Nidhi Angle", "+91 99224 35066 \nnidhiangle94@igmail.com"));
			db.addEvent(new EventObject(8, "Open Showcase", "Specials",
					special_open, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/open-showcase/", "", "Nidhi Angle", "+91 99224 35066 \nnidhiangle94@gmail.com", "Charajit Nayyar", "+91 98335 99153 \ninfinityslayer8@gmail.com"));
			db.addEvent(new EventObject(9, "Mortar Kombat", "Design and Build",
					design_mortar, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/mortar-kombat-get-ready-to-go-ballistic/", "", "Sanchit Aggarwal", "+91 91587 32687 \nsanchitaggarwal89@gmail.com", "Amit Gaiki", "+91 73037 36274 \nxamit94@gmail.com"));
			db.addEvent(new EventObject(10, "TrailBlazers", "Design and Build",
					design_trail, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/trailblazers-sky-is-the-limit/", "", "Harshit Sinha", "+91 95614 46115 \nharshit2704sinha@gmail.com ", "Prodyumna Mohanta", "+91 80072 31353 \npmohanta55@gmail.com"));
			db.addEvent(new EventObject(11, "Burnout", "Design and Build",
					design_burnout, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/burnout-the-nitro-loop/", "", "Gaurav Baglekar", "+91 95614 46337 \ngaurav.baglekar@gmail.com", "Rohit Rawal", "+91 86000 75185 \nrohitrawal94@gmail.com"));
			db.addEvent(new EventObject(12, "MatMania", "Electrify",
					electrify_mat, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/matmania/", "", "Samyukta Ramnath", "+91 95611 30432 \nsamyuktaramnath@gmail.com", "Srinath Rajagopalan", "+91 83902 33450 \nSrinath132@gmail.com"));
			db.addEvent(new EventObject(13, "EmbItion", "Electrify",
					electrify_emb, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/embition-problem-statement/", "", "Maitreya Naik", "+91 96372 79501 \nmaitreyanaik@gmail.com", "Pallavi Nema", "+91 88889 64785 \nnpallavi138@gmail.com"));
			db.addEvent(new EventObject(14, "Arduino Open", "Electrify",
					electrify_ard, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/arduino-open/", "", "Shreesh Katyayan", "+91 96372 77934 \nshreesh.blogwebs@gmail.com", "Puneet Madaan", "puneet.madaan94@gmail.com"));
			db.addEvent(new EventObject(15, "Digilogica", "Electrify",
					electrify_digi, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/digilogica/", "", "Dipali Agarwal", "+91 77099 59320 \nagarwal.dipali94@gmail.com", "Aastha Khandelwal", "+91 97678 92553 \naastha053@gmail.com"));
			db.addEvent(new EventObject(16, "Quark National Quiz", "Elixir",
					elixir_quark, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/181/", "", "Ananth Kachroo", "+91 83088 35598 \nananthkachroo@gmail.com", "", ""));
			db.addEvent(new EventObject(17, "NUMB3RS", "Elixir",
					elixir_numbers, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/numb3rs/", "", "Jaya Tiwari", "+91 75881 06988", "Rajat Mishra", "+91 9503371139 \nrajatm2112@gmail.com"));
			db.addEvent(new EventObject(18, "The Amazing Race", "Elixir",
					elixir_amazing, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/the-amazing-race/", "", "Ishan Dave", "+91 95033 70833 \nishan310@gmail.com", "Amritesh Singh", "+91 88063 36443 \nbits.amritesh@gmail.com"));
			db.addEvent(new EventObject(19, "M.A.D", "Elixir", elixir_mad,
					"url", "", "TBA", "http://www.bits-quark.org/2014/main_site/portfolio/m-a-d/",
					"", "Nishant Sarawagi", "+91 77690 35309 \nsarawaginis@gmail.com", "", ""));
			db.addEvent(new EventObject(20, "G20 Conference", "Corporate",
					corporate_g20, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/g20-conference/", "", "", "", "", ""));
			db.addEvent(new EventObject(21, "Quest", "Corporate",
					corporate_quest, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/quest/", "", "Abeer Om", "+91 97635 55285", "Malhar Patil", "+91 88068 26888"));
			db.addEvent(new EventObject(22, "Dalal Street Revolution",
					"Corporate", corporate_dalal, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/dalal-street-revolution/", "", "", "", "", ""));
			db.addEvent(new EventObject(23, "Bluechip Beatdown", "Corporate",
					corporate_blue, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/bluechip-beatdown/", "", "Aashima Garg", "+91 95611 33083", "Kaustav Mohanty", "+91 81491 43116"));
			db.addEvent(new EventObject(24, "Intellect", "Corporate",
					corporate_intel, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/corporate/", "", "Aastha Aggarwal", "+91 77699 26106", "", ""));
			db.addEvent(new EventObject(25, "Virtua Wars",
					"Roboficial", robo_vir, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/virtua-wars-concept-robotics/", "", "Eejya Singh", "+91 94051 38222", "Rohit Ranavat", "+91 76661 96367"));
			db.addEvent(new EventObject(26, "Project Reconnaissance",
					"Roboficial", robo_proj, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/project-reconnaissance/", "", "Srihari Menon", "+91 95034 92259", "Prafful Golani", "+91 96372 77279"));
			db.addEvent(new EventObject(27,
					"Saviors Of The Inter-Galactic Realm", "Roboficial",
					robo_savi, "url", "", "TBA", "http://www.bits-quark.org/2014/main_site/portfolio/saviors-of-the-inter-galactic-realm-image-processing/",
					"", "Sumit Agarwal", "+91 75072 10908 \ner.sumitagarwal@gmail.com", "Dipanshu Awasthi", "+91 81491 89388 \ndipanshu.awasthi@gmail.com"));
			db.addEvent(new EventObject(28, "Wrong Turn", "Roboficial",
					robo_wrong, "url", "", "TBA", "http://www.bits-quark.org/2014/main_site/portfolio/wrong-turn-line-following/",
					"", "Ashish Abhishek", "+91 95033 63850 \nabhishekashish18@gmail.com", "Dipanshu Awasthi", "+91 81491 89388 \ndipanshu.awasthi@gmail.com"));
			db.addEvent(new EventObject(29, "Call Of Duty", "Roboficial",
					robo_cod, "url", "", "TBA", "http://www.bits-quark.org/2014/main_site/portfolio/call-of-duty-robo-race/",
					"", "Udit Guru", "+91 88069 11445 \nuditbitsian431@gmail.com", "Rishabh Sinha", "+91 98812 20418 \nrishabh2693@gmail.com "));
			db.addEvent(new EventObject(30, "Armored Wreckage", "Roboficial",
					robo_armor, "url", "", "TBA", "http://www.bits-quark.org/2014/main_site/portfolio/robokombat/",
					"", "Rohit Jain", "+91 80879 55828 \nrohitjain1302@gmail.com", "Rohit Lad", "+91 81497 68906 \nrohit.lad.sae@gmail.com"));
			db.addEvent(new EventObject(31, "Startup Weekend",
					"Startup Weekend", startup, "url", "", "TBA",
					"http://www.bits-quark.org/2014/main_site/portfolio/startup-weekend/", "", "Rathin Shah", "+91 95033 69088 \nrathin1993@gmail.com", "", ""));

		}
		if (db.getWorkshopCount() == 0) {

			String ws_swarm = getResources().getString(R.string.ws_swarm_desc);
			String ws_cyber = getResources().getString(R.string.ws_cyber_desc);
			String ws_internet = getResources().getString(
					R.string.ws_internet_desc);
			String ws_auto = getResources().getString(R.string.ws_auto_desc);
			String ws_biped = getResources().getString(R.string.ws_biped_desc);
			String ws_magic = getResources().getString(R.string.ws_magic_desc);
			String ws_game = getResources().getString(R.string.ws_game_desc);

			db.addWorkshop(new WorkshopObject(1, "Swarm Robotics", "Rs 5600",
					ws_swarm, "Team of 4", "http://goo.gl/uXbUKA", "TBA",
					"", "Sarang Agarwal", "+91 94051 37320"));
			db.addWorkshop(new WorkshopObject(2,
					"Cyber Forensics and Information Security", "Rs 1200",
					ws_cyber, "", "http://goo.gl/rTCa2L ", "TBA", "",
					"Anadi Misra", "+91 99874 69335"));
			db.addWorkshop(new WorkshopObject(3, "Internet Of Things",
					"Rs 1250", ws_internet, "", "http://goo.gl/XYv42O", "TBA",
					"", "Aum Jadhav", "+91 99300 55028"));
			db.addWorkshop(new WorkshopObject(4,
					"MagicGlow(Persistence of Vision)", "Rs 850", ws_magic, "",
					"http://goo.gl/XYv42O", "TBA", "", "Aum Jadhav",
					"+91 99300 55028"));
			db.addWorkshop(new WorkshopObject(5, "Gameduino", "Rs 5400",
					ws_game, "Team of 4", "http://goo.gl/fxAIa9", "TBA", "",
					"Anmol Bansal", "+91 96371 30674"));
			db.addWorkshop(new WorkshopObject(6, "AutoNova", "Rs 1000",
					ws_auto, "", "http://goo.gl/r5qGrN", "TBA", "",
					"Kunal Konde", "+91 95611 34909"));
			db.addWorkshop(new WorkshopObject(7, "Biped", "Rs 5500", ws_biped,
					"Team of 4/5", "http://goo.gl/rggCj5", "TBA", "",
					"Rohan Aggarwal", "+91 83800 77457"));

		}
*/
		db.close();

		new Handler().postDelayed(new Runnable() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void run() {
				//Log.d("SplashActivity", "SplashRunning" + getSplashRunning());
				if ((getSplashRunning())) {

					if (regid.isEmpty()) {

						if (!cd.isConnectingToInternet()) {
							/*Log.d("SplashActivity",
									"GCM - RegID empty - Internet connection not found");*/
							Toast.makeText(
									getApplicationContext(),
									"Unable to connect to internet. You wont be able to use all the features.",
									Toast.LENGTH_LONG).show();

							Intent j = new Intent(Splash_Activity.this,
									MainActivity.class);
							j.putExtra("no_net", true);
							startActivity(j);

						}

						else {
							Log.i("SplashActivity",
                                    "RegID empty - Connected to internet - Will proceed with Registration");
							registerInBackground();

							
							SharedPreferences new_prefs = getSharedPreferences("notifications_prefs", MODE_PRIVATE);
							Boolean new_check = new_prefs.getBoolean(OPENED_KEY, false);
							if (new_check) {
								Toast.makeText(getApplicationContext(), "Unable to register for GCM!\nYou won't be able to recieve any updates!", Toast.LENGTH_SHORT).show();
								Intent i = new Intent(Splash_Activity.this,
										MainActivity.class);
								startActivity(i);
							}else{
                                Log.i("SplashActivity",
                                        "hi");
								Intent i = new Intent(Splash_Activity.this,
										FetchDataFirstRun.class);
								startActivity(i);
							}

						}

					} else {
						SharedPreferences prefs = getGCMPreferences(context);
						Boolean RegIdSenttoBackend = prefs.getBoolean("SentRegIdToBackend", false);
						if(!RegIdSenttoBackend){
							AsyncTask async = new AsyncTask() {

								@Override
								protected String doInBackground(Object... params) {
									sendNewRegistrationIdtoBackend();
									return "sent";
								}

							};
							async.execute();
						}
						Intent j = new Intent(Splash_Activity.this,
								MainActivity.class);
						startActivity(j);
					}

				}

				// close this activity
				finish();

			}
		}, SPLASH_TIME_OUT);

		// On create finishes here
	}

	
	@Override
	protected void onPause() {
		super.onPause();

		setSplashRunning(false);
		finish();

	}

	@Override
	protected void onResume() {

		super.onResume();

		setSplashRunning(true);

	}

	/**
	 * @author 2ku_PC Sets whether the splash screen is running or not in
	 *         {@code SharedPreferences}.
	 * 
	 */
	private void setSplashRunning(Boolean bool) {
		check = bool;
	}

	/**
	 * @author 2ku_PC Get whether the splash screen is running from the
	 *         {@code SharedPreferences}
	 * 
	 * 
	 * @return Boolean whether splash is running or not. (Default true)
	 */
	private Boolean getSplashRunning() {
		return check;
	}

	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 * 
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	@SuppressLint("NewApi")
	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
				Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {

		return getSharedPreferences(Splash_Activity.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	/**
	 * Registers the application with GCM servers asynchronously.
	 * <p>
	 * Stores the registration ID and app versionCode in the application's
	 * shared preferences.
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void registerInBackground() {
		AsyncTask async = new AsyncTask() {

			@Override
			protected String doInBackground(Object... params) {
				String msg = "";
				
				
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
						Log.d("SplashActivity",
								"registerInBackground() Running - 2");
					}

					regid = gcm.register(SENDER_ID);

					msg = "Device registered, registration ID=" + regid;
					Log.i("SplashActivity", msg);
					//Toast.makeText(getApplicationContext(), ""+regid, Toast.LENGTH_SHORT).show();

					// You should send the registration ID to your server over
					// HTTP,
					// so it can use GCM/HTTP or CCS to send messages to your
					// app.
					// The request to your server should be authenticated if
					// your app
					// is using accounts.
					sendNewRegistrationIdtoBackend();
					// For this demo: we don't need to send it because the
					// device
					// will send upstream messages to a server that echo back
					// the
					// message using the 'from' address in the message.

					// Persist the regID - no need to register again.
					storeRegistrationId(context, regid);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					// If there is an error, don't just keep trying to register.
					// Require the user to click a button again, or perform
					// exponential back-off.
					ex.printStackTrace();
				}
				return msg;
			}

		};

		async.execute();

	}

	public static String getDeviceId(Context context) {
	    final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	    if (deviceId != null) {
			return deviceId;
		}else  if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			 if ((Build.SERIAL != null)
					&& (Build.SERIAL != "unknown")) {
				return Build.SERIAL;
			} else {
				String m_szDevIDShort = "35"
						+ //we make this look like a valid IMEI
						Build.BOARD.length() % 10 + Build.BRAND.length() % 10
						+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length()
						% 10 + Build.DISPLAY.length() % 10
						+ Build.HOST.length() % 10 + Build.ID.length() % 10
						+ Build.MANUFACTURER.length() % 10
						+ Build.MODEL.length() % 10 + Build.PRODUCT.length()
						% 10 + Build.TAGS.length() % 10 + Build.TYPE.length()
						% 10 + Build.USER.length() % 10; //13 digits
				return m_szDevIDShort;
			}
		} else{
							String m_szDevIDShort = "35"
						+ //we make this look like a valid IMEI
						Build.BOARD.length() % 10 + Build.BRAND.length() % 10
						+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length()
						% 10 + Build.DISPLAY.length() % 10
						+ Build.HOST.length() % 10 + Build.ID.length() % 10
						+ Build.MANUFACTURER.length() % 10
						+ Build.MODEL.length() % 10 + Build.PRODUCT.length()
						% 10 + Build.TAGS.length() % 10 + Build.TYPE.length()
						% 10 + Build.USER.length() % 10; //13 digits
				return m_szDevIDShort;
			}
		}
	
	public void sendNewRegistrationIdtoBackend(){
		URL url;
		HttpURLConnection conn;
		String serial = getDeviceId(getApplicationContext());
		Log.i("Serial", serial);
		try {
			url = new URL("http://www.bits-waves.org/wavesapp/register.php");
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(3000);
			conn.setDoOutput(true);
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			params1.add(new BasicNameValuePair("reg_id", regid));
			params1.add(new BasicNameValuePair("serial", serial));

			String query = getQuery(params1);

			conn.setFixedLengthStreamingMode(QueryLength);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			

			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(os, "UTF-8"));
			writer.write(query);

			writer.flush();
			writer.close();
			os.close();
			conn.connect();
			status = conn.getResponseCode();
            Log.d(TAG, conn.getResponseMessage());

            InputStream is = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder finalString = new StringBuilder();
            String s;
            while((s = bufferedReader.readLine()) != null){
                finalString.append(s);
            }
            Log.d(TAG,"Response : " + finalString.toString());

		} catch (MalformedURLException e) {
			response =  "fail";
			e.printStackTrace();
			
		} catch (IOException e) {
			response =  "fail";
			e.printStackTrace();
		}
		if (status != 200) {
			Log.i("SplashActivity", "Http Response Status Code: " + status);
			response =  "fail";
		}else{
		Log.i("SplashActivity", "Successfully sent regid's details.");
		response =  "Success";
		SharedPreferences prefs = getGCMPreferences(context);
		Editor editor = prefs.edit();
		editor.putBoolean("SentRegIdToBackend", true);
		editor.commit();
		}
		
		
	}
	
	public String getQuery(List<NameValuePair> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}
		QueryLength = result.toString().getBytes().length;
		Log.i("SplashActivity", "Test Query : " + result.toString());
		return result.toString();
	}
	

	protected void storeRegistrationId(Context context, String regid) {
		// TODO Auto-generated method stub
		final SharedPreferences prefs = getGCMPreferences(context);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regid);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();

	}

}
