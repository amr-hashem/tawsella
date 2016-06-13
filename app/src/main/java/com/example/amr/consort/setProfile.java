package com.example.amr.consort;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class setProfile extends AppCompatActivity implements View.OnClickListener {

    ImageView imvprofile;
    Button btnEditPhoto,btnSave,btnCancel;
    EditText name,password,phone,e_mail,facebook,address,job;
    static final int CAMERA_PIC_REQUEST = 1111;
    private static final int SELECTED_PICTURE = 1;
    private AlertDialog alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);

        imvprofile=(ImageView)findViewById(R.id.ivProfile);

        btnEditPhoto=(Button)findViewById(R.id.btnEdit);
        btnEditPhoto.setOnClickListener(this);

        btnCancel=(Button)findViewById(R.id.btnCancel);

        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        name=(EditText)findViewById(R.id.edtName);
        password=(EditText)findViewById(R.id.edtPassword);
        phone=(EditText)findViewById(R.id.edtPhone);
        e_mail=(EditText)findViewById(R.id.edtE_mail);
        address=(EditText)findViewById(R.id.edtAddress);
        job=(EditText)findViewById(R.id.edtJob);



        phone.setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    private void ShowCustomDialog() {
        final Dialog dialog=new Dialog(setProfile.this);
        dialog.setTitle("Edit Photo");
        ViewGroup root=(ViewGroup)findViewById(R.id.rel);
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.customdialog,root,false);

        ImageButton imBgallary=(ImageButton)v.findViewById(R.id.imBGallary);
        imBgallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECTED_PICTURE);
                dialog.cancel();

            }
        });

        ImageButton imBCamera=(ImageButton)v.findViewById(R.id.imBGamera);
        imBCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent,
                        CAMERA_PIC_REQUEST);
                dialog.cancel();
            }
        });
        ImageButton imBremove=(ImageButton)v.findViewById(R.id.imbRemove);
        imBremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imvprofile.setImageDrawable(null);
                dialog.cancel();
            }
        });

        dialog.setContentView(v);
        dialog.show();


    }
    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {

        super.onActivityResult(arg0, arg1, arg2);

        switch (arg0) {
            case SELECTED_PICTURE:
                if (arg1 == RESULT_OK) {

                    Uri uri = arg2.getData();

                    String[] projection = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(uri, projection,
                            null, null, null);
                    cursor.moveToFirst();

                    int ColumnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(ColumnIndex);
                    cursor.close();
                    Bitmap yourselectedimage = BitmapFactory.decodeFile(filepath);
                    Drawable d = new BitmapDrawable(yourselectedimage);
                    imvprofile.setImageDrawable(d);

                }

                break;
            case CAMERA_PIC_REQUEST:

                if (arg1 == RESULT_OK) {
                    Bitmap thumbnail = (Bitmap) arg2.getExtras().get("data");
                    imvprofile.setImageBitmap(thumbnail);

                }

            default:
                break;
        }


    }



    private void validation() {



        if(name.getText().toString().isEmpty() ||phone.getText().toString().isEmpty()
                ||e_mail.getText().toString().isEmpty() || password.getText().toString().isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(setProfile.this);
            builder.setMessage("please complete all your info !");

            alert = builder.create();
            alert.setCanceledOnTouchOutside(true);
//            alert.setIconAttribute(R.drawable.error);

            alert.show();

        }else {

            Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[c-o-m]{3,})$");
            Matcher emailMatcher = emailPattern.matcher(e_mail.getText().toString());

            Pattern phonePattern = Pattern.compile("\\d{11}");
            Matcher phoneMatcher = phonePattern.matcher(phone.getText().toString());

            Pattern namePattern = Pattern.compile(".*\\d.*");
            Matcher nameMatcher = namePattern.matcher(name.getText().toString());
            //check name validation
            //by abo_elmaged
            if(nameMatcher.matches() ||(name.length()<2)){
                name.setError("name should not contain numbers and at least 3 character");

            }else{
                name.setError(null);
            }

            //check Phone number validation
            if( !phoneMatcher.matches()){
                phone.setError("invalid phone number");
            }else{
                phone.setError(null);
            }
            //check email validation
            if (!emailMatcher.matches()) {
                e_mail.setError("invalid email");
            }else{
                e_mail.setError(null);
            }
            //check password validation
            if(password.length() < 7){
                password.setError("password at least 7 digit");
            }else{
                password.setError(null);
            }

            if(!nameMatcher.matches() && (name.length()>2) && phoneMatcher.matches() &&
                    emailMatcher.matches() && (password.length() > 7)
                    ){
                Toast.makeText(setProfile.this,"Gooooooooooooooooooood",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSave:

                validation();

                break;
            case R.id.btnEdit:

                ShowCustomDialog();

            default:
                break;
        }

    }
}
