package com.hubin.app.scm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hubin.app.scm.models.GitHubRepo;
import com.hubin.app.scm.services.github.GithubService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by hubin on 2017/5/21.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MokitoTest {

    @Mock
    OkHttpClient mMockClient;
    GithubService client;
    MockWebServer mockWebServer;
    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test public void reposTest1() throws InterruptedException, IOException {
        mockWebServer = new MockWebServer();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .serializeNulls()
                .create();
        Retrofit retrofit =  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mockWebServer.url("").toString())
//                .client(mMockClient)
                .build();
        client =  retrofit.create(GithubService.class);
        //Set a response for retrofit to handle. You can copy a sample
        //response from your server to simulate a correct result or an error.
        //MockResponse can also be customized with different parameters
        //to match your test needs
        mockWebServer.enqueue(createResponse());
//        mockWebServer.enqueue(createListResponse());

        //With your service created you can now call its method that should
        //consume the MockResponse above. You can then use the desired
        //assertion to check if the result is as expected. For example:
        Call<GitHubRepo> call = client.getUser("hubinjisu");
        GitHubRepo result = call.execute().body();

        assertTrue(result != null);
        assertEquals("tom", result.getName());

//        Call<List<GitHubRepo>> listCall = client.reposForUser("hu");
//        List<GitHubRepo> listResult = listCall.execute().body();
//        assertTrue(listResult != null);
//        assertEquals(3, listResult.size());
//        assertEquals("atom", listResult.get(2).getName());

//        final AtomicReference<GitHubRepo> actual = new AtomicReference<>();
//        final CountDownLatch latch = new CountDownLatch(1);
//        call.enqueue(new Callback<GitHubRepo>() {
//            @Override
//            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
//                actual.set(response.body());
//                latch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<GitHubRepo> call, Throwable t) {
//                actual.set(null);
//                latch.countDown();
//            }
//        });
//        latch.await();
////        assertTrue(latch.await(1, SECONDS));
//
//        assertThat(actual.get()).isEqualTo("Response!");
        //Finish web server
        mockWebServer.shutdown();
    }

//    @Test
//    public void realTest() {
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        OkHttpClient httpClient = client.dispatcher(new Dispatcher(new ImmediateExecutor())).build();
//        OkHttpClient client = OkHttpClient.Builder().
//                OkHttpClient.Builder()
//                .dispatcher(new Dispatcher(new ImmediateExecutor()))
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(client)
//                //Your params
//                .build();
//    }


    private MockResponse createResponse() {
        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setResponseCode(200)
                .setBody("{\"id\":\"123\", \"name\":\"tom\"}");
        response.throttleBody(1024, 1, SECONDS);
        return response;
    }
    private MockResponse createListResponse() {
        MockResponse response = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setResponseCode(200)
                .setBody("{{\"id\":\"123\", \"name\":\"tom\"}, {\"id\":\"234\", \"name\":\"test\"}, {\"id\":\"345\", \"name\":\"atom\"}}");
        response.throttleBody(1024, 1, SECONDS);
        return response;
    }

    public class ImmediateExecutor implements Executor {
        @Override public void execute(Runnable command) {
            command.run();
        }
    }
}
