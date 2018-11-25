package com.cunjunwang.hospital.services.dbservice;

import com.cunjunwang.hospital.dao.HStaffMapper;
import com.cunjunwang.hospital.model.po.HStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
@Service
public class HStaffDBService {

    @Autowired
    private HStaffMapper staffMapper;

    public HStaff selectById(Long staffId) {
        if(staffId == null) {
            return null;
        }
        return staffMapper.selectByPrimaryKey(staffId);
    }

    public List<HStaff> selectAll() {
        return staffMapper.selectAll();
    }
}
