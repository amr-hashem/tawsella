package com.example.amr.consort;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


/**
 * Created by amr on 2/11/16.
 */
public class saveSetting {
    Context context;
    SharedPreferences sharedPreferences;
    public static final String mypreferences="myprefs3";
    public static String userId="0";
    public saveSetting(Context context){
      this.context=context;
        sharedPreferences=context.getSharedPreferences(mypreferences,Context.MODE_PRIVATE);

    }
    public void saveData(){
        try{
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("id",String.valueOf(userId));
            editor.commit();
            loadData();
        }catch(Exception e){

        }
    }
    public void loadData(){
        String tempUserID=sharedPreferences.getString("id","empty");
        if(!tempUserID.equals("empty")){
            userId=tempUserID;

        }else{
            Intent intent=new Intent(context, com.example.amr.consort.login.class);
            context.startActivity(intent);
        }
    }
}


