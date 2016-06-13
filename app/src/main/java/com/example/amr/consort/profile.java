package com.example.amr.consort;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONObject;

public class profile extends AppCompatActivity {

    ImageView image;
    TextView name,gender,job,place,age;
    ImageButton phone, facebook,e_maile;
    RatingBar rating,cusome_ratingBar;
    Button btnRating;
    Float ratingValue;
    String user_id,u_name,u_password,u_phone,u_email,u_job,u_address,u_gender,u_age;

    SharedPreferences sh2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sh=getSharedPreferences("u", MODE_PRIVATE);
        user_id=sh.getString("key", "nothing");
        initialization();
        getData();



    }

    private  void initialization(){

        image = (ImageView) findViewById(R.id.imageVprofile);
        name = (TextView) findViewById(R.id.tvname2);
        gender = (TextView) findViewById(R.id.tvgender2);
        job = (TextView) findViewById(R.id.tvjob2);
        place = (TextView) findViewById(R.id.tvplace2);
        age = (TextView) findViewById(R.id.tvage2);


        e_maile=(ImageButton)findViewById(R.id.imgemail);
        facebook = (ImageButton) findViewById(R.id.imgfacebook);
        phone = (ImageButton) findViewById(R.id.imgphone);

        rating = (RatingBar) findViewById(R.id.ratingBar);



    }
    private void ContentLisetner(final String  number,final String mail) {
//        number = "01288973254";
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    if (ActivityCompat.checkSelfPermission(profile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(profile.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(profile.this, "error", Toast.LENGTH_SHORT).show();
                }


            }
        });


        e_maile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT, "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(profile.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();

                }
            }
        });
        rating.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ShowCustomRatingDialog();
                return false;
            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(profile.this, imageShow.class);


                image.buildDrawingCache();

                Bitmap bitmap = image.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", bitmap);
                i.putExtras(extras);
                startActivity(i);

            }
        });
    }

    private void ShowCustomRatingDialog() {
        final Dialog dialog=new Dialog(profile.this);
        dialog.setTitle("Pute Rate");
        ViewGroup root=(ViewGroup)findViewById(R.id.rel);
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.custome_rating_dialog,root,false);

        cusome_ratingBar=(RatingBar)v.findViewById(R.id.dialogratingBar);
        btnRating=(Button)v.findViewById(R.id.btnRating);
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DataBase code must be write here

                ratingValue = cusome_ratingBar.getRating();
                rating.setRating(ratingValue);
                dialog.cancel();
            }
        });

        dialog.setContentView(v);
        dialog.show();

    }

    public void getData(){
//        Bundle bundle=getIntent().getExtras();
//        String user_id2=bundle.getString("profileId");
        SharedPreferences sh=getSharedPreferences("profile", MODE_PRIVATE);
        String user_id2=sh.getString("profileId", "nothing");
        Toast.makeText(profile.this, user_id2, Toast.LENGTH_SHORT).show();
        if(user_id2=="nothing"){
            Toast.makeText(profile.this, "nothing", Toast.LENGTH_SHORT).show();
            AQuery aq=new AQuery(this);
            String url="http://192.168.43.99:8080/profile?userid="+user_id+"&chk=1";
            aq.ajax(url, JSONObject.class, this, "jsonCallBack");

        }else{
            Toast.makeText(profile.this, "number", Toast.LENGTH_SHORT).show();
            AQuery aq=new AQuery(this);
            String url="http://192.168.43.99:8080/profile?userid="+user_id2+"&chk=1";
            aq.ajax(url, JSONObject.class, this, "jsonCallBack");

        }

        sh2 = getSharedPreferences("profile",MODE_PRIVATE);
        SharedPreferences.Editor editor = sh2.edit();
        editor.putString("profileId","nothing");
        editor.commit();
    }

    public void jsonCallBack(String url,JSONObject json,AjaxStatus status){
        if(json!=null){
            System.out.println("Json is not null");

            try{

                //deal with service
                String results =json.getString("result");
                JSONArray arr=new JSONArray(results);
                for(int i=0; i<arr.length();i++) {
                    JSONObject part=arr.getJSONObject(i);
                    u_name = part.getString("name");
                    u_password = part.getString("password");
                    u_phone = part.getString("phone");
                    u_email = part.getString("email");
                    u_job = part.getString("job");
                    u_address = part.getString("address");
                    u_gender=part.getString("gender");
                    u_age=part.getString("age");


                }
                name.setText(u_name);gender.setText(u_gender);job.setText(u_job);
                place.setText(u_address);age.setText(u_age);
                ContentLisetner(u_phone,u_email);
            }catch(Exception ex){

            }
        }else{
            System.out.println("JJJJJJJJJJJJJJJJJJ");
        }
    }

}

