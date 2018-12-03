package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.constant.Constant;
import com.cunjunwang.hospital.model.po.HStaff;
import com.cunjunwang.hospital.model.po.HWard;
import com.cunjunwang.hospital.model.vo.ModifyWardVO;
import com.cunjunwang.hospital.services.business.IWardService;
import com.cunjunwang.hospital.services.dbservice.HWardDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Service
public class WardService implements IWardService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Autowired
    private HWardDBService wardDBService;

    @Override
    public HWard getOneWardInfo(Long wardId) {
        return wardDBService.selectById(wardId);
    }

    @Override
    public List<HWard> getWardInfoList() {
        return wardDBService.selectAll();
    }

    @Override
    public Boolean addOrUpdateWardInfo(ModifyWardVO modifyWardVO) {
        Long wardId = modifyWardVO.getId();
        HWard originWard = wardDBService.selectById(wardId);
        // 原有该病房信息，更新
        if (originWard != null) {
            logger.info("开始更新病房[{}]的信息", wardId);
            BeanUtils.copyProperties(modifyWardVO, originWard);
            originWard.setUpdateTime(new Date());
            originWard.setIsDel(Constant.NOT_DEL);
            Boolean isSuccess = wardDBService.updateInfo(originWard);
            logger.info("更新病房[{}]的信息成功, {}", wardId, isSuccess);
        }
        // 新增
        else {
            logger.info("开始新增病房[{}]的信息", wardId);
            HWard newWard = new HWard();
            BeanUtils.copyProperties(modifyWardVO, newWard);
            newWard.setCreateDate(new Date());
            newWard.setIsDel(Constant.NOT_DEL);
            Long newId = wardDBService.insertInfo(newWard);
            logger.info("新增职员[{}]的信息成功, 新Id: {}", wardId, newId);
        }
        return true;
    }

    @Override
    public Boolean deleteWardInfo(Long wardId) {
        return null;
    }
}
