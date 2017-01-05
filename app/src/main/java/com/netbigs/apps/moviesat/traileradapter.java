package com.netbigs.apps.moviesat;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class traileradapter extends ArrayAdapter<ListItem> {

    private Context mContext;
    private int layoutResourceId;

    private ArrayList<ListItem> mTrailerData = new ArrayList<ListItem>();

    public traileradapter(Context mContext,int layoutResourceId,ArrayList<ListItem>mTrailerData) {
        super(mContext,layoutResourceId,mTrailerData);
        this.layoutResourceId=layoutResourceId;
        this.mContext=mContext;
        this.mTrailerData=mTrailerData;

    }

    public void setTrailerData(ArrayList<ListItem> mTrailerData) {
        this.mTrailerData = mTrailerData;
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
            holder.titleTextView = (TextView) v.findViewById(R.id.textvi);
            holder.imageView = (ImageView) v.findViewById(R.id.imagetrailerview);
            v.setTag(holder);
        }else {
            holder = (ViewHolder) v.getTag();
        }


       ListItem item = mTrailerData.get(i);

        holder.titleTextView.setText(item.getName());
        System.out.println(item.getName());
        Picasso.with(mContext).load(item.getDrawableId()).into(holder.imageView);


        System.out.println(item.getName());
        return  v;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }
}