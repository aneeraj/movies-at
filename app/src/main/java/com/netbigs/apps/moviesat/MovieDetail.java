package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
    TextView []tv= new TextView[10];

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
        TextView tv0 = (TextView)findViewById(R.id.tvmvname);
        TextView tv1 = (TextView)findViewById(R.id.tvmvdate);
        TextView tv2 = (TextView)findViewById(R.id.tvmvinfo);
        getData();

    }

    protected void showList(){


        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            movies = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i=0;i<movies.length();i++){
                JSONObject c = movies.getJSONObject(i);
                String mvid = c.getString(TAG_ID);
                String mvname = c.getString(TAG_NAME);
                System.out.println(TAG_NAME);
                String imglink = c.getString(TAG_IMG);
                String rdate = c.getString(TAG_DATE);
                String mvinfo = c.getString(TAG_MOVINF);

               HashMap<String,String> movies = new HashMap<String,String>();
                movies.put(TAG_ID,mvid);
                movies.put(TAG_NAME,mvname);
                movies.put(TAG_IMG,imglink);
                movies.put(TAG_DATE,rdate);
                movies.put(TAG_MOVINF,mvinfo);

                movieList.add(movies);


            }
            for(int j=0;j<movieList.size();j++){
          tv[j].setText((CharSequence) movieList.get(j));


        }}
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
