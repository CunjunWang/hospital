package com.cunjunwang.hospital.services.business;

import com.cunjunwang.hospital.model.po.HMedicine;
import com.cunjunwang.hospital.model.vo.ModifyMedicineVO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
public interface IMedicineService {

    /**
     * 根据Id获取药品信息
     * @param medicineId
     * @return
     */
    HMedicine getOneMedicineInfo(Long medicineId);

    /**
     * 获取全部药品信息
     * @retur */
    List<HMedicine> getMedicineInfoList();

    /**
     * 添加/更新药品信息
     * @param modifyMedicineVO
     * @return
     */
    Boolean addOrUpdateMedicineInfo(ModifyMedicineVO modifyMedicineVO);

    /**
     * 逻辑删除药品信息
     * @param medicineId
     * @return
     */
    Boolean deleteMedicineInfo(Long medicineId);
}
