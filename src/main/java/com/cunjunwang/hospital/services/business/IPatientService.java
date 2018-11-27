package com.cunjunwang.hospital.services.business;

import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.model.vo.ModifyPatientVO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
public interface IPatientService {

    /**
     * 根据Id获取病人信息
     * @param patientId
     * @return
     */
    HPatient getOnePatientInfo(Long patientId);

    /**
     * 获取病人信息
     * @return
     */
    List<HPatient> getPatientInfoList();

    /**
     * 添加或更新病人信息
     * @param modifyPatientVO
     * @return
     */
    Boolean addOrUpdatePatientInfo(ModifyPatientVO modifyPatientVO);

    /**
     * 逻辑删除病人信息
     * @param patientId
     * @return
     */
    Boolean deletePatientInfo(Long patientId);
}
