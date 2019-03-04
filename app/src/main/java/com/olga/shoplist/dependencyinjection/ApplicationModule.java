package com.olga.shoplist.dependencyinjection;

import android.app.Application;

import com.olga.shoplist.ShopListApplication;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private final ShopListApplication application;

    public ApplicationModule(final ShopListApplication application) {
        this.application = application;
    }

    @Provides
    ShopListApplication provideRoomDemoApplication() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    RoomModule provideRoomModule() {
        return new RoomModule(provideApplication());
    }
}
