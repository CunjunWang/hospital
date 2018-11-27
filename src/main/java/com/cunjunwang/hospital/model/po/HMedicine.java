package com.cunjunwang.hospital.model.po;

import java.util.Date;

public class HMedicine {
    private Long id;

    private String medicineName;

    private String medicineDescription;

    private Short medicinePrice;

    private Long medicineStock;

    private Byte medicineType;

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

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName == null ? null : medicineName.trim();
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription == null ? null : medicineDescription.trim();
    }

    public Short getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Short medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public Long getMedicineStock() {
        return medicineStock;
    }

    public void setMedicineStock(Long medicineStock) {
        this.medicineStock = medicineStock;
    }

    public Byte getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(Byte medicineType) {
        this.medicineType = medicineType;
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