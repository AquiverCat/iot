package com.genius.controller;

import com.genius.pojo.Device;
import com.genius.pojo.bo.DeviceBO;
import com.genius.service.DeviceService;
import com.genius.service.UserService;
import com.genius.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "设备管理", tags = {"用于设备增删改查的接口"})
@RequestMapping("device")
@RestController
public class DeviceController {
    /**
     * 用户在设备管理界面，可以针对设备做如下操作：
     * 1.查询用户的所有设备列表
     * 2.新增设备
     * 3.删除设备
     * 4.修改设备
     */

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "根据用户id查询设备列表",notes = "根据用户id查询设备列表", httpMethod = "POST")
    @PostMapping("/list")
    public JSONResult list(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam String userId){

        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("");
        }

        List<Device> list = deviceService.queryAllDevice(userId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "用户新增设备", notes = "用户新增设备", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestBody DeviceBO deviceBO) {

        JSONResult checkRes = checkDevice(deviceBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        deviceService.addNewDevice(deviceBO);

        return JSONResult.ok();
    }
    private JSONResult checkDevice(DeviceBO deviceBO) {
        String userId = deviceBO.getUserId();
        if (userId.length() != 16) {
            return JSONResult.errorMsg("用户id错误");
        }

        String name = deviceBO.getName();
        if (StringUtils.isBlank(name)) {
            return JSONResult.errorMsg("设备名称不能为空");
        }
        if (name.length() > 12) {
            return JSONResult.errorMsg("设备名称不能太长");
        }
        String password = deviceBO.getPassword();
        if (StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("设备密码不能为空");
        }
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能小于6");
        }

        String publish = deviceBO.getPublish();
        if (StringUtils.isBlank(publish)) {
            return JSONResult.errorMsg("发布主题不能为空");
        }
        if (publish.length() > 36) {
            return JSONResult.errorMsg("发布主题不能太长");
        }

        String receive = deviceBO.getReceive();
        if (StringUtils.isBlank(receive)) {
            return JSONResult.errorMsg("订阅主题不能为空");
        }
        if (receive.length() > 36) {
            return JSONResult.errorMsg("订阅主题不能太长");
        }

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户修改设备", notes = "用户修改设备", httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult update(@RequestBody DeviceBO deviceBO) {

        if (StringUtils.isBlank(deviceBO.getDeviceId())) {
            return JSONResult.errorMsg("修改设备错误：deviceId不能为空");
        }

        JSONResult checkRes = checkDevice(deviceBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        deviceService.updateUserDevice(deviceBO);

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户删除设备", notes = "用户删除设备", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(
            @RequestParam String userId,
            @RequestParam String deviceId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(deviceId)) {
            return JSONResult.errorMsg("用户id或设备id为空");
        }

        deviceService.deleteUserDevice(userId, deviceId);
        return JSONResult.ok();
    }
}
