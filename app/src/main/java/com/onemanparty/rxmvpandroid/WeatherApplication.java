package com.onemanparty.rxmvpandroid;

import android.app.Application;
import android.content.Context;

public class WeatherApplication extends Application {

	private AppComponent component;

	private static WeatherApplication instance;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static AppComponent getAppComponent(Context context) {
		WeatherApplication app = (WeatherApplication)context.getApplicationContext();
		if (app.component == null) {
			app.component = DaggerAppComponent.builder()
					.appModule(app.getApplicationModule())
					.build();
		}
		return app.component;
	}

	public static WeatherApplication app() {
		return instance;
	}

	protected AppModule getApplicationModule() {
		return new AppModule(this);
	}

}
