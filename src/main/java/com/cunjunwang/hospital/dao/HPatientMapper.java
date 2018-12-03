package com.cunjunwang.hospital.dao;

import com.cunjunwang.hospital.model.po.HPatient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HPatientMapper {
    int insert(HPatient record);

    int insertSelective(HPatient record);

    HPatient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HPatient record);

    int updateByPrimaryKey(HPatient record);

    List<HPatient> selectAll();

    int deleteByPrimaryKey(@Param("patientId") Long patientId);
}