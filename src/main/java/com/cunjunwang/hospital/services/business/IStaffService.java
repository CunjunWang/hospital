package com.cunjunwang.hospital.services.business;

import com.cunjunwang.hospital.model.po.HStaff;

import java.util.List;

/**
 * Created by CunjunWang on 2018/11/25.
 */
public interface IStaffService {

    HStaff getOneStaffInfo(Long staffId);

    List<HStaff> getStaffInfoList();

}
