package com.cunjunwang.hospital.model.po;

import java.util.Date;

public class HStaff {
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

    private Date createTime;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffUuid() {
        return staffUuid;
    }

    public void setStaffUuid(String staffUuid) {
        this.staffUuid = staffUuid == null ? null : staffUuid.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Integer getStaffAge() {
        return staffAge;
    }

    public void setStaffAge(Integer staffAge) {
        this.staffAge = staffAge;
    }

    public Long getStaffDepartment() {
        return staffDepartment;
    }

    public void setStaffDepartment(Long staffDepartment) {
        this.staffDepartment = staffDepartment;
    }

    public Integer getStaffType() {
        return staffType;
    }

    public void setStaffType(Integer staffType) {
        this.staffType = staffType;
    }

    public Integer getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(Integer staffGender) {
        this.staffGender = staffGender;
    }

    public Byte getStaffIsOnJob() {
        return staffIsOnJob;
    }

    public void setStaffIsOnJob(Byte staffIsOnJob) {
        this.staffIsOnJob = staffIsOnJob;
    }

    public Integer getStaffExperience() {
        return staffExperience;
    }

    public void setStaffExperience(Integer staffExperience) {
        this.staffExperience = staffExperience;
    }

    public String getStaffDescription() {
        return staffDescription;
    }

    public void setStaffDescription(String staffDescription) {
        this.staffDescription = staffDescription == null ? null : staffDescription.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}