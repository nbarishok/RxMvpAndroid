// Copyright (c) ABBYY (BIT Software), 1993 - 2014. All rights reserved.
// Автор: Телегин Валентин

package com.onemanparty.rxmvpandroid.core.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Класс для работы со snackbar
 */
public class SnackbarHelper {

    /**
     * Показать snackbar без action
     *
     * @param view     родительская view
     * @param text     текст
     */
    public static void show(final View view, final String text) {
        show(view, text, Snackbar.LENGTH_LONG);
    }

    /**
     * Показать snackbar без action
     *
     * @param view     родительская view
     * @param text     текст
     * @param durationMillis длина показа, в миллисекундах
     */
    public static void show(final View view, final String text, int durationMillis) {
        if (view != null) {
            Snackbar.make(view, text, durationMillis)
                    .setActionTextColor(Color.WHITE)
                    .show();
        }
    }
}