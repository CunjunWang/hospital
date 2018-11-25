package com.cunjunwang.hospital.controller;

import com.cunjunwang.hospital.entity.ResultData;
import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.model.po.HStaff;
import com.cunjunwang.hospital.services.business.impl.PatientService;
import com.cunjunwang.hospital.services.business.impl.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
@RestController
@RequestMapping("/staff")
@Api(value = "[Staff API]职员业务API")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/getOneStaffInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取单个职员信息", notes = "根据Id获取单个职员信息")
    public ResultData<HStaff> getOneStaffInfo(@ApiParam(name = "staffId", value = "职员Id", required = false) @RequestParam Long staffId) {
        return new ResultData<>(ResultData.SUCCESS, "", "获取职员信息成功", staffService.getOneStaffInfo(staffId));
    }

    @RequestMapping(value = "/getStaffInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有职员信息", notes = "获取所有职员信息")
    public ResultData<List<HStaff>> getStaffInfoList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取职员信息成功", staffService.getStaffInfoList());
    }

}
