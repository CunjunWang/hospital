package com.cunjunwang.hospital.emp.services.dbservice;

import com.cunjunwang.hospital.emp.dao.HStaffMapper;
import com.cunjunwang.hospital.emp.model.po.HStaff;
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

    public Boolean updateInfo(HStaff originStaff) {
        if(originStaff == null) {
            return null;
        }
        return staffMapper.updateByPrimaryKeySelective(originStaff) == 1;
    }

    public Long insertInfo(HStaff newStaff) {
        if(newStaff == null) {
            return null;
        }
        staffMapper.insertSelective(newStaff);
        return newStaff.getId();
    }

    public Boolean deleteById(Long staffId) {
        if(staffId == null) {
            return null;
        }
        return staffMapper.deleteByPrimaryKey(staffId) == 1;
    }
}
