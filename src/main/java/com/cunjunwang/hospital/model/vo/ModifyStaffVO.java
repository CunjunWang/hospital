package com.cunjunwang.hospital.model.vo;

import com.cunjunwang.hospital.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Data
@ApiModel(description = "添加/更新职员信息VO")
public class ModifyStaffVO {

    private Long id;

    private String staffUuid;

    private String staffName;

    private Integer staffAge;

    private Long staffDepartment;

    private Integer staffType;

    private Integer staffGender;

    private Byte staffIsOnJob;

    private Integer staffExperience;

    private String staffDescription;

    private Byte isDel;

    @JsonFormat(pattern = Constant.FORMAT_DATE, timezone = "GMT+8")
    @Pattern(regexp = "(19|20)[0-9][0-9]-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")
    private Date createTime;

    private Long createBy;

    @JsonFormat(pattern = Constant.FORMAT_DATE, timezone = "GMT+8")
    @Pattern(regexp = "(19|20)[0-9][0-9]-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")
    private Date updateTime;

    private Long updateBy;
}
