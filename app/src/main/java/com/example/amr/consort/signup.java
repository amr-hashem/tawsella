//put firebase code in  validation method
package com.example.amr.consort;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class signup extends AppCompatActivity  {

    EditText Name,Phone,email,Password,birthday;
    TextView alert;
    RadioButton male,female;
    Button register;
    String gender;
    int id,myyear,mymonth,myday,currentYear;

    //put fire base definition

    public static final String mypreferences="prefid";
    @Override

    protected void onCreate(Bundle savedInstanceState) {

         //put fire base calling
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        intialize();
        dateIntialize(birthday);

    }



       //intialize element
    public void intialize(){
        Name=(EditText)findViewById(R.id.ename);
        Phone=(EditText)findViewById(R.id.ephone);
        email=(EditText)findViewById(R.id.eEmail);
        Password=(EditText)findViewById(R.id.epassowrd);
        alert=(TextView)findViewById(R.id.tvalert);
        birthday=(EditText)findViewById(R.id.ebirthday);
        male=(RadioButton)findViewById(R.id.rdmale);
        female=(RadioButton)findViewById(R.id.rdfemale);



    }
     //get date dialoge
    public void dateIntialize(final EditText datetext){


        final Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        currentYear=year;


        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datepick=new DatePickerDialog(signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datetext.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                        myyear=year;
                        mymonth=monthOfYear;
                        myday=dayOfMonth;

                    }
                },year,month,day);

                datepick.setTitle("set date");
                datepick.show();

            }
        });

    }
    //check validation
    public void validation() {
        //check on gender
        if(male.isChecked()){
            gender="male";
        }else{
            gender="female";
        }
        if(Name.getText().toString().isEmpty() ||Phone.getText().toString().isEmpty()
                ||email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()
                ||birthday.getText().toString().isEmpty()
                ||(male.isChecked()==false && female.isChecked()==false) ){

            alert.setText("please complete all your info !");
            alert.setTextColor(Color.RED);
        }else {

            Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[c-o-m]{3,})$");
            Matcher emailMatcher = emailPattern.matcher(email.getText().toString());

            Pattern phonePattern = Pattern.compile("\\d{11}");
            Matcher phoneMatcher = phonePattern.matcher(Phone.getText().toString());

            Pattern namePattern = Pattern.compile(".*\\d.*");
            Matcher nameMatcher = namePattern.matcher(Name.getText().toString());
            //check name validation
            //by abo_elmaged
            if(nameMatcher.matches() ||(Name.length()<3)){
                Name.setError("name should not contain numbers and at least 3 character");

            }else{
                Name.setError(null);
            }

            //check Phone number validation
            if( !phoneMatcher.matches()){
                Phone.setError("invalid phone number");
            }else{
                Phone.setError(null);
            }
            //check email validation
            if (!emailMatcher.matches()) {
                email.setError("invalid email");
            }else{
                email.setError(null);
            }
            //check password validation
            if(Password.length() < 7){
                Password.setError("password at least 7 digit");
            }else{
                Password.setError(null);
            }
            //check age
            if((currentYear-myyear)<18){
                birthday.setError("age at least 18 years");
            }else{
                birthday.setError(null);
            }
            if(!nameMatcher.matches() && (Name.length()>2) && phoneMatcher.matches() &&
                    emailMatcher.matches() && (Password.length() > 7) &&
                    ((currentYear-myyear)>18)
                    ){

            //put your firebase code here


            }
        }
    }
      //call validation
    public void clickregister(View view) {

        validation();

    }


}

