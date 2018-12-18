package com.tinklabs.b2c.cloud.parameterservice.beans;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * Created by clover on 2017/10/9.
 */
public class ApiCallResult<T> implements Serializable {
    private static final long serialVersionUID = 1996691926903571155L;
    private String message;
    private T content;

    public ApiCallResult() {
    }

    public ApiCallResult(String message, T content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String toString() {
        return "ApiCallResult [message=" + this.message + ", content=" + JSON.toJSONString(this.content, new SerializerFeature[]{SerializerFeature.WriteMapNullValue}) + "]";
    }
}