package com.example.amr.consort;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        final Intent openMainActivity=new Intent(this,MainActivity.class);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    startActivity(openMainActivity);
                }
            }

        };
        timer.start();
    }
}
