package com.genius.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name = "feed_plan")
public class FeedPlan {
    /**
     * 主键 饲养方案id
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
     * 最适温度
     */
    private String temp;

    /**
     * 最适ph
     */
    private Integer ph;

    /**
     * 喂食周期
     */
    @Column(name = "feed_cycle")
    private String feedCycle;

    /**
     * 换水周期
     */
    @Column(name = "water_change_cycle")
    private String waterChangeCycle;

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
     * 获取主键 饲养方案id
     *
     * @return id - 主键 饲养方案id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键 饲养方案id
     *
     * @param id 主键 饲养方案id
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
     * 获取最适温度
     *
     * @return temp - 最适温度
     */
    public String getTemp() {
        return temp;
    }

    /**
     * 设置最适温度
     *
     * @param temp 最适温度
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }

    /**
     * 获取最适ph
     *
     * @return ph - 最适ph
     */
    public Integer getPh() {
        return ph;
    }

    /**
     * 设置最适ph
     *
     * @param ph 最适ph
     */
    public void setPh(Integer ph) {
        this.ph = ph;
    }

    /**
     * 获取喂食周期
     *
     * @return feed_cycle - 喂食周期
     */
    public String getFeedCycle() {
        return feedCycle;
    }

    /**
     * 设置喂食周期
     *
     * @param feedCycle 喂食周期
     */
    public void setFeedCycle(String feedCycle) {
        this.feedCycle = feedCycle;
    }

    /**
     * 获取换水周期
     *
     * @return water_change_cycle - 换水周期
     */
    public String getWaterChangeCycle() {
        return waterChangeCycle;
    }

    /**
     * 设置换水周期
     *
     * @param waterChangeCycle 换水周期
     */
    public void setWaterChangeCycle(String waterChangeCycle) {
        this.waterChangeCycle = waterChangeCycle;
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