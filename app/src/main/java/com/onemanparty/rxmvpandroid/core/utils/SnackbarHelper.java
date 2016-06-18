package com.onemanparty.rxmvpandroid.core.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarHelper {

    public static void show(final View view, final String text) {
        show(view, text, Snackbar.LENGTH_LONG);
    }

    public static void show(final View view, final String text, int durationMillis) {
        if (view != null) {
            Snackbar.make(view, text, durationMillis)
                    .setActionTextColor(Color.WHITE)
                    .show();
        }
    }
}