package com.cunjunwang.hospital.dao;

import com.cunjunwang.hospital.model.po.HPatient;
import org.springframework.stereotype.Repository;

@Repository
public interface HPatientMapper {
    int insert(HPatient record);

    int insertSelective(HPatient record);

    HPatient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HPatient record);

    int updateByPrimaryKey(HPatient record);
}