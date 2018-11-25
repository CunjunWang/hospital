package com.cunjunwang.hospital.DataAccess.Models;

import com.cunjunwang.hospital.DataAccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/12.
 */
public class MedicineInfo {

    private static Integer m_ID;
    private static Double m_price;
    private static String m_name;
    private static Integer m_stock;
    private static String m_description;

    // default constructor
    public MedicineInfo(){}

    // minimal constructor
    public MedicineInfo(int m_ID){
        this.m_ID = m_ID;
    }

    // full constructor
    public MedicineInfo(int m_ID, double m_price, String m_name, int m_stock, String m_description){
        this.m_ID = m_ID;
        this.m_price = m_price;
        this.m_name = m_name;
        this.m_stock = m_stock;
        this.m_description = m_description;
    }

    public static Integer getM_ID() {
        return m_ID;
    }

    public static String getM_name() {
        return m_name;
    }

    public static Double getM_price() {
        return m_price;
    }

    public static Integer getM_stock() {
        return m_stock;
    }

    public static String getM_description() {
        return m_description;
    }

    public void setM_ID(Integer m_ID) {
        this.m_ID = m_ID;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public void setM_stock(Integer m_stock) {
        this.m_stock = m_stock;
    }

    public void setM_price(Double m_price) {
        this.m_price = m_price;
    }

    public void setM_description(String m_description) {
        this.m_description = m_description;
    }

    // get one medicine data
    public static MedicineInfo getMedicineInfo(List priKeyList) {
        String where = "m_name='" + priKeyList.get(1) + "'";
        if (getM_ID() != null) {
            where = "m_ID='" + priKeyList.get(0) + "'";
        }
        MedicineInfo medicineInfo = new MedicineInfo();
        ResultSet resultSet = Dao.findForResultSet("SELECT * FROM Medicine WHERE " + where);
        try {
            if (resultSet.next()) {
                medicineInfo.setM_ID(resultSet.getInt("m_ID"));
                medicineInfo.setM_name(resultSet.getString("m_name").trim());
                medicineInfo.setM_stock(resultSet.getInt("m_stock"));
                medicineInfo.setM_price(resultSet.getDouble("m_price"));
                medicineInfo.setM_description(resultSet.getString("m_description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicineInfo;
    }

    // get list of medicine data
    public static List getMedicineInfos() {
        List list = Dao.findForList("SELECT m_ID,m_name FROM Medicine");
        return list;
    }

}
