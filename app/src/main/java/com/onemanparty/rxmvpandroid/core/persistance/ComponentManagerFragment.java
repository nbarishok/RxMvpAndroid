package com.onemanparty.rxmvpandroid.core.persistance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.onemanparty.rxmvpandroid.core.persistance.holder.ComponentHelper;
import com.onemanparty.rxmvpandroid.core.view.BaseFragment;

/**
 * Base fragment that abstracts away presenter persistence management
 * Presenter can be found in dagger component for fragment, returned by {@code getComponent()}
 *
 * One thing to do is to implement {@code createComponent()} method to build object graph for fragment
 */
public abstract class ComponentManagerFragment<C extends HasPresenter, V> extends BaseFragment {

    /**
     *  Helper object that contains all the logic to manage object graph state
     */
    private ComponentHelper<C, V> mComponentHelper = new ComponentHelper<>();

    /**
     * Factory object, provides instance of object graph for fragment
     */
    private ComponentCreator<C> mCreator = this::createComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponentHelper.onCreate(savedInstanceState, getArguments(), mCreator);
    }

    @Override
    protected void setupViews(View view) {
        super.setupViews(view);
        mComponentHelper.attachView((V)this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        mComponentHelper.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mComponentHelper.onDestroyView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mComponentHelper.onDestroy(this);
    }

    /**
     * Get object graph for the fragment
     * @return object graph
     */
    public C getComponent() {
        return mComponentHelper.getComponent();
    }

    /**
     * Create an instance of object graph for fragment
     * @return instance of object graph
     */
    protected abstract C createComponent();
}
