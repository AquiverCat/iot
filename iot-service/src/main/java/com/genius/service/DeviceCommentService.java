package com.genius.service;

import com.genius.pojo.DeviceComments;
import com.genius.pojo.bo.DeviceCommentBO;
import com.genius.pojo.bo.FeedPlanBO;

import java.util.List;

public interface DeviceCommentService {

    /**
     * 新增评价
     * @param deviceCommentBO
     */
    public void addNewDeviceComment(DeviceCommentBO deviceCommentBO);

    /**
     * 根据用户id、设备id、饲养方案id，删除对应的用户设备的评价
     * @param userId
     * @param deviceId
     * @param feedPlanId
     */
    public void deleteDeviceComment(String userId, String deviceId, String feedPlanId);

    /**
     * 修改评价
     * @param deviceCommentBO
     */
    public void updateDeviceComment(DeviceCommentBO deviceCommentBO);

    /**
     * 根据用户id查询对应的用户设备的评价列表
     * @param userId
     * @return
     */
    public List<DeviceComments> queryAllDeviceComment(String userId, String deviceId);
}
