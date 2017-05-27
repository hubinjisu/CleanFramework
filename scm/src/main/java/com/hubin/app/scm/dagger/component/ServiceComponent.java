package com.hubin.app.scm.dagger.component;

import com.hubin.app.scm.dagger.module.HttpServiceModule;
import com.hubin.app.scm.services.github.GithubService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hubin on 2017/5/20.
 */
@Singleton
@Component(modules = {HttpServiceModule.class})
public interface ServiceComponent {
    void inject(GithubService githubService);
}
