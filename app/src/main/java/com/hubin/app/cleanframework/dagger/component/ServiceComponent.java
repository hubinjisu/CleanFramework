package com.hubin.app.cleanframework.dagger.component;

import com.hubin.app.cleanframework.dagger.module.AppModule;
import com.hubin.app.cleanframework.dagger.module.ServiceModule;
import com.hubin.app.cleanframework.ui.MainActivity;
import com.hubin.app.scm.dagger.module.HttpServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hubin on 2017/5/20.
 */
@Singleton
@Component(modules = {AppModule.class, ServiceModule.class, HttpServiceModule.class})
public interface ServiceComponent {
    void inject(MainActivity activity);

}
