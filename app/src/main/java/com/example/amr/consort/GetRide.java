//go to btnsearch method

package com.example.amr.consort;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetRide extends AppCompatActivity {

    String cityFrom,cityTo,fromjson,tojson;
    ListView listview;
    Context context;



    public List <String>  journey=new ArrayList<String>();
    public List <String>  date=new ArrayList<String>();
    public List <String>  time=new ArrayList<String>();
    public List <String>  budget=new ArrayList<String>();
    public List <String>  animal=new ArrayList<String>();
    List <String>  from=new ArrayList<String>();
    List <String>  to=new ArrayList<String>();
    List <String>  smoke=new ArrayList<String>();
    List <String>  music=new ArrayList<String>();
    List <String>  comment=new ArrayList<String>();
    List <String>  car_type=new ArrayList<String>();
    List <String>  car_plate=new ArrayList<String>();
    List <String>  seats=new ArrayList<String>();
    List <String> user_id=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ride);
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
        context=this;
        declareSpiner();

    }
    public void declareSpiner(){
        final Spinner spinerFrom=(Spinner)findViewById(R.id.sfrom);
        final Spinner spinerTo=(Spinner)findViewById(R.id.sto);
        //final TextView txtv=(TextView)findViewById(R.id.tv6);
        final List<String> spinnerarray=new ArrayList<String>();

        //add cites to spiner
        spinnerarray.add("Cairo");spinnerarray.add("Alexandria");spinnerarray.add("Aswan");spinnerarray.add("Asyut");spinnerarray.add("Beheira");
        spinnerarray.add("Beni Suef");spinnerarray.add("Dakahlia");spinnerarray.add("Damietta");
        spinnerarray.add("Faiyum");spinnerarray.add("Gharbia");spinnerarray.add("Giza");spinnerarray.add("Ismailia");
        spinnerarray.add("Kafr el-Sheikh");spinnerarray.add("Luxor");spinnerarray.add("Matruh");spinnerarray.add("Minya");
        spinnerarray.add("Monufia");spinnerarray.add("New Valley");spinnerarray.add("North Sinai");spinnerarray.add("Port Said");
        spinnerarray.add("Qalyubia");spinnerarray.add("Qena");spinnerarray.add("Red Sea");spinnerarray.add("Sharqia");
        spinnerarray.add("Sohag");spinnerarray.add("South Sinai");spinnerarray.add("Suez");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerFrom.setAdapter(adapter);
        spinerTo.setAdapter(adapter);
        spinerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // txtv.setText(spinnerarray.get(position));
                cityFrom=spinnerarray.get(position);
                Toast.makeText(GetRide.this, cityFrom, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityTo=spinnerarray.get(position);
                Toast.makeText(GetRide.this, cityTo, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void btnsearch(View view) {

       /*you will put your firebase code here to get this element in green color
        note : you will need user id as you will use it in contentRide class
        */

        if(cityFrom.equals("") && cityTo.equals("")){
            journey.add(("from")+"-"+("to"));
            date.add(("day")+"-"+("month"));
            time.add(("time"));budget.add(("price"));
            animal.add(("animal"));from.add(("from"));
            to.add(("to"));smoke.add(("smoke"));
            music.add(("music"));comment.add(("comment"));
            car_plate.add(("plate_car"));car_type.add(("car_model"));
            seats.add(("seats"));user_id.add(("users_idusers"));

            listview = (ListView) findViewById(R.id.listView);
            listview.setAdapter(new CustomAdapter(GetRide.this, journey, date, time, budget,animal,
                    from,to,smoke,music,comment,car_type,car_plate,seats,user_id
            ));

    }



                     }

}
