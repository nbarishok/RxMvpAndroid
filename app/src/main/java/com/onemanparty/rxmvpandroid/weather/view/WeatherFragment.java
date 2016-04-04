package com.onemanparty.rxmvpandroid.weather.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onemanparty.lib.dialog.delegate.ConfirmDialogFragmentDelegate;
import com.onemanparty.rxmvpandroid.R;
import com.onemanparty.rxmvpandroid.core.persistence.ComponentManagerFragment;
import com.onemanparty.rxmvpandroid.WeatherApplication;
import com.onemanparty.rxmvpandroid.sandbox.SandboxActivity;
import com.onemanparty.rxmvpandroid.weather.presenter.WeatherPresenter;
import com.onemanparty.rxmvpandroid.weather.view.di.DaggerWeatherComponent;
import com.onemanparty.rxmvpandroid.weather.view.di.WeatherComponent;
import com.onemanparty.rxmvpandroid.weather.view.model.WeatherViewModel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class WeatherFragment extends ComponentManagerFragment<WeatherComponent, WeatherView> implements WeatherView {

    public static final String TAG = WeatherFragment.class.getSimpleName();

    @Inject
    WeatherPresenter presenter;

    @Bind(R.id.weather_tv_temp)
    TextView currentTemperature;

    @Bind(R.id.weather_btn_navigate_with_probable_show)
    Button navigationWithDialog;

    private WeatherViewModel weather;

    ConfirmDialogFragmentDelegate<CautionDialogData> mCautionDialogDelegate;

    private ConfirmDialogFragmentDelegate.OnConfirmWithDataDialogListener<CautionDialogData> listener = new ConfirmDialogFragmentDelegate.OnConfirmWithDataDialogListener<CautionDialogData>() {
        @Override
        public void onOkDialog(DialogFragment dialogFragment, CautionDialogData data) {
            SandboxActivity.start(getActivity(), data);
        }

        @Override
        public void onCancelDialog(DialogFragment dialogFragment, CautionDialogData data) {
            getComponent().getPresenter().someInsaneActionClicked();
        }
    };

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    protected WeatherComponent createComponent() {
        return DaggerWeatherComponent
                .builder()
                .appComponent(WeatherApplication.getAppComponent(getActivity()))
                .build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCautionDialogDelegate = new ConfirmDialogFragmentDelegate<>("caution_dialog", listener, R.string.weather_dialog_title, R.string.weather_dialog_subtitle, android.R.string.ok, android.R.string.cancel);
        mCautionDialogDelegate.onCreate(savedInstanceState, getActivity());
        getComponent().inject(this);
    }

    @Override
    public void setupViews(View view) {
        super.setupViews(view);
        getComponent().getPresenter().loadWeather();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCautionDialogDelegate.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        mCautionDialogDelegate.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.weather;
    }

    @Override
    public void showLoading() {
        currentTemperature.setText("loading");
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void setData(WeatherViewModel w) {
        Toast.makeText(getContext(), "updated with data", Toast.LENGTH_SHORT).show();
        weather = w;
    }

    @Override
    public void showContent() {
        currentTemperature.setText(getString(R.string.weather_tv_temperature, weather.getTemperature()));
    }

    @Override
    public void showError(WeatherError error) {
        currentTemperature.setText("error!");
    }

    @Override
    public void showCautionDialog(CautionDialogData data) {
        mCautionDialogDelegate.showDialog(data);
    }

    @OnClick(R.id.weather_btn_navigate_with_probable_show)
    public void propagateClick() {
        getComponent().getPresenter().someInsaneActionClicked();
    }
}
