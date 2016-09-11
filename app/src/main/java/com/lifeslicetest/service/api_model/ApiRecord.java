package com.lifeslicetest.service.api_model;

import com.google.gson.annotations.SerializedName;

public class ApiRecord {

    private String videoLowURL;

    @SerializedName("username")
    private String userName;

    private String thumbnailUrl;


    public String getVideoLowURL() {
        return videoLowURL;
    }

    public String getUserName() {
        return userName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
