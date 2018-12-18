package com.tinklabs.b2c.cloud.splytwebhook.beans;

import java.util.Arrays;

/**
 * Created by clover on 2017/11/1.
 */
public class ConditionBean {
    private String conditionName;
    private String[] conditionNameArr;
    private String conditionExpression;
    private String conditionValue;
    private String[] conditionValueArr;

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String[] getConditionNameArr() {
        return conditionNameArr;
    }

    public void setConditionNameArr(String[] conditionNameArr) {
        this.conditionNameArr = conditionNameArr;
    }

    public String getConditionExpression() {
        return conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String[] getConditionValueArr() {
        return conditionValueArr;
    }

    public void setConditionValueArr(String[] conditionValueArr) {
        this.conditionValueArr = conditionValueArr;
    }

    @Override
    public String toString() {
        return "ConditionBean{" +
                "conditionName='" + conditionName + '\'' +
                ", conditionNameArr=" + Arrays.toString(conditionNameArr) +
                ", conditionExpression='" + conditionExpression + '\'' +
                ", conditionValue='" + conditionValue + '\'' +
                ", conditionValueArr=" + Arrays.toString(conditionValueArr) +
                '}';
    }
}
