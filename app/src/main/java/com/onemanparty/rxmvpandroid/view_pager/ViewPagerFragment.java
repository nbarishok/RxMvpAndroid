package com.onemanparty.rxmvpandroid.view_pager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.onemanparty.rxmvpandroid.R;
import com.onemanparty.rxmvpandroid.base.ui.AbsButterFragment;

import butterknife.Bind;

public class ViewPagerFragment extends AbsButterFragment {

    private static final String KEY_NUMBER = "KEY_NUMBER";

    @Bind(R.id.view_pager_text)
    TextView text;

    public static ViewPagerFragment newInstance(int number) {
        Bundle args = new Bundle();
        args.putInt(KEY_NUMBER, number);
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupViews(View view) {
        super.setupViews(view);
        int numberToDisplay = getArguments().getInt(KEY_NUMBER);
        text.setText(String.valueOf(numberToDisplay));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_pager_item;
    }
}
