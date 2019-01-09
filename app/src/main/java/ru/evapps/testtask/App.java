package ru.evapps.testtask;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import ru.evapps.testtask.di.AppComponent;
import ru.evapps.testtask.di.DaggerAppComponent;
import ru.evapps.testtask.di.modules.ContextModule;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
