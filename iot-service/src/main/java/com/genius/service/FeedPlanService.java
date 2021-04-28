package com.genius.service;

import com.genius.pojo.Device;
import com.genius.pojo.FeedPlan;
import com.genius.pojo.bo.DeviceBO;
import com.genius.pojo.bo.FeedPlanBO;

import java.util.List;

public interface FeedPlanService {

    /**
     * 新增饲养方案
     * @param feedPlanBO
     */
    public void addNewFeedPlan(FeedPlanBO feedPlanBO);

    /**
     * 根据用户id、设备id、饲养方案id，删除对应的用户设备的饲养方案
     * @param userId
     * @param deviceId
     * @param feedPlanId
     */
    public void deleteFeedPlan(String userId, String deviceId, String feedPlanId);

    /**
     * 修改饲养方案
     * @param feedPlanBO
     */
    public void updateFeedPlan(FeedPlanBO feedPlanBO);

    /**
     * 根据用户id查询对应的用户设备的饲养方案列表
     * @param userId
     * @return
     */
    public List<FeedPlan> queryAllFeedPlan(String userId, String deviceId);
}
