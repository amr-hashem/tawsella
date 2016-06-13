package com.example.amr.consort;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class imageShow extends AppCompatActivity {
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        image=(ImageView)findViewById(R.id.imageView);



        Intent i=getIntent();

        Bundle extras = i.getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

        image.setImageDrawable(new BitmapDrawable(getResources(), bmp));



    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        image.setImageDrawable(null);
    }


}