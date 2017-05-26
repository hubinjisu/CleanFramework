package com.hubin.app.scm.dagger.component;

import com.hubin.app.scm.dagger.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hubin on 2017/5/20.
 */
@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
//    void inject(GithubService githubService, CallServiceImpl callService);

}
