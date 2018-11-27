package com.cunjunwang.hospital.services.business;

import com.cunjunwang.hospital.model.po.HWard;
import com.cunjunwang.hospital.model.vo.ModifyWardVO;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/27.
 */
public interface IWardService {

    /**
     * 根据Id获取病房信息
     * @param wardId
     * @return
     */
    HWard getOneWardInfo(Long wardId);

    /**
     * 获取所有病房信息
     * @return
     */
    List<HWard> getWardInfoList();

    /**
     * 添加/删除病房信息
     * @param modifyWardVO
     * @return
     */
    Boolean addOrUpdateWardInfo(ModifyWardVO modifyWardVO);

    /**
     * 逻辑删除病房信息
     * @param wardId
     * @return
     */
    Boolean deleteWardInfo(Long wardId);
}
