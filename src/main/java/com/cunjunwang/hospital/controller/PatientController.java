package com.cunjunwang.hospital.controller;

import com.cunjunwang.hospital.entity.ResultData;
import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.services.business.impl.PatientService;
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
@RequestMapping("/patient")
@Api(value = "[Patient API]病人业务API")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/getOnePatientInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取单个病人信息", notes = "根据Id获取单个病人信息")
    public ResultData<HPatient> getOnePatientInfo(@ApiParam(name = "patientId", value = "病人Id", required = false) @RequestParam Long patientId) {
        return new ResultData<>(ResultData.SUCCESS, "", "获取病人信息成功", patientService.getOnePatientInfo(patientId));
    }

    @RequestMapping(value = "/getPatientInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有病人信息", notes = "获取所有病人信息")
    public ResultData<List<HPatient>> getPatientInfoList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取病人信息成功", patientService.getPatientInfoList());
    }

}
