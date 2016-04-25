package com.onemanparty.rxmvpandroid.view_pager;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;

import com.onemanparty.rxmvpandroid.core.utils.Tuple;
import com.onemanparty.rxmvpandroid.core.view.AbsViewPagerActivity;
import com.onemanparty.rxmvpandroid.core.view.adapter.ViewPagerAdapter;
import com.onemanparty.rxmvpandroid.weather.view.CautionDialogData;

import java.util.Random;

/**
 * Current WIP
 * Activity for real-world scenario: working with view-pager to show some more data
 */
public class ViewPagerActivity extends AbsViewPagerActivity {

    private static final String TAG = "ViewPagerActivity";

    public static void start(Context context, CautionDialogData data) {
        Intent starter = new Intent(context, ViewPagerActivity.class);
        starter.putExtra("DATA", (Parcelable) data);
        context.startActivity(starter);
    }

    @Override
    protected void setupToolbarImpl() {
        CautionDialogData data = getIntent().getParcelableExtra("DATA");
        setToolbarTitle("The Better Weather (" + String.valueOf(data.getCurrentTemp()) + " \u2103)");
    }

    @Override
    protected PagerAdapter providePagerAdapter() {

        CautionDialogData data = getIntent().getParcelableExtra("DATA");
        int currentTemp = data.getCurrentTemp();

        Random random = new Random();
        int i = random.nextInt(10);

        Tuple<String, Fragment>[] fragmentsWithTitles = new Tuple[i];
        for (int j = 0; j < fragmentsWithTitles.length; j++) {
            int somePseudoRandNumber = currentTemp + random.nextInt(100);
            fragmentsWithTitles[j] = new Tuple<>("I\'m " + somePseudoRandNumber, ViewPagerFragment.newInstance(somePseudoRandNumber));
        }

        return new ViewPagerAdapter(getSupportFragmentManager(), fragmentsWithTitles);
    }

}
