package com.lifeslicetest.service.impl.retrofit;

import com.lifeslicetest.service.api_model.ApiTagVideosResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitClientService {

    String BASE_URL = "https://api.vineapp.com/";

    @GET("timelines/tags/{tag}")
    Observable<ApiTagVideosResponse> getVideosByTag(@Path("tag") String tag, @Query("page_id") int pageId);

}
