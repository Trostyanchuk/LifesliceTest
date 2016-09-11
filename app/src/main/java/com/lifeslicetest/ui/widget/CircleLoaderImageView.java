package com.lifeslicetest.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lifeslicetest.ApplicationController;
import com.lifeslicetest.service.IImageLoader;

import javax.inject.Inject;

/**
 * This is a ImageView with attribute imageUri using data binding custom attribute setter
 */
@BindingMethods({
        @BindingMethod(type = CircleLoaderImageView.class,
                attribute = "app:imageUri",
                method = "loadImage"),
})
public class CircleLoaderImageView extends ImageView {

    @Inject
    IImageLoader imageLoader;

    public CircleLoaderImageView(Context context) {
        super(context);
        init();
    }

    public CircleLoaderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleLoaderImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleLoaderImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ApplicationController.getComponent().inject(this);
    }

    public void loadImage(String url) {
        if (!TextUtils.isEmpty(url)) {
            imageLoader.loadImage(getContext(), this, url);
        }
    }
}
