package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import java.util.List;


public class GridAdapter extends ArrayAdapter<GridItem> {



    private Context mContext;
    private int layoutResourceId;

    public ArrayList<GridItem> mGridData = new ArrayList<GridItem>();



    public GridAdapter(Context mContext,int layoutResourceId,ArrayList<GridItem>mGridData) {
        super(mContext,layoutResourceId,mGridData);
        this.layoutResourceId=layoutResourceId;
        this.mContext=mContext;
        this.mGridData=mGridData;

    }

    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ViewHolder holder;



        if (v == null) {

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            v = inflater.inflate(layoutResourceId, viewGroup, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) v.findViewById(R.id.text);
            holder.imageView = (ImageView) v.findViewById(R.id.picture);
            v.setTag(holder);
        }else {
            holder = (ViewHolder) v.getTag();
        }


        GridItem item = mGridData.get(i);

        holder.titleTextView.setText(item.getName());

        Picasso.with(mContext).load(item.getDrawableId()).into(holder.imageView);



        return v;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }


}