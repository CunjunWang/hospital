package com.cunjunwang.hospital.services.business;

import com.cunjunwang.hospital.model.po.HPatient;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
public interface IPatientService {

    HPatient getOnePatientInfo(Long patientId);

    List<HPatient> getPatientInfoList();
}
