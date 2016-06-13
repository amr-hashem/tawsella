/*
you add your code in 2 method
btnclick      btnprofile
 */
package com.example.amr.consort;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONObject;

public class contentRide extends AppCompatActivity {

    TextView from,to,date,time,price,comment,car_model,car_plate,seats;
    CheckBox smoke,music,animal;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    SharedPreferences sh,sh2;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentride);
        sentPI = PendingIntent. getBroadcast(this, 0, new Intent(SENT), 0); //for sender

        deliveredPI = PendingIntent. getBroadcast(this, 0, new Intent(DELIVERED), 0);//for reciever



        intialize();
        setData();
    }

    @Override
    public void onResume() {
        super.onResume();
        //---create the BroadcastReceiver when the SMS is sent---
        smsSentReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {//cases of any sent message
                switch (getResultCode())
                {
                    case Activity. RESULT_OK:
                        Toast. makeText(getBaseContext(), "SMS sent",Toast. LENGTH_SHORT).show();
                        break;
                    case SmsManager. RESULT_ERROR_GENERIC_FAILURE:
                        Toast. makeText(getBaseContext(), "Generic failure",Toast. LENGTH_SHORT).show();
                        break;
                    case SmsManager. RESULT_ERROR_NO_SERVICE:
                        Toast. makeText(getBaseContext(), "No service",Toast. LENGTH_SHORT).show();
                        break;
                    case SmsManager. RESULT_ERROR_NULL_PDU:
                        Toast. makeText(getBaseContext(), "Null PDU", Toast. LENGTH_SHORT).show();
                        break;
                    case SmsManager. RESULT_ERROR_RADIO_OFF:
                        Toast. makeText(getBaseContext(), "Radio off",Toast. LENGTH_SHORT).show();
                        break;
                }
            }
        };
        //---create the BroadcastReceiver when the SMS is delivered---
        smsDeliveredReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity. RESULT_OK:
                        Toast. makeText(getBaseContext(), "SMS delivered",Toast. LENGTH_SHORT).show();
                        break;
                    case Activity. RESULT_CANCELED:
                        Toast. makeText(getBaseContext(), "SMS not delivered",Toast. LENGTH_SHORT).show();
                        break;
                }
            }
        };
        //---register the two BroadcastReceivers---
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
    }
    @Override
    public void onPause() {
        super.onPause();
        //---unregister the two BroadcastReceivers---
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }

    public void onClick(View v) {

    }
    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager. getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);

    }
    public void intialize(){
        from=(TextView)findViewById(R.id.txtvFrom);
        to=(TextView)findViewById(R.id.txtvTo);
        date=(TextView)findViewById(R.id.txtVDate);
        time=(TextView)findViewById(R.id.txtVTime);
        price=(TextView)findViewById(R.id.txtVBudget);
        comment=(TextView)findViewById(R.id.txtVComment);
        car_model=(TextView)findViewById(R.id.txtVCarType);
        car_plate=(TextView)findViewById(R.id.txtVCarPlate);
        seats=(TextView)findViewById(R.id.txtVSeatsLeft);
        smoke=(CheckBox)findViewById(R.id.chkBSmoke);
        music=(CheckBox)findViewById(R.id.chkBMusic);
        animal=(CheckBox)findViewById(R.id.chkBAnimal);

    }




      public void setData(){

          Bundle bundle=getIntent().getExtras();
          from.setText(bundle.getString("from"));to.setText(bundle.getString("to"));
          date.setText(bundle.getString("date"));time.setText(bundle.getString("time"));
          price.setText(bundle.getString("budjet"));comment.setText(bundle.getString("comment"));
          car_model.setText(bundle.getString("type"));car_plate.setText(bundle.getString("plate"));
          seats.setText(bundle.getString("seats"));smoke.setChecked(Boolean.valueOf(bundle.getString("smoke")));
          music.setChecked(Boolean.valueOf(bundle.getString("music")));animal.setChecked(Boolean.valueOf(bundle.getString("animal")));


          sh = getSharedPreferences("details",MODE_PRIVATE);
          SharedPreferences.Editor editor = sh.edit();
          editor.putString("keyFrom",bundle.getString("from"));
          editor.putString("keyTo",bundle.getString("to"));
          editor.commit();

      }

    public void btnclick(View view) {
        /*
        this two lines will get user_id comming from GetRide class
        as you will get onlt it's data from firebase
         */

        Bundle bundle=getIntent().getExtras();
        bundle.getString("user_id");
         //this line to send message to travel owner
        sendSMS("phone number here", "Hello from tawsela");//<<-----------the phone and the message
    }




    public void btnProfile(View view) {
        Bundle bundle=getIntent().getExtras();
        sh2 = getSharedPreferences("profile",MODE_PRIVATE);
        SharedPreferences.Editor editor = sh2.edit();
        editor.putString("profileId", bundle.getString("user_id"));
        editor.commit();
        Intent intent=new Intent(this,profile.class);
        startActivity(intent);
    }
}