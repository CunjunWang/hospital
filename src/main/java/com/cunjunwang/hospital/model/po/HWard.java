package com.cunjunwang.hospital.model.po;

import java.util.Date;

public class HWard {
    private Long id;

    private Long wardRoomNumber;

    private Long wardFloor;

    private Integer wardCapacity;

    private Byte isDel;

    private Date createDate;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;

    private Long wardType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWardRoomNumber() {
        return wardRoomNumber;
    }

    public void setWardRoomNumber(Long wardRoomNumber) {
        this.wardRoomNumber = wardRoomNumber;
    }

    public Long getWardFloor() {
        return wardFloor;
    }

    public void setWardFloor(Long wardFloor) {
        this.wardFloor = wardFloor;
    }

    public Integer getWardCapacity() {
        return wardCapacity;
    }

    public void setWardCapacity(Integer wardCapacity) {
        this.wardCapacity = wardCapacity;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Long getWardType() {
        return wardType;
    }

    public void setWardType(Long wardType) {
        this.wardType = wardType;
    }
}