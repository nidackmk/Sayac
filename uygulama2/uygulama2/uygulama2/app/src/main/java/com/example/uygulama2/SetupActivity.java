package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {


    Button alt_limit, ust_limit, alt_arti, alt_eksi;
    int ust_deger=20;
    int alt_deger=5;
    EditText deger, altdeger;
    CheckBox ust_ses;
    CheckBox ust_titresim;
    CheckBox alt_ses;
    CheckBox alt_titresim;
    MediaPlayer mediaPlayer=null;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        alt_limit=(Button) findViewById(R.id.alt_limit);
        ust_limit=(Button) findViewById(R.id.ust_limit);
        alt_arti=(Button) findViewById(R.id.alt_arti);
        alt_eksi=(Button) findViewById(R.id.alt_eksi);
        deger=(EditText) findViewById(R.id.deger);
        altdeger=(EditText) findViewById(R.id.alt_deger);
        ust_ses=(CheckBox) findViewById(R.id.ust_ses);
        alt_ses=(CheckBox) findViewById(R.id.alt_ses);
        alt_titresim=(CheckBox) findViewById(R.id.alt_titresim);
        ust_titresim=(CheckBox) findViewById(R.id.ust_titresim);


        Context context= getApplicationContext();
        sharedPreferences=context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();



        deger.setText(String.valueOf(ust_deger));
        altdeger.setText(String.valueOf(alt_deger));

        ust_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ust_deger++;
                deger.setText(String.valueOf(ust_deger));
            }
        });

        alt_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ust_deger--;
                deger.setText(String.valueOf(ust_deger));
            }
        });





        alt_arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alt_deger++;
                altdeger.setText(String.valueOf(alt_deger));
            }
        });

        alt_eksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alt_deger--;
                altdeger.setText(String.valueOf(alt_deger));
            }
        });




    }


    @Override
    protected void onPause() {
        super.onPause();
        editor.putInt("ustLimit", ust_deger);
        editor.putInt("altdeger", alt_deger);
        editor.commit();
    }

    public void alertSound(){
        mediaPlayer.start();
    }
}