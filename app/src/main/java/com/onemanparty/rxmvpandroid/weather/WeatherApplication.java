package com.onemanparty.rxmvpandroid.weather;

import android.app.Application;
import android.content.Context;

/**
 * Application
 */
public class WeatherApplication extends Application {

	private AppComponent component;

	@Override
	public void onCreate() {
		super.onCreate();

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

	protected AppModule getApplicationModule() {
		return new AppModule(this);
	}

}
