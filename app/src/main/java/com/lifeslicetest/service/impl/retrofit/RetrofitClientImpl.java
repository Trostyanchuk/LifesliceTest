package com.lifeslicetest.service.impl.retrofit;

import android.content.Context;
import android.util.Log;

import com.lifeslicetest.model.Record;
import com.lifeslicetest.service.IBroadcast;
import com.lifeslicetest.service.IClientApi;
import com.lifeslicetest.service.api_model.ApiModelConverter;
import com.lifeslicetest.service.api_model.ApiRecord;
import com.lifeslicetest.service.api_model.ApiTagVideosResponse;
import com.lifeslicetest.service.impl.events.TagVideosEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RetrofitClientImpl implements IClientApi {

    private Retrofit retrofitClient;
    private RetrofitClientService service;

    private Context context;
    private IBroadcast broadcast;
    private RetrofitClientErrorParser errorParser;

    @Inject
    public RetrofitClientImpl(Context context, IBroadcast broadcast) {
        this.context = context;
        retrofitClient = new Retrofit.Builder()
                .baseUrl(RetrofitClientService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
        service = retrofitClient.create(RetrofitClientService.class);

        this.broadcast = broadcast;
        this.errorParser = RetrofitClientErrorParser.create(context);
    }


    @Override
    public void getVideosByTag(String tag, int page) {
        service.getVideosByTag(tag, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ApiTagVideosResponse, TagVideosEvent>() {
                    @Override
                    public TagVideosEvent call(ApiTagVideosResponse apiTagVideosResponse) {
                        List<Record> records = new ArrayList<>();
                        if (apiTagVideosResponse.isSuccess() && apiTagVideosResponse.getData() != null &&
                                apiTagVideosResponse.getData().getRecords() != null) {
                            for (ApiRecord apiRecord : apiTagVideosResponse.getData().getRecords()) {
                                records.add(ApiModelConverter.getRecordFromApiRecord(apiRecord));
                            }
                        } else {
                            return new TagVideosEvent(apiTagVideosResponse.getError());
                        }
                        return new TagVideosEvent(records);
                    }
                })
                .subscribe(new Action1<TagVideosEvent>() {
                    @Override
                    public void call(TagVideosEvent tagVideosEvent) {
                        broadcast.postEvent(tagVideosEvent);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        broadcast.postEvent(new TagVideosEvent(errorParser.getLocalizedError(throwable)));
                    }
                });
    }
}
