package com.hubin.app.scm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by hubin on 2017/5/21.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MokitoTest {
    Github mGithub = GithubFactory.getSingleton();

    Github mMockGithub;
    Client mMockClient;

    @Before public void setUp() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .serializeNulls()
                .create();
        mMockClient = mock(Client.class);
        RequestInterceptor requestInterceptor = request -> request.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        RestAdapter restAdapter = new RestAdapter.Builder().setClient(mMockClient)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.github.com")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
                .build();
        mMockGithub = restAdapter.create(Github.class);
    }

    @Test public void reposTest1() throws IOException {
        String mockJsonResult =
                "这里模拟数据太长就不贴了，请看代码吧";
        FakeHttp.addPendingHttpResponse(200, mockJsonResult);

        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.print(result);
        assertThat(result, is(mockJsonResult));
    }

    //这个是走真实网络返回的数据
    @Test public void reposTest2() {
        List<Repo> list = mGithub.listRepos("devinshine");
        assertThat(list.size(), is(not(0)));
        System.out.print(list.size());
    }

    //这个是走真实网络返回的数据
    @Test public void reposTestByObservable() {
        int size = mGithub.listRepos2Observable("devinshine")
                .flatMap(Observable::from)
                .count()
                .toBlocking()
                .single();
        assertThat(size, is(not(0)));
        System.out.print(size);

        //下面代码是会报错的
        //TestSubscriber<Repo> testSubscriber = new TestSubscriber<>();
        //mGithub.listRepos2Observable("devinshine")
        //    .flatMap(Observable::from)
        //    .subscribe(testSubscriber);
        //assertThat(testSubscriber.getOnNextEvents().size(),is(not(0)));
    }

    //这是走模拟数据
    @Test public void reposTestByMockClient() throws IOException {
        String mockJsonResult =
                "这里模拟数据太长就不贴了，请看代码吧";
        Response response =
                new Response("http://www.baidu.com", 200, "nothing", Collections.EMPTY_LIST,
                        new TypedByteArray("application/json", mockJsonResult.getBytes()));
        when(mMockClient.execute(Matchers.anyObject())).thenReturn(response);

        int size = mMockGithub.listRepos2Observable("devinshine")
                .flatMap(Observable::from)
                .count()
                .toBlocking()
                .single();
        assertThat(size, is(1));
}
