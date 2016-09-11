package com.lifeslicetest;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lifeslicetest.service.IBroadcast;
import com.lifeslicetest.service.IClientApi;
import com.lifeslicetest.service.IImageLoader;
import com.lifeslicetest.service.impl.EventBusBroadcastImpl;
import com.lifeslicetest.service.impl.PicassoImageLoader;
import com.lifeslicetest.service.impl.retrofit.RetrofitClientImpl;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public IBroadcast provideBroadcast() {
        return new EventBusBroadcastImpl(EventBus.getDefault());
    }


    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    public IClientApi provideClientApi(IBroadcast broadcast) {
        return new RetrofitClientImpl(context, broadcast);
    }

    @Provides
    public IImageLoader provideImageLoader() {
        return new PicassoImageLoader();
    }
}
