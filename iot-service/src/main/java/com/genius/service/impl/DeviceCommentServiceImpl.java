package com.genius.service.impl;

import com.genius.mapper.DeviceCommentsMapper;
import com.genius.mapper.FeedPlanMapper;
import com.genius.pojo.DeviceComments;
import com.genius.pojo.FeedPlan;
import com.genius.pojo.bo.DeviceCommentBO;
import com.genius.pojo.bo.FeedPlanBO;
import com.genius.service.DeviceCommentService;
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
public class DeviceCommentServiceImpl implements DeviceCommentService {

    @Autowired
    private DeviceCommentsMapper deviceCommentsMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewDeviceComment(DeviceCommentBO deviceCommentBO) {
        //1.判断当前用户是否存在设备，如果没有，则新增为设备
        Integer isDefault = 0;
        List<DeviceComments> deviceCommentsList = this.queryAllDeviceComment(deviceCommentBO.getUserId(),deviceCommentBO.getDeviceId());
        if(deviceCommentsList == null || deviceCommentsList.isEmpty() || deviceCommentsList.size() == 0){
            isDefault = 1;
        }
        //2.保存地址到数据库
        String deviceId = sid.nextShort();
        DeviceComments newDeviceComments = new DeviceComments();
        BeanUtils.copyProperties(deviceCommentBO, newDeviceComments);
        newDeviceComments.setId(deviceId);
        newDeviceComments.setCreatedTime(new Date());
        newDeviceComments.setUpdatedTime(new Date());

        deviceCommentsMapper.insert(newDeviceComments);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteDeviceComment(String userId, String deviceId, String deviceCommentId){
        DeviceComments deviceComments = new DeviceComments();
        deviceComments.setId(deviceCommentId);
        deviceComments.setUserId(userId);
        deviceComments.setDeviceId(deviceId);

        deviceCommentsMapper.delete(deviceComments);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateDeviceComment(DeviceCommentBO deviceCommentBO) {
        String deviceCommentId = deviceCommentBO.getDeviceCommentId();

        DeviceComments pendingDeviceComments = new DeviceComments();
        BeanUtils.copyProperties(deviceCommentBO, pendingDeviceComments);
        pendingDeviceComments.setId(deviceCommentId);
        pendingDeviceComments.setUpdatedTime(new Date());

        deviceCommentsMapper.updateByPrimaryKeySelective(pendingDeviceComments);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<DeviceComments> queryAllDeviceComment(String userId, String deviceId) {
        DeviceComments deviceComments = new DeviceComments();
        deviceComments.setUserId(userId);
        deviceComments.setDeviceId(deviceId);

        return deviceCommentsMapper.select(deviceComments);
    }
}
