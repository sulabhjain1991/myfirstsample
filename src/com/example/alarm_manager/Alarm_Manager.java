package com.example.alarm_manager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class Alarm_Manager extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm__manager);
		 // get a Calendar object with current time
		 Calendar cal = Calendar.getInstance();
		 // add 5 minutes to the calendar object
		 cal.add(Calendar.SECOND, 5);
		 Intent intent = new Intent(this, AlarmReceiver.class);
//		 intent.putExtra("alarm_message", "O'Doyle Rules!");
		 // In reality, you would want to have a static variable for the request code instead of 192837
		 PendingIntent sender = PendingIntent.getBroadcast(this, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 
		 // Get the AlarmManager service
		 AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		 am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
	}


}
