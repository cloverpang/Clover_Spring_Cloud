package com.tinklabs.b2c.cloud.splytwebhook.beans;

import java.io.Serializable;

public class ServiceCallResult implements Serializable {
    private static final long serialVersionUID = -407190612131005334L;
    private int statusCode;
    private String reasonPhase;
    private String responseString;

    public ServiceCallResult() {
    }

    public ServiceCallResult(int statusCode, String reasonPhase, String responseString) {
        this.statusCode = statusCode;
        this.reasonPhase = reasonPhase;
        this.responseString = responseString;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhase() {
        return this.reasonPhase;
    }

    public void setReasonPhase(String reasonPhase) {
        this.reasonPhase = reasonPhase;
    }

    public String getResponseString() {
        return this.responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String toString() {
        return "ServiceCallResult [statusCode=" + this.statusCode + ", reasonPhase=" + this.reasonPhase + ", responseString=" + this.responseString + "]";
    }
}

