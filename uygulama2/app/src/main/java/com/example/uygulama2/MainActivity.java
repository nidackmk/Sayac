package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button arti, eksi, ayarlar;
    TextView sonuc;
    int ust_limit=20;
    int altdeger=5;
    int sayac=altdeger;
    Vibrator vibrator;
    CheckBox ust_ses;
    CheckBox ust_titresim;
    CheckBox alt_ses;
    CheckBox alt_titresim;
    MediaPlayer mediaPlayer=null;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Uygulama2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arti= (Button) findViewById(R.id.arti);
        eksi= (Button) findViewById(R.id.eksi);
        sonuc= (TextView) findViewById(R.id.sonuc);
        ayarlar= (Button) findViewById(R.id.ayarlar);
        ust_ses=(CheckBox) findViewById(R.id.ust_ses);
        alt_ses=(CheckBox) findViewById(R.id.alt_ses);
        alt_titresim=(CheckBox) findViewById(R.id.alt_titresim);
        ust_titresim=(CheckBox) findViewById(R.id.ust_titresim);

        Context context= getApplicationContext();
        sharedPreferences=context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sayac==ust_limit){
                    alertvib();
                    alertSound();

                }
                if (sayac<ust_limit) {
                    sayac++;
                }
                sonuc.setText(String.valueOf(sayac));
            }
        });

        eksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sayac>altdeger)
                    sayac--;
                sonuc.setText(String.valueOf(sayac));
            }
        });

        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i= new Intent(MainActivity.this, SetupActivity.class);
              startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ust_limit=sharedPreferences.getInt("ust_deger", 20);
        altdeger=sharedPreferences.getInt("alt_deger", 5);
    }

    public void alertvib()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    public void alertSound(){
        mediaPlayer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            if(sayac>altdeger)
                sayac=sayac-5;
            sonuc.setText(String.valueOf(sayac));
        }
        return true;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            if(sayac<ust_limit)
                sayac=sayac+5;
            sonuc.setText(String.valueOf(sayac));
        }
        return true;
    }
}