package com.hubin.app.scm;

import com.hubin.app.scm.services.call.CallService;
import com.hubin.app.scm.services.call.imp.CallServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleUnitTest {
    CallService callService;

    @Before
    public void setup() {
        callService = new CallServiceImpl();
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void callServiceMakeCall() throws Exception {
        assertEquals(callService.makeCall(), 0);
    }
}