package com.cunjunwang.hospital.dao;

import com.cunjunwang.hospital.model.po.HMedicine;
import org.springframework.stereotype.Repository;

@Repository
public interface HMedicineMapper {
    int insert(HMedicine record);

    int insertSelective(HMedicine record);

    HMedicine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HMedicine record);

    int updateByPrimaryKey(HMedicine record);
}