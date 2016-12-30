package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MovieDetail extends Activity {

    public static final String showUrl = "http://netbigs.com/apps/fetch.php";
    String myJSON;
    String mvname;
    String mvinfo;
    String rdate,imglink;
    ImageView imageView;
    Bitmap myBitmap;
    URL urii;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "mvid";
    private static final String TAG_NAME = "mvname";
    private static final String TAG_IMG = "imglink";
    private static final String TAG_DATE = "rdate";
    private static final String TAG_MOVINF = "mvinfo";

    JSONArray movies = null;

    ArrayList<HashMap<String,String>> movieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        getData();

    }

    protected void showList(){


        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            movies = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i=0;i<movies.length();i++){
                JSONObject c = movies.getJSONObject(i);
                String mvid = c.getString(TAG_ID);
                mvname = c.getString(TAG_NAME);

                imglink = c.getString(TAG_IMG);
                System.out.println(imglink);
                rdate = c.getString(TAG_DATE);
                mvinfo = c.getString(TAG_MOVINF);
             try {
                 urii = new URL(imglink);
                 HttpURLConnection connection = (HttpURLConnection) urii.openConnection();
                 connection.setDoInput(true);
                 connection.connect();
                 InputStream in = connection.getInputStream();
                 myBitmap = BitmapFactory.decodeStream(in);


             }
catch (Exception e){
    e.printStackTrace();
}
            }
            imageView = (ImageView)findViewById(R.id.imView);
            Picasso.with(this).load(imglink).into(imageView);
            TextView tvname = (TextView)findViewById(R.id.tvmvname);
            TextView tvdate = (TextView)findViewById(R.id.tvmvdate);
            TextView tvinfo = (TextView)findViewById(R.id.tvmvinfo);
            tvname.setText(mvname);
            tvdate.setText(rdate);
            tvinfo.setText(mvinfo);


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
                    String uri = showUrl;
                       URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
                myJSON=result;
                showList();
                }
            }
        GetDataJSON g = new GetDataJSON();
        g.execute();
        }


}
