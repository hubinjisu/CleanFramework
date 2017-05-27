package com.hubin.app.cleanframework.dagger.module;

import com.hubin.app.scm.services.github.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hubin on 2017/5/20.
 */
@Module
public class ServiceModule {

    public ServiceModule() {
    }

    @Provides
    @Singleton
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
