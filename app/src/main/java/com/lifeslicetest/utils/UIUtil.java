package com.lifeslicetest.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.HashMap;

public final class UIUtil {

    private static HashMap<String, Typeface> typefaces = new HashMap<>();

    /**
     * Method for getting typeface from font file
     *
     * @param context      Current context
     * @param typefaceName Path to specific font file in assets
     * @return specific typeface
     */
    public static Typeface getTypeface(Context context, String typefaceName) {
        Typeface requiredTypeface = typefaces.get(typefaceName);
        if (requiredTypeface == null) {
            requiredTypeface = Typeface.createFromAsset(context.getAssets(), typefaceName);
            typefaces.put(typefaceName, requiredTypeface);
        }
        return requiredTypeface;
    }

    /**
     * Method for hiding keyboard if currently visible
     *
     * @param activity Current Activity where keyboard should be closed
     */
    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void openKeyboard(Activity activity, View v) {
        InputMethodManager imm = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }
}
