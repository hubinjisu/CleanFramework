package com.hubin.app.cleanframework.dagger.module;

import com.hubin.app.cleanframework.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hubin on 2017/5/20.
 */
@Module
public class AppModule {
    MainApplication mApplication;

    public AppModule(MainApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    MainApplication provideApplication() {
        return mApplication;
    }
}
