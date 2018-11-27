package com.cunjunwang.hospital.dao;

import com.cunjunwang.hospital.model.po.HWard;
import org.springframework.stereotype.Repository;

@Repository
public interface HWardMapper {
    int insert(HWard record);

    int insertSelective(HWard record);

    HWard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HWard record);

    int updateByPrimaryKey(HWard record);
}