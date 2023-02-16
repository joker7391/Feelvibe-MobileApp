package com.example.feelvibe_mobileapp;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }
    public void setMyData(String number){
        SharedPreferences.Editor editor = context.getSharedPreferences("mydata", Context.MODE_PRIVATE).edit();
        editor.putString("number",number );
        editor.apply();
    }
    public String getMyNumber(){
        SharedPreferences editor = context.getSharedPreferences("mydata", context.MODE_PRIVATE);
        return editor.getString("number", null);

    }
}
