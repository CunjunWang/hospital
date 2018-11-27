package com.cunjunwang.hospital.model.vo;

import com.cunjunwang.hospital.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by CunjunWang on 2018/11/27.
 */
@Data
public class ModifyWardVO {
    private Long id;

    private Long wardRoomNumber;

    private Long wardFloor;

    private Integer wardCapacity;

    private Byte isDel;

    @JsonFormat(pattern = Constant.FORMAT_DATE, timezone = "GMT+8")
    @Pattern(regexp = "(19|20)[0-9][0-9]-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")
    private Date createDate;

    private Long createBy;

    @JsonFormat(pattern = Constant.FORMAT_DATE, timezone = "GMT+8")
    @Pattern(regexp = "(19|20)[0-9][0-9]-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")
    private Date updateTime;

    private Long updateBy;

    private Long wardType;
}
