package com.genius.controller;

import com.genius.pojo.DeviceComments;
import com.genius.pojo.FeedPlan;
import com.genius.pojo.bo.DeviceCommentBO;
import com.genius.pojo.bo.FeedPlanBO;
import com.genius.service.DeviceCommentService;
import com.genius.service.FeedPlanService;
import com.genius.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "设备评价管理", tags = {"用于设备评价增删改查的接口"})
@RequestMapping("deviceComment")
@RestController
public class DeviceCommentController {
    /**
     * 用户在设备评价管理界面，可以针对设备评价做如下操作：
     * 1.查询用户对应设备下的设备评价列表
     * 2.新增设备评价
     * 3.删除设备评价
     * 4.修改设备评价
     */

    @Autowired
    private DeviceCommentService deviceCommentService;

    @ApiOperation(value = "根据用户id查询设备评价列表",notes = "根据用户id查询设备评价列表", httpMethod = "POST")
    @PostMapping("/list")
    public JSONResult list(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam String userId,
            @ApiParam(name = "deviceId", value = "设备ID", required = true)
            @RequestParam String deviceId){

        if(StringUtils.isBlank(userId)){
            return JSONResult.errorMsg("用户Id为空");
        }

        if(StringUtils.isBlank(deviceId)){
            return JSONResult.errorMsg("设备Id为空");
        }

        List<DeviceComments> list = deviceCommentService.queryAllDeviceComment(userId, deviceId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "用户新增设备评价", notes = "用户新增设备评价", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestBody DeviceCommentBO deviceCommentBO) {

        JSONResult checkRes = checkDeviceComment(deviceCommentBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        deviceCommentService.addNewDeviceComment(deviceCommentBO);

        return JSONResult.ok();
    }
    private JSONResult checkDeviceComment(DeviceCommentBO deviceCommentBO) {
        String userId = deviceCommentBO.getUserId();
        if (userId.length() != 16) {
            return JSONResult.errorMsg("用户id错误");
        }
        String deviceId = deviceCommentBO.getDeviceId();
        if (deviceId.length() != 16) {
            return JSONResult.errorMsg("设备id错误");
        }
        String deviceName = deviceCommentBO.getDeviceName();
        if (StringUtils.isBlank(deviceName)) {
            return JSONResult.errorMsg("设备名称不为空");
        }
        if (deviceName.length() > 12) {
            return JSONResult.errorMsg("设备名称不能太长");
        }
        Integer commentLevel = deviceCommentBO.getCommentLevel();
        if (commentLevel > 3) {
            return JSONResult.errorMsg("评价等级选择不正确");
        }

        String content = deviceCommentBO.getContent();
        if (StringUtils.isBlank(content)) {
            return JSONResult.errorMsg("评价内容不能为空");
        }
        if (content.length() > 64) {
            return JSONResult.errorMsg("评价内容不能太长");
        }

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户修改设备评价", notes = "用户修改设备评价", httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult update(@RequestBody DeviceCommentBO deviceCommentBO) {

        if (StringUtils.isBlank(deviceCommentBO.getDeviceId())) {
            return JSONResult.errorMsg("修改设备评价错误：deviceId不能为空");
        }

        if (StringUtils.isBlank(deviceCommentBO.getDeviceCommentId())) {
            return JSONResult.errorMsg("修改设备评价错误：deviceCommentId不能为空");
        }

        JSONResult checkRes = checkDeviceComment(deviceCommentBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        deviceCommentService.updateDeviceComment(deviceCommentBO);

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户删除设备评价", notes = "用户删除设备评价", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam String userId,
            @ApiParam(name = "deviceId", value = "设备ID", required = true)
            @RequestParam String deviceId,
            @ApiParam(name = "deviceCommentId", value = "设备评价ID", required = true)
            @RequestParam String deviceCommentId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(deviceId)) {
            return JSONResult.errorMsg("用户id、设备id或设备评价id为空");
        }

        deviceCommentService.deleteDeviceComment(userId, deviceId, deviceCommentId);
        return JSONResult.ok();
    }
}
