//please put fire base code in validation method

package com.example.amr.consort;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    EditText email,password;
    TextView tv;
    int id;
    SharedPreferences sh;
    CheckBox showpass;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intialize();
    }
       //intialize xml element
     public void intialize(){
        email=(EditText)findViewById(R.id.etmail);
        password=(EditText)findViewById(R.id.etpassword);
        tv=(TextView)findViewById(R.id.tvalertlog);
        showpass=(CheckBox)findViewById(R.id.chkshowpass);
    }

    //check validation and add your fierbase
    public void validation() {
        //check password validation
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[c-o-m]{3,})$");
        Matcher matcher = pattern.matcher(email.getText().toString());
        if (password.getText().toString().isEmpty()||email.getText().toString().isEmpty())
        {
            tv.setText("please complete your information");
            tv.setTextColor(Color.RED);
        }else
        {
            if(password.length()<7)
            {
                password.setError("weak password");
            }else{
                password.setError(null);
            }
            if(!matcher.matches()){
                email.setError("weak password");
            }else{
                email.setError(null);
            }

            if(password.length()>7 && matcher.matches()){

                 //put your firebase code here !

            }
        }
    }

     //call validation
    public void buclick(View view) {

        validation();//=>you will put firebase code in validation method

    }

    //go to signup
    public void createclick(View view) {

        Intent intent_signup=new Intent(this,signup.class);
        startActivity(intent_signup);
    }
      //put forget password here
    public void passwordclick(View view) {
        System.out.println("passsssssssssss");
    }

    //hint password
    public void chkclicked(View view) {


        if(showpass.isChecked()) {
            Toast.makeText(login.this, "clicked", Toast.LENGTH_SHORT).show();
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{

            password.setTransformationMethod(PasswordTransformationMethod.getInstance());



        }

    }
}