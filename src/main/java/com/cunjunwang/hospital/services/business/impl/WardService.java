package com.cunjunwang.hospital.services.business.impl;

import com.cunjunwang.hospital.model.po.HWard;
import com.cunjunwang.hospital.model.vo.ModifyWardVO;
import com.cunjunwang.hospital.services.business.IWardService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Service
public class WardService implements IWardService {

    @Override
    public HWard getOneWardInfo(Long wardId) {
        return null;
    }

    @Override
    public List<HWard> getWardInfoList() {
        return null;
    }

    @Override
    public Boolean addOrUpdateWardInfo(ModifyWardVO modifyWardVO) {
        return null;
    }

    @Override
    public Boolean deleteWardInfo(Long wardId) {
        return null;
    }
}
