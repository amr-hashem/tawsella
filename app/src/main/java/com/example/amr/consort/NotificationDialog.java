package com.example.amr.consort;


        import android.app.Activity;
        import android.app.Dialog;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.telephony.SmsManager;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.Toast;

public class NotificationDialog extends AppCompatActivity {



    String phoneNumber;
    Context context;
    String travelFrom,travelTo;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_dialog);



        Intent i=getIntent();
        phoneNumber=i.getStringExtra("phoneNumber");

        sh=getSharedPreferences("details", context.MODE_PRIVATE);

        travelFrom=sh.getString("keyFrom","nothing");
        travelTo=sh.getString("keyTo", "nothing");


        DialogNotification();

    }

    private void DialogNotification() {

        final Dialog dialog=new Dialog(NotificationDialog.this);
         //get prefereence

        ViewGroup root=(ViewGroup)findViewById(R.id.notify);
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.dialog,root,false);

        Button Cancel=(Button)v.findViewById(R.id.btnCancel);
        Button Accept=(Button)v.findViewById(R.id.btnAccept);
        Button Profile=(Button)v.findViewById(R.id.btnProfile);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(phoneNumber, "Your ride  is refused "+travelFrom+"-"+travelTo);

                Toast.makeText(NotificationDialog.this,"Cancel is sent",Toast.LENGTH_LONG).show();



            }
        });
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendSMS(phoneNumber, "Your ride  is accepted "+travelFrom+"-"+travelTo);
                Toast.makeText(NotificationDialog.this, travelFrom+"-"+travelTo, Toast.LENGTH_SHORT).show();
                Toast.makeText(NotificationDialog.this,"Accept is sent", Toast.LENGTH_LONG).show();


            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(NotificationDialog.this,profile.class);
                startActivity(i);

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(v);
        dialog.show();


    }





    //---sends an SMS message to another device---
    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager. getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);}

}