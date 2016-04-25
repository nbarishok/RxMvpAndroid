package com.onemanparty.rxmvpandroid.core.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.onemanparty.rxmvpandroid.R;

public abstract class AbsViewPagerActivity extends AbsActivity {

    TabLayout tabs;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected final void setupViews() {
        super.setupViews();
        setupTabs();
    }

    private void setupTabs() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabs = (TabLayout) findViewById(R.id.tabs);

        viewPager.setAdapter(providePagerAdapter());
        tabs.setupWithViewPager(viewPager);
    }

    protected int getLayoutId() {
        return R.layout.base_tabs_activity;
    }

    protected abstract PagerAdapter providePagerAdapter();
}
