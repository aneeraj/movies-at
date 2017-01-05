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


public class ListCustomAdapter extends ArrayAdapter<TheatreList>{

    private Context mContext;
    private int layoutResourceId;

    public ArrayList<TheatreList> showtimes = new ArrayList<TheatreList>();

    public ListCustomAdapter(Context mContext, int layoutResourceId, ArrayList<TheatreList>showtimes) {
        super(mContext,layoutResourceId,showtimes);
        this.layoutResourceId=layoutResourceId;
        this.mContext=mContext;
        this.showtimes=showtimes;

    }

    public void setListData(ArrayList<TheatreList> showtimes) {
        this.showtimes = showtimes;
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
            holder.theatreTextView = (TextView) v.findViewById(R.id.tvtheatrename);
            holder.showsTextView = (TextView) v.findViewById(R.id.tvshows);
            v.setTag(holder);
        }else {
            holder = (ViewHolder) v.getTag();
        }



    TheatreList item = showtimes.get(i);

        holder.theatreTextView.setText(item.getTheatrename());

        holder.showsTextView.setText(item.getShows());


        return v;
    }


    static class ViewHolder {
        TextView theatreTextView;
        TextView showsTextView;
    }



}
