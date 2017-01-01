package com.netbigs.apps.moviesat;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrailerFragment extends Fragment {

    private ListView listview;
    private ArrayList<ListItem> imageid = new ArrayList<>();
    String mJSON,trailername,trailerid,imagetrailer,trailervideo;
    public static final String showUrl = "http://netbigs.com/apps/fetchtrailer.php";
    private traileradapter trail;
    JSONArray trailers = null;
    private static final String TAG_ID = "tid";
    private static final String TAG_TNAME = "tname";
    private static final String TAG_TIMG = "timg";
    private static final String TAG_TVIDEO = "tvideo";
    private static final String TAG_RESUL = "result";



    public TrailerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trailer, container, false);

        trail = new traileradapter(getContext(),R.layout.trailerlist,imageid);

        listview = (ListView) view.findViewById(R.id.trailer_list);
        listview.setAdapter(trail);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"You Clicked ",Toast.LENGTH_SHORT).show();
            }
        });

        getData();

        return view;

    }

    protected void showList(){


        try{
            JSONObject jsonObj = new JSONObject(mJSON);
            trailers = jsonObj.getJSONArray(TAG_RESUL);

            for (int i=0;i<trailers.length();i++){
                JSONObject c = trailers.getJSONObject(i);
                trailerid = c.getString(TAG_ID);
                trailername = c.getString(TAG_TNAME);

                imagetrailer = c.getString(TAG_TIMG);

                trailervideo = c.getString(TAG_TVIDEO);

                try {
                    ListItem item;
                    item = new ListItem();
                    item.setDrawableId(imagetrailer);
                    item.setName(trailername);

                    imageid.add(item);

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
                    String ur = showUrl;
                    URL url = new URL(ur);
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
                mJSON=result;
                showList();

                trail.setTrailerData(imageid);

            }

        }
        GetDataJSON g = new GetDataJSON();
        g.execute();


    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
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
