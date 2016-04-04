package com.onemanparty.rxmvpandroid.weather.utils;

import android.content.Context;

import com.onemanparty.rxmvpandroid.core.utils.CompatUtils;
import com.onemanparty.rxmvpandroid.WeatherApplication;

import java.io.File;
import java.io.IOException;

public class PathManager {
    static final String TAG = "PathManager";

    private static final String LOG_DIR = "log/";
    private static final String CACHE_DIR = "cache/";
    private String resourcesDirectory;

    public PathManager(final Context context) {
        final String externalDir = "Android/data/" + WeatherApplication.app().getPackageName();
        resourcesDirectory = CompatUtils.getRootPath(context, externalDir);
    }

    public String getApplicationPath() {
        return getDir("");
    }

    public String getCachePath() {
        return getDir(CACHE_DIR);
    }

    public String getLogPath() {
        return getDir(LOG_DIR);
    }

    private String getDir(final String path) {
        final String dir = resourcesDirectory + "/" + path;
        try {
            createDirectory(dir);
        } catch(IOException e) {
        }
        return dir;
    }

    private void createDirectory(final String path) throws IOException {
        final File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("create directory failed");
            }
        }
    }
}
