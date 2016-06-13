//go to btnride method
package com.example.amr.consort;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferRide extends AppCompatActivity {
    String user_id;
    EditText time,date,price,seats,car_model,plate_number,Comment;
    CheckBox chksmoke,chkmusic,chkanimal;
    int myyear,mymonth,myday,myhour,myminute,myprice,myseats;
    String from,to,carModel,platenumber,animal,music,smoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        intialize();
        declareSpiner();
        timeIntialize(time);
        dateIntialize(date);


    }

    public void intialize(){
        time=(EditText)findViewById(R.id.etTime);
        date=(EditText)findViewById(R.id.etDate);
        price=(EditText)findViewById(R.id.etprice);
        seats=(EditText)findViewById(R.id.etseats);
        car_model=(EditText)findViewById(R.id.etmodel);
        plate_number=(EditText)findViewById(R.id.etplate);
        chkanimal=(CheckBox)findViewById(R.id.chkBAnimal);
        chkmusic=(CheckBox)findViewById(R.id.chkBMusic);
        chksmoke=(CheckBox)findViewById(R.id.chkBSmoke);
        Comment=(EditText)findViewById(R.id.etcomment);
        //get user_id
        SharedPreferences sh=getSharedPreferences("u",MODE_PRIVATE);
        user_id=sh.getString("key","nothing here");
    }
    public void declareSpiner(){
        final Spinner subjectspiner=(Spinner)findViewById(R.id.sfrom);
        final Spinner subjectspiner2=(Spinner)findViewById(R.id.sto);
        //final TextView txtv=(TextView)findViewById(R.id.tv6);
        final List<String> spinnerarray=new ArrayList<String>();

        //add cites to spiner
        spinnerarray.add("Alexandria");spinnerarray.add("Aswan");spinnerarray.add("Asyut");spinnerarray.add("Beheira");
        spinnerarray.add("Beni Suef");spinnerarray.add("Cairo");spinnerarray.add("Dakahlia");spinnerarray.add("Damietta");
        spinnerarray.add("Faiyum");spinnerarray.add("Gharbia");spinnerarray.add("Giza");spinnerarray.add("Ismailia");
        spinnerarray.add("Kafr el-Sheikh");spinnerarray.add("Luxor");spinnerarray.add("Matruh");spinnerarray.add("Minya");
        spinnerarray.add("Monufia");spinnerarray.add("New Valley");spinnerarray.add("North Sinai");spinnerarray.add("Port Said");
        spinnerarray.add("Qalyubia");spinnerarray.add("Qena");spinnerarray.add("Red Sea");spinnerarray.add("Sharqia");
        spinnerarray.add("Sohag");spinnerarray.add("South Sinai");spinnerarray.add("Suez");



        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectspiner.setAdapter(adapter);
        subjectspiner2.setAdapter(adapter);
        subjectspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // txtv.setText(spinnerarray.get(position));
                from=spinnerarray.get(position);
//                Toast.makeText(OfferRide.this,from,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        subjectspiner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to=spinnerarray.get(position);
//                Toast.makeText(OfferRide.this,to,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void timeIntialize(final EditText timetext){


        final Calendar calendar=Calendar.getInstance();

        final int hour=calendar.get(Calendar.HOUR_OF_DAY);
        final int minute2=calendar.get(Calendar.MINUTE);
        timetext.setInputType(InputType.TYPE_NULL);
        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timepick=new TimePickerDialog(OfferRide.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timetext.setText(hourOfDay+":"+minute);
                        myhour=hourOfDay;
                        myminute=minute;
//                        Toast.makeText(OfferRide.this,myhour+"/"+myminute,Toast.LENGTH_LONG).show();
                    }
                },hour,minute2,true);
                timepick.setTitle("set time");
                timepick.show();
            }

        });

    }

    public void dateIntialize(final EditText datetext){


        final Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        datetext.setInputType(InputType.TYPE_NULL);
        String name=this.getLocalClassName();
        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datepick=new DatePickerDialog(OfferRide.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datetext.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                        myyear=year;
                        mymonth=monthOfYear;
                        myday=dayOfMonth;
//                        Toast.makeText(OfferRide.this,myyear+"/"+mymonth+"/"+myday,Toast.LENGTH_LONG).show();
                    }
                },year,month,day);
                datepick.setTitle("set date");
                datepick.show();

            }
        });
    }

    public void btnride(View view) {
        //check options
        if(chkanimal.isChecked()){
            animal="true";
            Toast.makeText(this,animal,Toast.LENGTH_LONG).show();
        }else{
            animal="false";
            Toast.makeText(this,animal,Toast.LENGTH_LONG).show();
        }
        if(chksmoke.isChecked()){
            smoke="true";
            Toast.makeText(this,smoke,Toast.LENGTH_LONG).show();
        }else{
            smoke="false";
            Toast.makeText(this,smoke,Toast.LENGTH_LONG).show();
        }
        if(chkmusic.isChecked()){
            music="true";
            Toast.makeText(this,music,Toast.LENGTH_LONG).show();
        }else{
            music="false";
            Toast.makeText(this,music,Toast.LENGTH_LONG).show();
        }

        try{

            /*
            this where you will you put firebase code
            just uncomment these variables and put it in your code :)
            */
            /*
          from;
            to ;
            String.valueOf(mymonth );
            String.valueOf(myday);
             price.getText().toString();
            seats.getText().toString();
            car_model.getText().toString() ;
            plate_number.getText().toString();
            Comment.getText().toString();
            music;
            smoke;
           animal;
           user_id;
           ("time",myhour+"-"+myminute);
           */
            Intent MainIntent=new Intent(this,MainActivity.class);
            startActivity(MainIntent);



        }catch(Exception ex){

        }
    }


    public void btncancel(View view) {
        time.setText(null);price.setText(null);date.setText(null);seats.setText(null);car_model.setText(null);
        plate_number.setText(null);Comment.setText(null);
        chksmoke.setChecked(false);chkmusic.setChecked(false);chkanimal.setChecked(false);

    }
}
