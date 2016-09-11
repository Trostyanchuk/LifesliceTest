package com.lifeslicetest.service.impl.retrofit;

import android.content.Context;

import com.lifeslicetest.R;

import java.io.IOException;

public final class RetrofitClientErrorParser {

    private Context context;

    private RetrofitClientErrorParser(Context context) {
        this.context = context;
    }

    public static RetrofitClientErrorParser create(Context context) {
        return new RetrofitClientErrorParser(context);
    }

    public String getLocalizedError(Throwable throwable) {
        RetrofitException responseException = (RetrofitException) throwable;
        RetrofitException.Type responseType = responseException.getType();
        switch (responseType) {
            case HTTP: {
//                try {
                return responseException.getMessage();
//                } catch (IOException e) {
//                    return context.getString(R.string.unexpected_error);
//                }
            }
            case NETWORK: {
                return context.getString(R.string.network_error);
            }
            case UNEXPECTED: {
                return context.getString(R.string.unexpected_error);
            }
            default: {
                return context.getString(R.string.unexpected_error);
            }
        }
    }
}
