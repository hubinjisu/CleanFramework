package com.hubin.app.scm.services.github;

import android.database.Observable;

import com.hubin.app.scm.models.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hubin on 2017/5/20.
 */

public interface GithubService {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);

//    @GET("/repos/{owner}/{repo}/contributors")
//    Observable<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/users/{user}/starred")
    Observable<List<GitHubRepo>> starred(@Path("user") String user);
}
