package com.genius.service;

import com.genius.pojo.Device;
import com.genius.pojo.bo.DeviceBO;

import java.util.List;

public interface DeviceService {

    /**
     * 新增设备
     * @param deviceBO
     */
    public void addNewDevice(DeviceBO deviceBO);

    /**
     * 根据用户id和设备id，删除对应的用户设备信息
     * @param userId
     * @param deviceId
     */
    public void deleteUserAddress(String userId, String deviceId);

    /**
     * 修改设备
     * @param deviceBO
     */
    public void updateUserAddress(DeviceBO deviceBO);

    /**
     * 根据用户id查询用户的设备列表
     * @param userId
     * @return
     */
    public List<Device> queryAll(String userId);
}
