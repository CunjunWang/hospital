package com.cunjunwang.hospital.emp.services.business;

import com.cunjunwang.hospital.emp.model.po.HStaff;
import com.cunjunwang.hospital.emp.model.vo.ModifyStaffVO;
import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
public interface IStaffService {

    /**
     * 根据Id获取职员信息
     * @param staffId
     * @return
     */
    HStaff getOneStaffInfo(Long staffId);

    /**
     * 获取所有职员信息
     * @return
     */
    List<HStaff> getStaffInfoList();

    /**
     * 添加/更新职员信息
     * @param modifyStaffVO
     * @return
     */
    Boolean addOrUpdateStaffInfo(ModifyStaffVO modifyStaffVO);

    /**
     * 删除职员信息
     * @param staffId
     * @return
     */
    Boolean deleteStaffInfo(Long staffId);
}
