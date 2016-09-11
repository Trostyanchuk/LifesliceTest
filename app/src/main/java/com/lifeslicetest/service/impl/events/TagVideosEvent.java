package com.lifeslicetest.service.impl.events;

import com.lifeslicetest.model.Record;

import java.util.List;

public class TagVideosEvent {

    private final String errorMessage;

    private final List<Record> records;

    public TagVideosEvent(List<Record> records) {
        this.errorMessage = null;
        this.records = records;
    }

    public TagVideosEvent(String errorMessage) {
        this.errorMessage = errorMessage;
        this.records = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Record> getRecords() {
        return records;
    }
}
