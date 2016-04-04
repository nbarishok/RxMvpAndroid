package com.onemanparty.rxmvpandroid.sandbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.onemanparty.rxmvpandroid.core.view.BaseActivity;
import com.onemanparty.rxmvpandroid.weather.view.CautionDialogData;

/**
 * Activity for pure testing purposes
 * Now it should show the input number in toolbar
 */
public class SandboxActivity extends BaseActivity {

    private static final String TAG = "SandboxActivity";

    public static void start(Context context, CautionDialogData data) {
        Intent starter = new Intent(context, SandboxActivity.class);
        starter.putExtra("DATA", (Parcelable) data);
        context.startActivity(starter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CautionDialogData data = getIntent().getParcelableExtra("DATA");
        getSupportActionBar().setTitle(String.valueOf(data.getCurrentTemp()));
    }

}
