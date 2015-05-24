package com.example.alarm_manager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
 
public class AlarmReceiver extends BroadcastReceiver 
{
	SharedPreferences appSharedPrefs;
 @Override
 public void onReceive(Context context, Intent intent) {
   try {
	   Context myContext = context.createPackageContext("com.lst.lifemirror", 
			   0); // where com.example is the owning  app containing the preferences
			     SharedPreferences testPrefs = myContext.getSharedPreferences 
			   ("com.lst.lifemirror", Context.MODE_PRIVATE); 
			     String str = testPrefs.getString("user_id", "");
//     Bundle bundle = intent.getExtras();
//     String message = bundle.getString("alarm_message");
//      Intent newIntent = new Intent(context, Alarm_Activity.class);
//      newIntent.putExtra("person_name", "O'Doyle Rules!");
//      newIntent.putExtra("birthday", "22 Jan 1991");
//     newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//     context.startActivity(newIntent);
	   Toast.makeText(context, str , Toast.LENGTH_SHORT).show();
	   Intent intent1 =new Intent(context, MyService.class);
	   intent1.putExtra("user_id", str);
	   intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   context.startService(intent1);
    } catch (Exception e) {
//     Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
     e.printStackTrace();
 
    }
 }
 
}