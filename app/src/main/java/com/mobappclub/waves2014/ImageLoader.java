package com.mobappclub.waves2014;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageLoader extends AsyncTask<Void, Integer, Void> {

	private ProgressBar pb;
	private String url;
	private ImageView img;
	private Bitmap bmp;
	private ImageLoaderListener listener;
	private int final_id;
	private int sponsors;

	public ImageLoader(String url, ProgressBar pb, ImageView img, Context c,
			Bitmap bmp, ImageLoaderListener imageLoaderListener, int final_id) {

		this.url = url;
		this.pb = pb;
		this.img = img;
		this.bmp = bmp;
		this.listener = imageLoaderListener;
		this.final_id = final_id;
	}

	public ImageLoader(String url, ProgressBar pb, ImageView img, Context c,
			Bitmap bmp, ImageLoaderListener imageLoaderListener, int final_id,int sponsors) {

		this.url = url;
		this.pb = pb;
		this.img = img;
		this.bmp = bmp;
		this.listener = imageLoaderListener;
		this.final_id = final_id;
		this.sponsors = sponsors;
	}
	
	public interface ImageLoaderListener {

		void onImageDownloaded(Bitmap bmp, int position);

	}

	@Override
	protected void onPreExecute() {
		if(pb!=null)
		pb.setVisibility(View.VISIBLE);
		
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		bmp = getBitmapFromURL(url);
		if (bmp!=null) {
			/*Log.i("ImageLoader - doInBackground()",
					"Got bmp from getBitmapFromURL(url)");*/
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {

		if (listener != null) {
			//Log.d("ImageLoader - onPostExecute", "listener!=null");
			listener.onImageDownloaded(bmp, final_id);
		}
		if(sponsors==1)
			img.setImageBitmap(bmp);
		super.onPostExecute(result);
	}

	public static Bitmap getBitmapFromURL(String link) {

		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);

			connection.connect();
			InputStream input = new BufferedInputStream(
					connection.getInputStream());
			int response_code = connection.getResponseCode();
			//Log.d("ImageLoader - Http Response Code", "" + response_code);

			if (response_code == 200) {
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
				//connection.disconnect();
				return myBitmap;
			} else {
				//connection.disconnect();
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			//Log.e("getBmpFromUrl error: ", e.getMessage().toString());
			return null;
		}
	}

}