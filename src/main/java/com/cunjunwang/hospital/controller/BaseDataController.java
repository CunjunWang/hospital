package com.cunjunwang.hospital.controller;

import com.cunjunwang.hospital.entity.ResultData;
import com.cunjunwang.hospital.model.po.HMedicine;
import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.model.po.HStaff;
import com.cunjunwang.hospital.model.po.HWard;
import com.cunjunwang.hospital.model.vo.ModifyMedicineVO;
import com.cunjunwang.hospital.model.vo.ModifyPatientVO;
import com.cunjunwang.hospital.model.vo.ModifyStaffVO;
import com.cunjunwang.hospital.model.vo.ModifyWardVO;
import com.cunjunwang.hospital.services.business.impl.MedicineService;
import com.cunjunwang.hospital.services.business.impl.PatientService;
import com.cunjunwang.hospital.services.business.impl.StaffService;
import com.cunjunwang.hospital.services.business.impl.WardService;
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
    private PatientService patientService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private WardService wardService;

    @RequestMapping(value = "/getOnePatientInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取单个病人信息", notes = "根据Id获取单个病人信息")
    public ResultData<HPatient> getOnePatientInfo(
            @ApiParam(name = "patientId", value = "病人Id", required = true)
            @RequestParam Long patientId) {
        return new ResultData<>(ResultData.SUCCESS, "", "获取病人信息成功", patientService.getOnePatientInfo(patientId));
    }

    @RequestMapping(value = "/getPatientInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有病人信息", notes = "获取所有病人信息")
    public ResultData<List<HPatient>> getPatientInfoList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取病人信息成功", patientService.getPatientInfoList());
    }

    @RequestMapping(value = "/addOrUpdatePatientInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加/更新病人信息", notes = "添加/更新病人信息")
    public ResultData<Boolean> addOrUpdatePatientInfo(
            @ApiParam(name = "modifyPatientVO", value = "病人信息对象", required = true)
            @RequestBody ModifyPatientVO modifyPatientVO) {
        return new ResultData<>(ResultData.SUCCESS, "", "添加/更新病人信息成功", patientService.addOrUpdatePatientInfo(modifyPatientVO));
    }

    @RequestMapping(value = "/deletePatientInfo", method = RequestMethod.POST)
    @ApiOperation(value = "删除病人信息", notes = "删除病人信息")
    public ResultData<Boolean> deletePatientInfo(
            @ApiParam(name = "patientId", value = "病人Id", required = true)
            @RequestParam Long patientId) {
        return new ResultData<>(ResultData.SUCCESS, "", "删除病人信息成功", patientService.deletePatientInfo(patientId));
    }

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

    @RequestMapping(value = "/getOneMedicineInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取单个药品信息", notes = "根据Id获取单个药品信息")
    public ResultData<HMedicine> getOneMedicineInfo(
            @ApiParam(name = "medicineId", value = "药品Id", required = true)
            @RequestParam Long medicineId) {
        return new ResultData<>(ResultData.SUCCESS, "", "获取药品信息成功", medicineService.getOneMedicineInfo(medicineId));
    }

    @RequestMapping(value = "/getMedicineInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有药品信息", notes = "获取所有药品信息")
    public ResultData<List<HMedicine>> getMedicineInfoList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取所有药品信息成功", medicineService.getMedicineInfoList());
    }

    @RequestMapping(value = "/addOrUpdateMedicineInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加/更新药品信息", notes = "添加/更新药品信息")
    public ResultData<Boolean> addOrUpdateMedicineInfo(
            @ApiParam(name = "modifyMedicineVO", value = "药品信息对象", required = true)
            @RequestBody ModifyMedicineVO modifyMedicineVO) {
        return new ResultData<>(ResultData.SUCCESS, "", "添加/更新药品信息成功", medicineService.addOrUpdateMedicineInfo(modifyMedicineVO));
    }

    @RequestMapping(value = "/deleteMedicineInfo", method = RequestMethod.POST)
    @ApiOperation(value = "删除药品信息", notes = "删除药品信息")
    public ResultData<Boolean> deleteMedicineInfo(
            @ApiParam(name = "medicineId", value = "药品Id", required = true)
            @RequestParam Long medicineId) {
        return new ResultData<>(ResultData.SUCCESS, "", "删除药品信息成功", medicineService.deleteMedicineInfo(medicineId));
    }

    @RequestMapping(value = "/getOneWardInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id获取单个病房信息", notes = "根据Id获取单个病房信息")
    public ResultData<HWard> getOneWardInfo(
            @ApiParam(name = "wardId", value = "病房Id", required = true)
            @RequestParam Long wardId) {
        return new ResultData<>(ResultData.SUCCESS, "", "获取病房信息成功", wardService.getOneWardInfo(wardId));
    }

    @RequestMapping(value = "/getWardInfoList", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有病房信息", notes = "获取所有病房信息")
    public ResultData<List<HWard>> getWardInfoList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取所有病房信息成功", wardService.getWardInfoList());
    }

    @RequestMapping(value = "/addOrUpdateWardInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加/更新病房信息", notes = "添加/更新病房信息")
    public ResultData<Boolean> addOrUpdateWardInfo(
            @ApiParam(name = "modifyWardVO", value = "病房信息对象", required = true)
            @RequestBody ModifyWardVO modifyWardVO) {
        return new ResultData<>(ResultData.SUCCESS, "", "添加/更新病房信息成功", wardService.addOrUpdateWardInfo(modifyWardVO));
    }

    @RequestMapping(value = "/deleteWardInfo", method = RequestMethod.POST)
    @ApiOperation(value = "删除病房信息", notes = "删除病房信息")
    public ResultData<Boolean> deleteWardInfo(
            @ApiParam(name = "wardId", value = "病房Id", required = true)
            @RequestParam Long wardId) {
        return new ResultData<>(ResultData.SUCCESS, "", "删除病房信息成功", wardService.deleteWardInfo(wardId));
    }
}
