package com.hubin.app.scm.models;

/**
 * Created by hubin on 2017/5/27.
 */

public class HttpResult<T> {

    //resultCode and resultMessage
    private int count;
    private int start;
    private int total;
    private String title;

    //Data
    private T subjects;
}