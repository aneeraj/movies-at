package com.netbigs.apps.moviesat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neerajathamkavil on 22/12/16.
 */

public final class GridAdapter extends BaseAdapter {

    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public GridAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Pulimurugan",       R.drawable.puli));
        mItems.add(new Item("Dangal", R.drawable.dang));
        mItems.add(new Item("Kattappanayile Rithwik Roshan",   R.drawable.katta));
        mItems.add(new Item("Ore Mukham",      R.drawable.ore));
        mItems.add(new Item("Moana",     R.drawable.mo));
        mItems.add(new Item("Dear Zindagi",      R.drawable.zin));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}