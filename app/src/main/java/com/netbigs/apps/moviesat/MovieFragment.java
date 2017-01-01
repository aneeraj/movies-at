package com.netbigs.apps.moviesat;


import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    public static final String showUr = "http://netbigs.com/apps/fetch.php";
    String myJSON;
    public String mvname;
    String mvinfo;
    private GridView mGridView;
    public String rdate,imglink;
    private ProgressBar mProgressBar;

    private ArrayList<GridItem> movie = new ArrayList<>();
    private GridAdapter mGridAdapter;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "mvid";
    private static final String TAG_NAME = "mvname";
    private static final String TAG_IMG = "imglink";
    private static final String TAG_DATE = "rdate";
    private static final String TAG_MOVINF = "mvinfo";
    JSONArray movies = null;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mGridView = (GridView) view.findViewById(R.id.gridview);

        mGridAdapter = new GridAdapter(getContext(),R.layout.grid_item,movie);
        mGridView.setAdapter(mGridAdapter);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){

                    case 0:

                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        i.putExtra("MovieName",mvname);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;



                }


            }
        });
        getData();
        return view;


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

                rdate = c.getString(TAG_DATE);
                mvinfo = c.getString(TAG_MOVINF);
                try {
                GridItem item;
                    item = new GridItem();
                    item.setDrawableId(imglink);
                    item.setName(mvname);

                    movie.add(item);

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

                    mGridAdapter.setGridData(movie);

            }

        }
        GetDataJSON g = new GetDataJSON();
        g.execute();


    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }
}
