package com.hubin.app.cleanframework;

import android.app.Application;

import com.hubin.app.cleanframework.dagger.component.DaggerServiceComponent;
import com.hubin.app.cleanframework.dagger.component.ServiceComponent;
import com.hubin.app.cleanframework.dagger.module.AppModule;
import com.hubin.app.cleanframework.dagger.module.ServiceModule;

/**
 * Created by hubin on 2017/5/20.
 */

public class MainApplication extends Application {
    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        serviceComponent = DaggerServiceComponent.builder()
                .appModule(new AppModule(this))
                .serviceModule(new ServiceModule("https://api.github.com/"))
                .build();
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
