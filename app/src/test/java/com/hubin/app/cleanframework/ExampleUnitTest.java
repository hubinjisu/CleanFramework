package com.hubin.app.cleanframework;

import android.widget.Button;

import com.hubin.app.cleanframework.ui.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleUnitTest
{
    private LoginActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( LoginActivity.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void addition_isCorrect() throws Exception
    {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveWelcomeFragment() throws Exception
    {
        Button loginBtn = (Button)activity.findViewById(R.id.email_sign_in_button);
        assertNotNull(loginBtn);
        assertEquals(loginBtn.getText(), activity.getString(R.string.action_sign_in));
    }
}