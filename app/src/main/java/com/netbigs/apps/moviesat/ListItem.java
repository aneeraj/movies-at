package com.netbigs.apps.moviesat;

public class ListItem {
    private String trailername;
    private String imagetrailer;


    public ListItem() {
        super();
    }

    public String getName() {
        return trailername;
    }

    public void setName(String trailername) {
        this.trailername = trailername;
    }

    public String getDrawableId() {
        return imagetrailer;
    }

    public void setDrawableId(String imagetrailer) {
        this.imagetrailer = imagetrailer;
    }
}
