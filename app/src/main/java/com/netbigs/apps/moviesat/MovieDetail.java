package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MovieDetail extends Activity implements Serializable {

    int position;

    ImageView imageView;

    private ArrayList<GridItem> moviedata = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent i=getIntent();
        position = i.getExtras().getInt("id");
        moviedata= i.getParcelableArrayListExtra("array");
        System.out.println(position);
        imageView = (ImageView)findViewById(R.id.imView);

        Picasso.with(this).load(moviedata.get(position).getDrawableId()).into(imageView);
        TextView tvname = (TextView)findViewById(R.id.tvmvname);
        TextView tvdate = (TextView)findViewById(R.id.tvmvdate);
        TextView tvinfo = (TextView)findViewById(R.id.tvmvinfo);
        tvname.setText(moviedata.get(position).getName());
        tvdate.setText(moviedata.get(position).getRdate());
        tvinfo.setText(moviedata.get(position).getMinfo());
    }


}
