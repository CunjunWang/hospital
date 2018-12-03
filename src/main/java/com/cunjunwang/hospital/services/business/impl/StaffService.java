package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.constant.Constant;
import com.cunjunwang.hospital.model.po.HPatient;
import com.cunjunwang.hospital.model.po.HStaff;
import com.cunjunwang.hospital.model.vo.ModifyStaffVO;
import com.cunjunwang.hospital.services.business.IStaffService;
import com.cunjunwang.hospital.services.dbservice.HStaffDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 职员相关业务操作服务
 * Created by CunjunWang on 2018/11/25.
 */
@Service
public class StaffService implements IStaffService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

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
        Long staffId = modifyStaffVO.getId();
        HStaff originStaff = staffDBService.selectById(staffId);
        // 原有改病人信息，更新
        if (originStaff != null) {
            logger.info("开始更新职员[{}]的信息", staffId);
            BeanUtils.copyProperties(modifyStaffVO, originStaff);
            originStaff.setUpdateTime(new Date());
            originStaff.setIsDel(Constant.NOT_DEL);
            Boolean isSuccess = staffDBService.updateInfo(originStaff);
            logger.info("更新职员[{}]的信息成功, {}", staffId, isSuccess);
        }
        // 新增
        else {
            logger.info("开始新增职员[{}]的信息", staffId);
            HStaff newStaff = new HStaff();
            BeanUtils.copyProperties(modifyStaffVO, newStaff);
            newStaff.setCreateTime(new Date());
            newStaff.setIsDel(Constant.NOT_DEL);
            Long newId = staffDBService.insertInfo(newStaff);
            logger.info("新增职员[{}]的信息成功, 新Id: {}", staffId, newId);
        }
        return true;
    }

    @Override
    public Boolean deleteStaffInfo(Long staffId) {
        return staffDBService.deleteById(staffId);
    }
}
