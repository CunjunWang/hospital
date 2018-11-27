package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.model.po.HStaff;
import com.cunjunwang.hospital.model.vo.ModifyStaffVO;
import com.cunjunwang.hospital.services.business.IStaffService;
import com.cunjunwang.hospital.services.dbservice.HStaffDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职员相关业务操作服务
 * Created by CunjunWang on 2018/11/25.
 */
@Service
public class StaffService implements IStaffService {

    @Autowired
    private HStaffDBService staffDBService;

    @Override
    public HStaff getOneStaffInfo(Long staffId) {
        return staffDBService.selectById(staffId);
    }

    @Override
    public List<HStaff> getStaffInfoList() {
        return staffDBService.selectAll();
    }

    @Override
    public Boolean addOrUpdateStaffInfo(ModifyStaffVO modifyStaffVO) {
        return null;
    }

    @Override
    public Boolean deleteStaffInfo(Long staffId) {
        return null;
    }
}
