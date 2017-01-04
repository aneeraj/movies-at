package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovieDetail extends Activity implements Serializable {

    int position;

    ImageView imageView;

    private ArrayList<GridItem> moviedata = new ArrayList<>();

    public static final String showUr = "http://netbigs.com/apps/fetchdetail.php";
    String myJS;
    String theatrename,showtim,paramValue;
    TheatreList item;
    private ArrayList<TheatreList> theatres = new ArrayList<>();
    private ListCustomAdapter listCustomAdapter;
    private NonScrollListView mListView;
    private static final String TAG_RESULTS="result";
    private static final String TAG_THNAME="theatr";
    private static final String TAG_SHOW="shows";
    JSONArray showslist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent i=getIntent();
        position = i.getExtras().getInt("id");
        moviedata= i.getParcelableArrayListExtra("array");


        mListView = (NonScrollListView) findViewById(R.id.theatrelist);
        listCustomAdapter = new ListCustomAdapter(this,R.layout.theatre_list,theatres);
        mListView.setAdapter(listCustomAdapter);

        imageView = (ImageView)findViewById(R.id.imView);

        Picasso.with(this).load(moviedata.get(position).getDrawableId()).into(imageView);
        TextView tvname = (TextView)findViewById(R.id.tvmvname);
        TextView tvdate = (TextView)findViewById(R.id.tvmvdate);
        TextView tvinfo = (TextView)findViewById(R.id.tvmvinfo);
        tvname.setText(moviedata.get(position).getName());
        tvdate.setText(moviedata.get(position).getRdate());
        tvinfo.setText(moviedata.get(position).getMinfo());
        paramValue=moviedata.get(position).getName();
        getData();


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
         if(ev.getAction()==MotionEvent.ACTION_MOVE)
             return true;
        return super.dispatchTouchEvent(ev);
    }

    protected void showList(){


        try{
            JSONObject jsonObj = new JSONObject(myJS);
            showslist = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i=0;i<showslist.length();i++){
                JSONObject c = showslist.getJSONObject(i);
                theatrename = c.getString(TAG_THNAME);
                System.out.println(theatrename);
                showtim = c.getString(TAG_SHOW);
                try {

                    item = new TheatreList();

                    item.setTheatrename(theatrename);
                    item.setShows(showtim);
                    theatres.add(item);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }




        }
        catch (JSONException e){
            e.printStackTrace();
        }



    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                try {
                    String uri = showUr;
                    URL url = new URL(uri);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("film",paramValue);
                    String query = builder.build().getEncodedQuery();

                    OutputStream os = con.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();

                    con.connect();
                    InputStream inputStream = null;
                    String result = null;
                    inputStream = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return null;

                }


            }
            @Override
            protected void onPostExecute(String result){
                myJS=result;
                showList();

                listCustomAdapter.setListData(theatres);

            }

        }
        GetDataJSON g = new GetDataJSON();
        g.execute();


    }



}
