//onrecieve class dosen't use in this project


package com.example.amr.consort;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
//import android.support.v4.media.MediaDescriptionCompatApi21;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Locale;

import static android.app.Notification.*;

public class SmsReciver extends BroadcastReceiver {

	String number;
	String CertainMessage;
	private RemoteViews view;
	static int c = 1;

	@Override
	public void onReceive(Context context, Intent intent) {

		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
		//---get the SMS message passed in---


			Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String str = "";
		if (bundle != null) {
			//---retrieve the SMS message received---
			final Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];


			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);


				if (i == 0) {
					//---get the sender address/phone number---
					str += msgs[i].getOriginatingAddress();
					number = str;
					//Toast.makeText(context, "" + str, Toast.LENGTH_LONG).show();

					str += ": ";
					//Toast.makeText(context, "" + str, Toast.LENGTH_LONG).show();
				}
				//---get the message body---
				str += msgs[i].getMessageBody().toString();
				CertainMessage=msgs[i].getMessageBody().toString();
			}
			//---display the new SMS message---
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
			Log.d("SMSReceiver", str);
		}

		if (CertainMessage.equals("Hello from tawsela"))
		{notification(context);}

	}

}



	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void notification(Context context) {

		//get travel destination
			//put

		SharedPreferences sh=context.getSharedPreferences("details",context.MODE_PRIVATE);
		String travelFrom=sh.getString("keyFrom","nothing");
		String travelTo=sh.getString("keyTo","nothing");

		Intent intent=new Intent(context, NotificationDialog.class);

		intent.putExtra("phoneNumber", number);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack( NotificationDialog.class);
		stackBuilder.addNextIntent(intent);

		PendingIntent pin= stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
				| PendingIntent.FLAG_ONE_SHOT);

		view=new RemoteViews("com.example.amr.consort",R.layout.activity_custom_notification);
		Builder builder=new Builder(context);



		long vibration []={0,500,100, 200,};
		builder.setAutoCancel(true)
				.setContent(view)
				.setTicker("TAWSELA "+" "+travelFrom+"-"+travelTo)
				.setSmallIcon(R.drawable.ride)
				.setVibrate(vibration)
				.setContentIntent(pin);

		Notification notf= builder.build();

		NotificationManager mngr=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mngr.notify(c,notf);
		c++;
	}


}
