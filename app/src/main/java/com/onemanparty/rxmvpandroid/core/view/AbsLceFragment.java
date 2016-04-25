package com.onemanparty.rxmvpandroid.core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;

import com.onemanparty.rxmvpandroid.R;
import com.onemanparty.rxmvpandroid.core.persistence.ComponentManagerFragment;
import com.onemanparty.rxmvpandroid.core.persistence.HasPresenter;
import com.onemanparty.rxmvpandroid.core.utils.SnackbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Base load - content - error view
 * Handles CONNECTION, NO_DATA, GENERAL errors
 * To work properly, your view should be able to work with enum <code>XXXError</code> with corresponding fields
 */
public abstract class AbsLceFragment<C extends HasPresenter, M, E extends Enum<E>, V extends LceView<M, E>> extends ComponentManagerFragment<C, V> implements LceView<M, E> {

    @Bind(R.id.progress)
    ImageView progress;

    @Bind(R.id.no_connection_error_layout)
    View noConnection;

    @Bind(R.id.retry_no_connection_button)
    Button noConnectionButton;

    @Bind(R.id.no_loaded_error_layout)
    View noData;

    @Bind(R.id.retry_no_loaded_button)
    Button noDataButton;

    @Bind(R.id.lce_container_le)
    View containerLe;

    protected M data;
    private View contentRoot;

    private List<View> mViews;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void preSetupViews(final View view) {
        super.preSetupViews(view);
        ViewStub stub = (ViewStub) view.findViewById(R.id.content_placeholder);
        stub.setLayoutResource(getContentResId());
        contentRoot = stub.inflate();
    }

    @Override
    protected void setupViews(final View view) {
        super.setupViews(view);
        mViews = new ArrayList<>();
        mViews.add(noConnection);
        mViews.add(noData);
        mViews.add(progress);
        mViews.add(containerLe);

        setupNoConnection();
        setupLoading();
        setupNoData();
        hide(containerLe);
    }

    private void setupLoading() {
        hide(progress);
    }

    private void setupNoConnection() {
        hide(noConnection);
        noConnectionButton.setOnClickListener(v -> {
            loadData();
        });
    }

    private void setupNoData() {
        hide(noData);
        noDataButton.setOnClickListener(v -> {
            loadData();
        });
    }

    protected void showFullLoading() {
        getActivity().runOnUiThread(() -> {
            hide(noConnection);
            hide(noData);
            show(progress);
            show(containerLe);
        });
    }

    protected void showNoConnection() {
        getActivity().runOnUiThread(() -> {
            hide(progress);
            hide(noData);
            show(noConnection);
            show(containerLe);
        });
    }

    protected void showNoData() {
        getActivity().runOnUiThread(() -> {
            hide(noConnection);
            hide(progress);
            show(noData);
            show(containerLe);
        });
    }

    protected void hideFullLoading() {
        getActivity().runOnUiThread(() -> {
            hide(progress);
        });
    }

    protected void hideFullNoConnection() {
        getActivity().runOnUiThread(() -> {
            hide(noConnection);
        });
    }

    protected void hideFullNoData() {
        getActivity().runOnUiThread(() -> {
            hide(noData);
        });
    }

    protected void hideLeContainer() {
        getActivity().runOnUiThread(() -> hide(containerLe));
    }

    protected abstract int getContentResId();

    @Override
    protected int getLayoutId() {
        return R.layout.lce_view;
    }

    private void show(View v) {
        for (View view : mViews) {
            if (view == v) {
                getActivity().runOnUiThread(() -> view.setVisibility(View.VISIBLE));
                break;
            }
        }
    }

    private void hide(View v) {
        for (View view : mViews) {
            if (view == v) {
                getActivity().runOnUiThread(() -> view.setVisibility(View.GONE));
                break;
            }
        }
    }

    @Override
    public void showLoading() {
        if (!hasData()) {
            showFullLoading();
        }
    }

    protected final boolean hasData() {
        return data != null;
    }

    @Override
    public void hideLoading() {
        hideFullLoading();
    }

    @Override
    public void showContent() {
        hideLeContainer();
    }

    @Override
    public void showError(E e) {
        String name = e.name();
        switch (name) {
            case "CONNECTION":
                handleNoConnection();
                break;
            case "DATA":
            case "GENERAL":
                handleNoData();
                break;
            default:
                break;
        }
    }

    private void handleNoData() {
        if (!hasData()) {
            showNoData();
        } else {
            showSnackbarNoData();
        }
    }

    private void handleNoConnection() {
        if (!hasData()) {
            showNoConnection();
        } else {
            showSnackbarNoConnection();
        }
    }

    private void showSnackbarNoConnection() {
        SnackbarHelper.show(contentRoot, getString(R.string.error_title_no_connection));
    }

    private void showSnackbarNoData() {
        SnackbarHelper.show(contentRoot, getString(R.string.error_title_no_loaded));
    }

    @Override
    public void setData(M data) {
        this.data = data;
    }
}