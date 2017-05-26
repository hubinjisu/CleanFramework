package com.hubin.app.scm;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

/**
 * Created by hubin on 2017/5/14.
 */

public class ServiceHandler extends Handler {
    private static ServiceHandler instance;
    private ServiceHandler(Looper looper)
    {
        super(looper);
    }

    public static ServiceHandler getInstance()
    {
        if (instance == null)
        {
            HandlerThread sessionThread = new HandlerThread("ServiceHandler", Process.THREAD_PRIORITY_BACKGROUND);
            sessionThread.start();
            instance = new ServiceHandler(sessionThread.getLooper());
        }
        return instance;
    }
}
