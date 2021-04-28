package com.genius.service.impl;

import com.genius.mapper.FeedPlanMapper;
import com.genius.pojo.FeedPlan;
import com.genius.pojo.bo.FeedPlanBO;
import com.genius.service.FeedPlanService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FeedPlanServiceImpl implements FeedPlanService {

    @Autowired
    private FeedPlanMapper feedPlanMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewFeedPlan(FeedPlanBO feedPlanBO) {
        //1.判断当前用户是否存在设备，如果没有，则新增为设备
        Integer isDefault = 0;
        List<FeedPlan> feedPlanList = this.queryAllFeedPlan(feedPlanBO.getUserId(),feedPlanBO.getDeviceId());
        if(feedPlanList == null || feedPlanList.isEmpty() || feedPlanList.size() == 0){
            isDefault = 1;
        }
        //2.保存地址到数据库
        String deviceId = sid.nextShort();
        FeedPlan newFeedPlan = new FeedPlan();
        BeanUtils.copyProperties(feedPlanBO, newFeedPlan);
        newFeedPlan.setId(deviceId);
        newFeedPlan.setCreatedTime(new Date());
        newFeedPlan.setUpdatedTime(new Date());

        feedPlanMapper.insert(newFeedPlan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteFeedPlan(String userId, String deviceId, String feedPlanId){
        FeedPlan feedPlan = new FeedPlan();
        feedPlan.setId(feedPlanId);
        feedPlan.setUserId(userId);
        feedPlan.setDeviceId(deviceId);

        feedPlanMapper.delete(feedPlan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateFeedPlan(FeedPlanBO feedPlanBO) {
        String feedPlanId = feedPlanBO.getFeedPlanId();

        FeedPlan pendingFeedPlan = new FeedPlan();
        BeanUtils.copyProperties(feedPlanBO, pendingFeedPlan);
        pendingFeedPlan.setId(feedPlanId);
        pendingFeedPlan.setUpdatedTime(new Date());

        feedPlanMapper.updateByPrimaryKeySelective(pendingFeedPlan);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<FeedPlan> queryAllFeedPlan(String userId, String deviceId) {
        FeedPlan feedPlan = new FeedPlan();
        feedPlan.setUserId(userId);
        feedPlan.setDeviceId(deviceId);

        return feedPlanMapper.select(feedPlan);
    }
}
