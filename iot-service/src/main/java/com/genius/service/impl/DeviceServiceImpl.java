package com.genius.service.impl;

import com.genius.mapper.DeviceMapper;
import com.genius.pojo.Device;
import com.genius.pojo.bo.DeviceBO;
import com.genius.service.DeviceService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewDevice(DeviceBO deviceBO) {
        //1.判断当前用户是否存在设备，如果没有，则新增为设备
        Integer isDefault = 0;
        List<Device> deviceList = this.queryAllDevice(deviceBO.getUserId());
        if(deviceList == null || deviceList.isEmpty() || deviceList.size() == 0){
            isDefault = 1;
        }
        //2.保存地址到数据库
        String deviceId = sid.nextShort();
        Device newDevice = new Device();
        BeanUtils.copyProperties(deviceBO, newDevice);
        newDevice.setId(deviceId);
        newDevice.setCreatedTime(new Date());
        newDevice.setUpdatedTime(new Date());

        deviceMapper.insert(newDevice);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserDevice(String userId, String deviceId) {
        Device device = new Device();
        device.setId(deviceId);
        device.setUserId(userId);

        deviceMapper.delete(device);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserDevice(DeviceBO deviceBO) {
        String deviceId = deviceBO.getDeviceId();

        Device pendingDevice = new Device();
        BeanUtils.copyProperties(deviceBO, pendingDevice);
        pendingDevice.setId(deviceId);
        pendingDevice.setUpdatedTime(new Date());

        deviceMapper.updateByPrimaryKeySelective(pendingDevice);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Device> queryAllDevice(String userId) {
        Device device = new Device();
        device.setUserId(userId);

        return deviceMapper.select(device);
    }
}
