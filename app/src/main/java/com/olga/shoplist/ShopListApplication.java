package com.olga.shoplist;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.olga.shoplist.dependencyinjection.ApplicationComponent;
import com.olga.shoplist.dependencyinjection.ApplicationModule;
import com.olga.shoplist.dependencyinjection.DaggerApplicationComponent;
import com.olga.shoplist.dependencyinjection.RoomModule;

public class ShopListApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}