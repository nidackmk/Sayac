package com.example.uygulama2;

import android.content.Context;
import android.content.SharedPreferences;

public class SetupClass {

    Boolean altses;
    Boolean ustses;
    Boolean alttitresim;
    Boolean usttitresim;


    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static SetupClass setupClass=null;

    private SetupClass(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("setup", Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();

    }

    public static SetupClass getInstance(Context context){
        if (setupClass==null)
            setupClass=new SetupClass(context);
        return setupClass;
    }

    
}
