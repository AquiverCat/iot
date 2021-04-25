package com.genius.pojo;

import javax.persistence.*;
import java.util.Date;

public class Device {
    /**
     * 主键 设备id
     */
    @Id
    private String id;

    /**
     * 关联用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 设备名
     */
    private String name;

    /**
     * 设备密码
     */
    private String password;

    /**
     * 发布主题
     */
    private String publish;

    /**
     * 订阅主题
     */
    private String receive;

    /**
     * 设备类型 1:  水族箱:  2:智能插排 3:智能花盆
     */
    private Integer type;

    /**
     * 设备图片
     */
    private String imag;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 获取主键 设备id
     *
     * @return id - 主键 设备id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键 设备id
     *
     * @param id 主键 设备id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取关联用户id
     *
     * @return user_id - 关联用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置关联用户id
     *
     * @param userId 关联用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取设备名
     *
     * @return name - 设备名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设备名
     *
     * @param name 设备名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取设备密码
     *
     * @return password - 设备密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置设备密码
     *
     * @param password 设备密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取发布主题
     *
     * @return publish - 发布主题
     */
    public String getPublish() {
        return publish;
    }

    /**
     * 设置发布主题
     *
     * @param publish 发布主题
     */
    public void setPublish(String publish) {
        this.publish = publish;
    }

    /**
     * 获取订阅主题
     *
     * @return receive - 订阅主题
     */
    public String getReceive() {
        return receive;
    }

    /**
     * 设置订阅主题
     *
     * @param receive 订阅主题
     */
    public void setReceive(String receive) {
        this.receive = receive;
    }

    /**
     * 获取设备类型 1:  水族箱:  2:智能插排 3:智能花盆
     *
     * @return type - 设备类型 1:  水族箱:  2:智能插排 3:智能花盆
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置设备类型 1:  水族箱:  2:智能插排 3:智能花盆
     *
     * @param type 设备类型 1:  水族箱:  2:智能插排 3:智能花盆
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取设备图片
     *
     * @return imag - 设备图片
     */
    public String getImag() {
        return imag;
    }

    /**
     * 设置设备图片
     *
     * @param imag 设备图片
     */
    public void setImag(String imag) {
        this.imag = imag;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}