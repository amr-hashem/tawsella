/*
you will not need to put any code here
 this is just to pass data to contentRide class
 */
package com.example.amr.consort;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter{
    List<String> journeyName=new ArrayList<String>();
    List<String> journeyDate=new ArrayList<String>();
    List<String> journeyTime=new ArrayList<String>();
    List<String> journeybudget=new ArrayList<String>();
    List <String>  animal=new ArrayList<String>();
    List <String>  from=new ArrayList<String>();
    List <String>  to=new ArrayList<String>();
    List <String>  smoke=new ArrayList<String>();
    List <String>  music=new ArrayList<String>();
    List <String>  comment=new ArrayList<String>();
    List <String>  car_type=new ArrayList<String>();
    List <String>  car_plate=new ArrayList<String>();
    List <String>  seats=new ArrayList<String>();
    List <String>  user_id=new ArrayList<String>();



    public Context  context;

    private static LayoutInflater inflater=null;

    public CustomAdapter(com.example.amr.consort.GetRide mainact, List<String> journey, List<String> date,
                         List<String>time,List<String>budget,List<String>animal2,List<String>from2,List<String>to2,List<String>smoke2,
                         List<String>music2,List<String>comment2,List<String>car_type2,List<String>car_plate2,List<String>seats2
            ,List<String>user_id2 ) {
        // TODO Auto-generated constructor stub
        journeyName=journey;journeyDate=date;journeyTime=time;
        journeybudget=budget;animal=animal2;from=from2;
        to=to2;smoke=smoke2;music=music2;
        comment=comment2;car_type=car_type2;car_plate=car_plate2;
        seats=seats2;
        user_id=user_id2;
        context=mainact;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return journeyName.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView journey;
        TextView date;
        TextView time;
        TextView budget;
        ;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();


        convertView = inflater.inflate(R.layout.contentadapter, null);
        holder.journey=(TextView) convertView.findViewById(R.id.txtvjourney);
        holder.date=(TextView) convertView.findViewById(R.id.txtVDate);
        holder.time=(TextView)convertView.findViewById(R.id.ttVTime);
        holder.budget=(TextView)convertView.findViewById(R.id.txtVbudget);
        holder.journey.setText(journeyName.get(position));
        holder.date.setText(journeyDate.get(position));
        holder.time.setText(journeyTime.get(position));
        holder.budget.setText(journeybudget.get(position));
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Intent i = new Intent(context, contentRide.class);
                i.putExtra("from",from.get(position));i.putExtra("to",to.get(position));
                i.putExtra("date",journeyDate.get(position));i.putExtra("time",journeyTime.get(position));
                i.putExtra("budjet",journeybudget.get(position));i.putExtra("smoke",smoke.get(position));
                i.putExtra("music",music.get(position));i.putExtra("animal",animal.get(position));
                i.putExtra("comment",comment.get(position));i.putExtra("plate",car_plate.get(position));
                i.putExtra("type",car_type.get(position));i.putExtra("seats",seats.get(position));
                i.putExtra("user_id",user_id.get(position));
                context.startActivity(i);


            }
        });


        return convertView;
    }

} 