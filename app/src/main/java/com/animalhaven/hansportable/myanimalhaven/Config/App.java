package com.animalhaven.hansportable.myanimalhaven.Config;

import android.app.Application;
import android.content.Context;

/**
 * Created by Admin on 2016/05/08.
 */
public class App extends Application{

    public static Context context;

    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static synchronized App getInstance() {
        return singleton;
    }

}
