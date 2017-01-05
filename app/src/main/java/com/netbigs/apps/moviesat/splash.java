package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (internet_connection()) {
            Thread starttimer = new Thread() {
                public void run() {
                    try {
                        sleep(5000);
                        Intent i = new Intent(splash.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            starttimer.start();
        }

    else
    {
        //create a snackbar telling the user there is no internet connection and issuing a chance to reconnect
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                "No internet connection.",
                Snackbar.LENGTH_SHORT);

        snackbar.setAction(R.string.try_again, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               snackbar.dismiss(); //recheck internet connection and call DownloadJson if there is internet
            }
        }).show();
    }
    }

    private boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
