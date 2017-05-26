package com.hubin.app.scm.services.call.imp;

import android.os.SystemClock;

import com.hubin.app.scm.ServiceHandler;
import com.hubin.app.scm.services.call.CallService;

/**
 * Created by hubin on 2017/5/14.
 */

public class CallServiceImpl implements CallService {
    @Override
    public int makeCall() {
        ServiceHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(500);
            }
        });
        return 0;
    }
}
