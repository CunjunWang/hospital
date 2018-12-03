package com.cunjunwang.hospital.services.dbservice;

import com.cunjunwang.hospital.dao.HMedicineMapper;
import com.cunjunwang.hospital.dao.HPatientMapper;
import com.cunjunwang.hospital.model.po.HMedicine;
import com.cunjunwang.hospital.model.po.HPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Service
public class HMedicineDBService {


    @Autowired
    private HMedicineMapper medicineMapper;

    public HMedicine selectById(Long medicineId) {
        if (medicineId == null) {
            return null;
        }
        return medicineMapper.selectByPrimaryKey(medicineId);
    }

    public List<HMedicine> selectAll() {
        return medicineMapper.selectAll();
    }

}
