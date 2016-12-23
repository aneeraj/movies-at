package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread starttimer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                    Intent i = new Intent(splash.this,login.class);
                    startActivity(i);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        starttimer.start();
    }
}
