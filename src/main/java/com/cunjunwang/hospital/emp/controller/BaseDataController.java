package com.cunjunwang.hospital.emp.controller;

import com.cunjunwang.hospital.emp.entity.ResultData;
import com.cunjunwang.hospital.emp.model.po.HStaff;
import com.cunjunwang.hospital.emp.model.vo.ModifyStaffVO;
import com.cunjunwang.hospital.emp.services.business.impl.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
@RestController
@RequestMapping("/data")
@Api(value = "[CRUD API]基础数据CRUD操作API")
public class BaseDataController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/getOneStaffInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取单个职员信息", notes = "根据Id获取单个职员信息")
    public ResultData<HStaff> getOneStaffInfo(
            @ApiParam(name = "staffId", value = "职员Id", required = true)
            @RequestParam Long staffId) {
        return new ResultData<>(ResultData.SUCCESS, "", "获取职员信息成功", staffService.getOneStaffInfo(staffId));
    }

    @RequestMapping(value = "/getStaffInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有职员信息", notes = "获取所有职员信息")
    public ResultData<List<HStaff>> getStaffInfoList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取职员信息成功", staffService.getStaffInfoList());
    }

    @RequestMapping(value = "/addOrUpdateStaffInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加/更新职员信息", notes = "添加/更新职员信息")
    public ResultData<Boolean> addOrUpdateStaffInfo(
            @ApiParam(name = "modifyStaffVO", value = "modifyStaffVO", required = true)
            @RequestBody ModifyStaffVO modifyStaffVO) {
        return new ResultData<>(ResultData.SUCCESS, "", "添加/更新职员信息成功", staffService.addOrUpdateStaffInfo(modifyStaffVO));
    }

    @RequestMapping(value = "/deleteStaffInfo", method = RequestMethod.POST)
    @ApiOperation(value = "删除职员信息", notes = "删除职员信息")
    public ResultData<Boolean> deleteStaffInfo(
            @ApiParam(name = "staffId", value = "职员Id", required = true)
            @RequestParam Long staffId) {
        return new ResultData<>(ResultData.SUCCESS, "", "删除职员信息成功", staffService.deleteStaffInfo(staffId));
    }

}
