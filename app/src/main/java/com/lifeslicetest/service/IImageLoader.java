package com.lifeslicetest.service;

import android.content.Context;
import android.widget.ImageView;

public interface IImageLoader {

    void loadImage(Context context, ImageView imageView, String url);
}
