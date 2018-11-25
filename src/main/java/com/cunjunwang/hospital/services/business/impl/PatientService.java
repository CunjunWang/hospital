package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.services.business.IPatientService;
import com.cunjunwang.hospital.services.dbservice.HPatientDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 病人相关业务操作服务
 * Created by CunjunWang on 2018/11/25.
 */
@Service
public class PatientService implements IPatientService {

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
}
