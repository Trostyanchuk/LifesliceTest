package com.lifeslicetest.service.api_model;

import java.util.List;

public class ApiTagVideosResponse {

    private String code;

    private boolean success;

    private String error;

    private ApiTagData data;

    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public ApiTagData getData() {
        return data;
    }
}
