package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.model.po.HMedicine;
import com.cunjunwang.hospital.model.vo.ModifyMedicineVO;
import com.cunjunwang.hospital.services.business.IMedicineService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Service
public class MedicineService implements IMedicineService {


    @Override
    public HMedicine getOneMedicineInfo(Long medicineId) {
        return null;
    }

    @Override
    public List<HMedicine> getMedicineInfoList() {
        return null;
    }

    @Override
    public Boolean addOrUpdateMedicineInfo(ModifyMedicineVO modifyMedicineVO) {
        return null;
    }

    @Override
    public Boolean deleteMedicineInfo(Long medicineId) {
        return null;
    }
}
