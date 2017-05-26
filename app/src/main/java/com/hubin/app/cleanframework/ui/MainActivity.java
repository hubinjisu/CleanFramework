package com.hubin.app.cleanframework.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hubin.app.cleanframework.MainApplication;
import com.hubin.app.cleanframework.R;
import com.hubin.app.cleanframework.ui.adapt.RepoAdapt;
import com.hubin.app.scm.models.GitHubRepo;
import com.hubin.app.scm.services.github.GithubService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    RecyclerView repoListView;
    RepoAdapt repoAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","onCreate");
        setContentView(R.layout.activity_main);
        ((MainApplication)getApplication()).getServiceComponent().inject(this);
        repoListView = (RecyclerView) findViewById(R.id.repo_list);
        repoAdapt = new RepoAdapt(this);
        repoListView.setLayoutManager(new LinearLayoutManager(this));
        repoListView.setAdapter(repoAdapt);

        // Create a very simple REST adapter which points the GitHub API endpoint.
        GithubService client =  retrofit.create(GithubService.class);

        // Fetch a list of the Github repositories.
        Call<List<GitHubRepo>> call = client.reposForUser("hubinjisu");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                Log.i("MainActivity","onResponse:"+ response.body());
                if (response.body() != null)  {
                    Log.i("MainActivity","onResponse:"+ response.body().get(0).getName());
                }
                repoAdapt.initListItems(response.body());
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.i("MainActivity","onFailure");
                repoAdapt.initListItems(null);
            }
        });
    }

}
