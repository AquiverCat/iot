package com.genius.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "设备评价对象BO", description = "从客户端，由用户传入的数据封装在此entity中")
public class DeviceCommentBO {
    @ApiModelProperty(value = "设备评价id",name = "deviceCommentId",example = "1234567890123456",required = true)
    private String deviceCommentId;

    @ApiModelProperty(value = "评价用户id",name = "userId",example = "1234567890123456",required = true)
    private String userId;

    @ApiModelProperty(value = "设备id",name = "deviceId",example = "1234567890123456",required = true)
    private String deviceId;

    @ApiModelProperty(value = "设备名称",name = "deviceName",example = "水族箱1号",required = true)
    private String deviceName;

    @ApiModelProperty(value = "评价等级",name = "commentLevel",example = "1：好评 2：中评 3：差评",required = true)
    private Integer commentLevel;

    @ApiModelProperty(value = "评价内容",name = "content",example = "鱼养的很好",required = true)
    private String content;

    public String getDeviceCommentId() {
        return deviceCommentId;
    }

    public void setDeviceCommentId(String deviceCommentId) {
        this.deviceCommentId = deviceCommentId;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
