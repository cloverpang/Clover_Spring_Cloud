package com.tinklabs.b2c.cloud.parameterservice.exceptions;

/**
 * Created by Administrator on 2017/12/20.
 */
public class ValidateException extends Exception {
    private static final long serialVersionUID = -2085497573819645858L;

    public ValidateException(String msg) {
        super(msg);
    }

    public ValidateException(String msg, Throwable e) {
        super(msg, e);
    }
}
