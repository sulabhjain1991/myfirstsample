package com.example.alarm_manager;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyService extends IntentService
{
	
	
	// No-arg constructor is required
	public MyService() 
	{
		 super("MyService");
//		Toast.makeText(getApplicationContext(), "Hello how r u", Toast.LENGTH_LONG).show();
	  
	}
	 
	@Override
	protected void onHandleIntent(Intent intent)
	{
		String str = intent.getExtras().getString("user_id");
	 System.out.println("user id "+ str);
	   new Show_Film_Today_Task().execute();
	 
	  
	 
	}
	
	class Show_Film_Today_Task extends AsyncTask<Void, String, String>
	{
		@Override
		protected void onPreExecute() 
		{
System.out.println("service started");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params)
		{
			
			try {
				uploadVideo();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			
				

			System.out.println("successfuly send");
			super.onPostExecute(result);
		}

	}

	private void uploadVideo() throws ParseException, IOException {

	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("https://www.lifemirror.org/api/uploadVideo");

	    FileBody filebodyVideo = new FileBody(new File("/mnt/sdcard/audiorecordtest.3gp"));
	    StringBody title = new StringBody("Sample Video");
	    StringBody filmId = new StringBody("5");
	    StringBody userId = new StringBody("10");
	    StringBody sessionKey = new StringBody("8gcYvblHo1rNqTZ7zKMaAPrIyk52jVDJX6ZxxZKg");
	    StringBody datetime = new StringBody("2013-04-12 02:34:00");
	    StringBody lattitude = new StringBody("22.22");
	    StringBody longitude = new StringBody("22.22");
	    MultipartEntity reqEntity = new MultipartEntity();
	   
	    reqEntity.addPart("title", title);
	    reqEntity.addPart("filmId", filmId);
	    reqEntity.addPart("userId", userId);
	    reqEntity.addPart("sessionKey", sessionKey);
	    reqEntity.addPart("datetime", datetime);
	    reqEntity.addPart("lattitude", lattitude);
	    reqEntity.addPart("longitude", longitude);
	    
	    reqEntity.addPart("videoFile", filebodyVideo);
	    httppost.setEntity(reqEntity);

	    // DEBUG
	    System.out.println( "executing request " + httppost.getRequestLine( ) );
	    HttpResponse response = httpclient.execute( httppost );
	    HttpEntity resEntity = response.getEntity( );

	    // DEBUG
	    System.out.println( response.getStatusLine( ) );
	    if (resEntity != null) {
	      System.out.println( EntityUtils.toString( resEntity ) );
	    } // end if

	    if (resEntity != null) {
	      resEntity.consumeContent( );
	    } // end if

	    httpclient.getConnectionManager( ).shutdown( );
	} // end of uploadVideo( )
	}
