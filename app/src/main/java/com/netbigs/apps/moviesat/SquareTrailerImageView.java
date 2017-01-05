package com.netbigs.apps.moviesat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by neerajathamkavil on 22/12/16.
 */

public class SquareTrailerImageView extends ImageView {

    public SquareTrailerImageView(Context context) {
        super(context);
    }

    public SquareTrailerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareTrailerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), 513); //Snap to width
    }
}