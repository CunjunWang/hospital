package com.cunjunwang.hospital.services.dbservice;

import com.cunjunwang.hospital.dao.HPatientMapper;
import com.cunjunwang.hospital.model.po.HPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
@Service
public class HPatientDBService {

    @Autowired
    private HPatientMapper patientMapper;

    public HPatient selectById(Long patientId) {
        if (patientId == null) {
            return null;
        }
        return patientMapper.selectByPrimaryKey(patientId);
    }

    public List<HPatient> selectAll() {
        return patientMapper.selectAll();
    }

    public Boolean updateInfo(HPatient originPatient) {
        if(originPatient == null) {
            return null;
        }
        return patientMapper.updateByPrimaryKeySelective(originPatient) == 1;
    }

    public Long insertInfo(HPatient newPatient) {
        if(newPatient == null) {
            return null;
        }
        patientMapper.insertSelective(newPatient);
        return newPatient.getId();
    }

    public Boolean deleteById(Long patientId) {
        if(patientId == null) {
            return null;
        }
        return patientMapper.deleteByPrimaryKey(patientId) == 1;
    }
}
