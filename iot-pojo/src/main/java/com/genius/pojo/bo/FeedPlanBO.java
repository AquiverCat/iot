package com.genius.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "饲养方案对象BO", description = "从客户端，由用户传入的数据封装在此entity中")
public class FeedPlanBO {
    @ApiModelProperty(value = "饲养方案id",name = "feedPlanId",example = "1234567890123456",required = true)
    private String feedPlanId;

    @ApiModelProperty(value = "用户id",name = "userId",example = "1234567890123456",required = true)
    private String userId;

    @ApiModelProperty(value = "设备id",name = "deviceId",example = "1234567890123456",required = true)
    private String deviceId;

    @ApiModelProperty(value = "温度",name = "temp",example = "36.5℃",required = true)
    private String temp;

    @ApiModelProperty(value = "酸碱度",name = "ph",example = "7",required = true)
    private Integer ph;

    @ApiModelProperty(value = "喂食周期",name = "feedCycle",example = "1天1次",required = true)
    private String feedCycle;

    @ApiModelProperty(value = "换水周期",name = "waterChangeCycle",example = "一周2次",required = true)
    private String waterChangeCycle;

    public String getFeedPlanId() {
        return feedPlanId;
    }

    public void setFeedPlanId(String feedPlanId) {
        this.feedPlanId = feedPlanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Integer getPh() {
        return ph;
    }

    public void setPh(Integer ph) {
        this.ph = ph;
    }

    public String getFeedCycle() {
        return feedCycle;
    }

    public void setFeedCycle(String feedCycle) {
        this.feedCycle = feedCycle;
    }

    public String getWaterChangeCycle() {
        return waterChangeCycle;
    }

    public void setWaterChangeCycle(String waterChangeCycle) {
        this.waterChangeCycle = waterChangeCycle;
    }
}
