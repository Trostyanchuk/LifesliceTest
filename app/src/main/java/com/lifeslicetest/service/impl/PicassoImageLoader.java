package com.lifeslicetest.service.impl;

import android.content.Context;
import android.widget.ImageView;

import com.lifeslicetest.R;
import com.lifeslicetest.service.IImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PicassoImageLoader implements IImageLoader {

    @Override
    public void loadImage(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(new File(url))
                .placeholder(R.drawable.ic_sentiment_very_satisfied_black_48dp)
                .into(imageView);
    }
}
