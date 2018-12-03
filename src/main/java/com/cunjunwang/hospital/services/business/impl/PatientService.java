package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.constant.Constant;
import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.model.vo.ModifyPatientVO;
import com.cunjunwang.hospital.services.business.IPatientService;
import com.cunjunwang.hospital.services.dbservice.HPatientDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 病人相关业务操作服务
 * Created by CunjunWang on 2018/11/25.
 */
@Service
public class PatientService implements IPatientService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Autowired
    private HPatientDBService patientDBService;

    @Override
    public HPatient getOnePatientInfo(Long patientId) {
        return patientDBService.selectById(patientId);
    }

    @Override
    public List<HPatient> getPatientInfoList() {
        return patientDBService.selectAll();
    }

    @Override
    public Boolean addOrUpdatePatientInfo(ModifyPatientVO modifyPatientVO) {
        Long patientId = modifyPatientVO.getId();
        HPatient originPatient = patientDBService.selectById(patientId);
        // 原有改病人信息，更新
        if(originPatient != null) {
            logger.info("开始更新病人[{}]的信息", patientId);
            BeanUtils.copyProperties(modifyPatientVO, originPatient);
            originPatient.setUpdateTime(new Date());
            originPatient.setIsDel(Constant.NOT_DEL);
            Boolean isSuccess = patientDBService.updateInfo(originPatient);
            logger.info("更新病人[{}]的信息成功, {}", patientId, isSuccess);
        }
        // 新增
        else {
            logger.info("开始新增病人[{}]的信息", patientId);
            HPatient newPatient = new HPatient();
            BeanUtils.copyProperties(modifyPatientVO, newPatient);
            newPatient.setCreateTime(new Date());
            newPatient.setIsDel(Constant.NOT_DEL);
            Long newId = patientDBService.insertInfo(newPatient);
            logger.info("新增病人[{}]的信息成功, 新Id: {}", patientId, newId);
        }
        return true;
    }

    @Override
    public Boolean deletePatientInfo(Long patientId) {
        return patientDBService.deleteById(patientId);
    }
}
