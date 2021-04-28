package com.genius.controller;

import com.genius.pojo.Device;
import com.genius.pojo.FeedPlan;
import com.genius.pojo.bo.DeviceBO;
import com.genius.pojo.bo.FeedPlanBO;
import com.genius.service.DeviceService;
import com.genius.service.FeedPlanService;
import com.genius.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "饲养方案管理", tags = {"用于饲养方案增删改查的接口"})
@RequestMapping("feedPlan")
@RestController
public class FeedPlanController {
    /**
     * 用户在饲养方案管理界面，可以针对饲养方案做如下操作：
     * 1.查询用户对应设备下的饲养方案列表
     * 2.新增饲养方案
     * 3.删除饲养方案
     * 4.修改饲养方案
     */

    @Autowired
    private FeedPlanService feedPlanService;

    @ApiOperation(value = "根据用户id查询饲养方案列表",notes = "根据用户id查询饲养方案列表", httpMethod = "POST")
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

        List<FeedPlan> list = feedPlanService.queryAllFeedPlan(userId, deviceId);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "用户新增饲养方案", notes = "用户新增饲养方案", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(@RequestBody FeedPlanBO feedPlanBO) {

        JSONResult checkRes = checkFeedPlan(feedPlanBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        feedPlanService.addNewFeedPlan(feedPlanBO);

        return JSONResult.ok();
    }
    private JSONResult checkFeedPlan(FeedPlanBO feedPlanBO) {
        String userId = feedPlanBO.getUserId();
        if (userId.length() != 16) {
            return JSONResult.errorMsg("用户id错误");
        }
        String deviceId = feedPlanBO.getDeviceId();
        if (deviceId.length() != 16) {
            return JSONResult.errorMsg("设备id错误");
        }
        String temp = feedPlanBO.getTemp();
        if (StringUtils.isBlank(temp)) {
            return JSONResult.errorMsg("温度不能为空");
        }
        if (temp.length() > 12) {
            return JSONResult.errorMsg("温度不能太长");
        }
        Integer ph = feedPlanBO.getPh();
        if (ph > 14 || ph < 0) {
            return JSONResult.errorMsg("酸碱度输入不正确");
        }

        String feedCycle = feedPlanBO.getFeedCycle();
        if (StringUtils.isBlank(feedCycle)) {
            return JSONResult.errorMsg("喂食周期不能为空");
        }
        if (feedCycle.length() > 36) {
            return JSONResult.errorMsg("喂食周期不能太长");
        }

        String waterChangeCycle = feedPlanBO.getWaterChangeCycle();
        if (StringUtils.isBlank(waterChangeCycle)) {
            return JSONResult.errorMsg("换水周期不能为空");
        }
        if (waterChangeCycle.length() > 36) {
            return JSONResult.errorMsg("换水周期不能太长");
        }

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户修改饲养方案", notes = "用户修改饲养方案", httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult update(@RequestBody FeedPlanBO feedPlanBO) {

        if (StringUtils.isBlank(feedPlanBO.getDeviceId())) {
            return JSONResult.errorMsg("修改饲养方案错误：deviceId不能为空");
        }

        if (StringUtils.isBlank(feedPlanBO.getFeedPlanId())) {
            return JSONResult.errorMsg("修改饲养方案错误：feedPlanId不能为空");
        }

        JSONResult checkRes = checkFeedPlan(feedPlanBO);
        if (checkRes.getStatus() != 200) {
            return checkRes;
        }

        feedPlanService.updateFeedPlan(feedPlanBO);

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户删除设备", notes = "用户删除设备", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @RequestParam String userId,
            @ApiParam(name = "deviceId", value = "设备ID", required = true)
            @RequestParam String deviceId,
            @ApiParam(name = "feedPlanId", value = "饲养方案ID", required = true)
            @RequestParam String feedPlanId) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(deviceId)) {
            return JSONResult.errorMsg("用户id、设备id或饲养方案id为空");
        }

        feedPlanService.deleteFeedPlan(userId, deviceId, feedPlanId);
        return JSONResult.ok();
    }
}
