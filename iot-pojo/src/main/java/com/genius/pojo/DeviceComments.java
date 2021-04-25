package com.genius.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "device_comments")
public class DeviceComments {
    /**
     * id主键
     */
    @Id
    private String id;

    /**
     * 用户id 用户名须脱敏
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 设备id
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    private String deviceName;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     */
    @Column(name = "comment_level")
    private Integer commentLevel;

    /**
     * 评价内容
     */
    private String content;

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
     * 获取id主键
     *
     * @return id - id主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id主键
     *
     * @param id id主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id 用户名须脱敏
     *
     * @return user_id - 用户id 用户名须脱敏
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id 用户名须脱敏
     *
     * @param userId 用户id 用户名须脱敏
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取设备id
     *
     * @return device_id - 设备id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备id
     *
     * @param deviceId 设备id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取设备名称
     *
     * @return device_name - 设备名称
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置设备名称
     *
     * @param deviceName 设备名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取评价等级 1：好评 2：中评 3：差评
     *
     * @return comment_level - 评价等级 1：好评 2：中评 3：差评
     */
    public Integer getCommentLevel() {
        return commentLevel;
    }

    /**
     * 设置评价等级 1：好评 2：中评 3：差评
     *
     * @param commentLevel 评价等级 1：好评 2：中评 3：差评
     */
    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * 获取评价内容
     *
     * @return content - 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评价内容
     *
     * @param content 评价内容
     */
    public void setContent(String content) {
        this.content = content;
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