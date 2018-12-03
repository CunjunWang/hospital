package com.cunjunwang.hospital.dao;

import com.cunjunwang.hospital.model.po.HStaff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HStaffMapper {
    int insert(HStaff record);

    int insertSelective(HStaff record);

    HStaff selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HStaff record);

    int updateByPrimaryKey(HStaff record);

    List<HStaff> selectAll();

    int deleteByPrimaryKey(@Param("staffId") Long staffId);
}