package com.example.alarm_manager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Alarm_Activity  extends Activity
{

	TextView person_name,person_birthday;
	CheckBox call_chk,sms_chk,fb_check;
	Button submit_btn;
	EditText call_no,sms_no;

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_activity);

		String name=getIntent().getExtras().getString("person_name");
		String birthday=getIntent().getExtras().getString("birthday");

		person_name=(TextView)findViewById(R.id.person_name);
		person_birthday=(TextView)findViewById(R.id.person_birthday);

		call_chk=(CheckBox)findViewById(R.id.call_check);
		sms_chk=(CheckBox)findViewById(R.id.sms_check);
		fb_check=(CheckBox)findViewById(R.id.fb_check);

		submit_btn=(Button)findViewById(R.id.submit_info);
		call_no=(EditText)findViewById(R.id.call_no);
		sms_no=(EditText)findViewById(R.id.sms_no);

		person_name.setText(name);
		person_birthday.setText(birthday);
		call_chk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(call_chk.isChecked())
				{

					call_no.setVisibility(View.VISIBLE);
				}

				else if(!call_chk.isChecked())
				{

					call_no.setVisibility(View.GONE);
					call_no.setText("");
				}

			}
		});

		sms_chk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(sms_chk.isChecked())
				{

					sms_no.setVisibility(View.VISIBLE);
				}

				else if(!sms_chk.isChecked())
				{

					sms_no.setVisibility(View.GONE);
					sms_no.setText("");
				}

			}
		});

		submit_btn.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v)
			{
				submit_btn_functionality();

			}

		});



	}

	public void submit_btn_functionality()
	{

		if(call_chk.isChecked())
		{
			if(String.valueOf(call_no.getText()).length()>1)
			{
				String url = "tel:" + String.valueOf(call_no.getText());
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
				startActivity(intent);
			}
			else
				Toast.makeText(Alarm_Activity.this, "Phone no is not valid", Toast.LENGTH_LONG).show();
		}

		if(sms_chk.isChecked())
		{
			if(String.valueOf(sms_no.getText()).length()>1)
			{
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(String.valueOf(sms_no.getText()), null, "Wish u a vry-vry happy birthday to you", null, null);
			}
			
			else
				Toast.makeText(Alarm_Activity.this, "Phone no is not valid", Toast.LENGTH_LONG).show();
		}

		if(fb_check.isChecked())
		{

		}
		sms_no.setText("");
		call_no.setText("");
		//Alarm_Activity.this.finish();
		
	}
	@Override
	public void onBackPressed() 
	{
		System.exit(0);
		super.onBackPressed();
	}
}
