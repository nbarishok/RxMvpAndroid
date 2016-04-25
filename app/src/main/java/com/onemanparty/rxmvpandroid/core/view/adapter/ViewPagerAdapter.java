package com.onemanparty.rxmvpandroid.core.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.onemanparty.rxmvpandroid.core.utils.Tuple;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Tuple<String, Fragment>[] fragmentTitlePairs;

    public ViewPagerAdapter(FragmentManager fm, Tuple<String, Fragment>[] fragmentTitlePairs) {
        super(fm);
        this.fragmentTitlePairs = fragmentTitlePairs;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentTitlePairs[position].second;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitlePairs[position].first;
    }

    @Override
    public int getCount() {
        return fragmentTitlePairs.length;
    }
}
