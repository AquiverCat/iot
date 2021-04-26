package com.genius.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "设备对象BO", description = "从客户端，由用户传入的数据封装在此entity中")
public class DeviceBO {

    @ApiModelProperty(value = "设备名",name = "name",example = "水族箱设备",required = true)
    private String name;

    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    private String password;

    @ApiModelProperty(value = "发布主题",name = "publish",example = "/public",required = true)
    private String publish;

    @ApiModelProperty(value = "订阅主题",name = "receive",example = "/receive",required = true)
    private String receive;

    /**
     * 设备类型 1:  水族箱:  2:智能插排 3:智能花盆
     */
    @ApiModelProperty(value = "设备类型",name = "type",example = "1:  水族箱",required = false)
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
