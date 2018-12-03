package com.cunjunwang.hospital.dao;

import com.cunjunwang.hospital.model.po.HMedicine;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HMedicineMapper {
    int insert(HMedicine record);

    int insertSelective(HMedicine record);

    HMedicine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HMedicine record);

    int updateByPrimaryKey(HMedicine record);

    List<HMedicine> selectAll();
}