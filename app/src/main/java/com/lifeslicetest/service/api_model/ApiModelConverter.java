package com.lifeslicetest.service.api_model;

import com.lifeslicetest.model.Record;

public final class ApiModelConverter {

    public static Record getRecordFromApiRecord(ApiRecord apiRecord) {
        return new Record()
                .setUserName(apiRecord.getUserName())
                .setThumbnailUrl(apiRecord.getThumbnailUrl())
                .setVideoLowURL(apiRecord.getVideoLowURL());
    }
}
