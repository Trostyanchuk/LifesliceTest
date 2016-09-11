package com.lifeslicetest.model;

public class Record {

    private String videoLowURL;

    private String userName;

    private String thumbnailUrl;

    public String getVideoLowURL() {
        return videoLowURL;
    }

    public Record setVideoLowURL(String videoLowURL) {
        this.videoLowURL = videoLowURL;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Record setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Record setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }
}
