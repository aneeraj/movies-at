package com.netbigs.apps.moviesat;

import android.os.Parcel;
import android.os.Parcelable;

public class GridItem implements Parcelable {
    private String name;
    private String drawableId;
    private String date;
    private String info;

    public GridItem() {
        super();
    }

    public String getName() {
        return name;
    }
    public String getRdate(){
        return date;
    }
    public String getMinfo(){
        return info;
    }

    public void setRdate(String date){ this.date=date;}
    public void setName(String name) {
        this.name = name;
    }
    public void setMinfo(String info){this.info = info;}
    public String getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(String drawableId) {
        this.drawableId = drawableId;
    }


    public static final Parcelable.Creator<GridItem> CREATOR = new Parcelable.Creator<GridItem>() {
        public GridItem createFromParcel(Parcel in) {
            return new GridItem(in);
        }

        public GridItem[] newArray(int size) {
            return new GridItem[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(drawableId);
        dest.writeString(date);
        dest.writeString(info);

    }
    private GridItem(Parcel in) {
        name = in.readString();
        drawableId = in.readString();
        date = in.readString();
        info = in.readString();
    }
}