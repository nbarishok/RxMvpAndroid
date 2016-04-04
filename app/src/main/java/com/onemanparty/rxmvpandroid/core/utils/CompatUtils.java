package com.onemanparty.rxmvpandroid.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class CompatUtils {

    public static String getRootPath(Context context, String externalDir) {
        String result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                result = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !Environment.isExternalStorageRemovable() ?
                        context.getExternalFilesDir(null).getPath() :
                        context.getCacheDir().getPath();
            } catch (NullPointerException e) {
                result = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !Environment.isExternalStorageRemovable() ?
                        Environment.getExternalStorageDirectory().getPath() + File.separatorChar +
                                externalDir :
                        context.getCacheDir().getPath();
            }
        } else {
            result = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                    !Environment.isExternalStorageRemovable() ?
                    Environment.getExternalStorageDirectory().getPath() + File.separatorChar +
                            externalDir :
                    context.getCacheDir().getPath();
        }

        return result;
    }

}
