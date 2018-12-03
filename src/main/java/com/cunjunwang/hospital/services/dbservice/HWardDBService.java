package com.cunjunwang.hospital.services.dbservice;

import com.cunjunwang.hospital.dao.HMedicineMapper;
import com.cunjunwang.hospital.dao.HWardMapper;
import com.cunjunwang.hospital.model.po.HMedicine;
import com.cunjunwang.hospital.model.po.HWard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Service
public class HWardDBService {

    @Autowired
    private HWardMapper wardMapper;

    public HWard selectById(Long wardId) {
        if (wardId == null) {
            return null;
        }
        return wardMapper.selectByPrimaryKey(wardId);
    }

    public List<HWard> selectAll() {
        return wardMapper.selectAll();
    }

    public Boolean updateInfo(HWard originWard) {
        if (originWard == null) {
            return null;
        }
        return wardMapper.updateByPrimaryKeySelective(originWard) == 1;
    }

    public Long insertInfo(HWard newWard) {
        if(newWard == null) {
            return null;
        }
        wardMapper.insertSelective(newWard);
        return newWard.getId();
    }
}
