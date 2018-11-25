package com.cunjunwang.hospital.DataAccess.Models;


import com.cunjunwang.hospital.DataAccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/12.
 */
public class DoctorInfo {
    private static Integer d_ID;
    private static String d_name;
    private static String d_dept;
    private static String d_gender;
    private static String d_phone_number;
    private static Integer d_year_of_experience;
    private static Double d_salary;
    private static Boolean d_is_on_job;

    // default constructor
    public DoctorInfo(){}

    // minimal constructor
    public DoctorInfo(int d_ID){
        this.d_ID = d_ID;
    }

    // full constructor
    public DoctorInfo(int d_ID, String d_name, String d_dept, String d_gender, String d_phone_number, int d_year_of_experience,
                      double d_salary, boolean d_is_on_job){
        this.d_ID = d_ID;
        this.d_name = d_name;
        this.d_dept = d_dept;
        this.d_gender = d_gender;
        this.d_phone_number = d_phone_number;
        this.d_year_of_experience = d_year_of_experience;
        this.d_salary = d_salary;
        this.d_is_on_job = d_is_on_job;
    }

    public static Integer getD_ID() {
        return d_ID;
    }

    public static String getD_name() {
        return d_name;
    }

    public static String getD_dept() {
        return d_dept;
    }

    public static String getD_gender() {
        return d_gender;
    }

    public static String getD_phone_number() {
        return d_phone_number;
    }

    public static Integer getD_year_of_experience() {
        return d_year_of_experience;
    }

    public static Double getD_salary() {
        return d_salary;
    }

    public static Boolean getD_is_on_job() {
        return d_is_on_job;
    }

    public void setD_ID(int d_ID) {
        this.d_ID = d_ID;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public void setD_dept(String d_dept) {
        this.d_dept = d_dept;
    }

    public void setD_gender(String d_gender) {
        this.d_gender = d_gender;
    }


    public void setD_phone_number(String d_phone_number) {
        this.d_phone_number = d_phone_number;
    }

    public void setD_year_of_experience(Integer d_year_of_experience) {
        this.d_year_of_experience = d_year_of_experience;
    }

    public void setD_salary(Double d_salary) {
        this.d_salary = d_salary;
    }

    public static void setD_is_on_job(Boolean d_is_on_job) {
        DoctorInfo.d_is_on_job = d_is_on_job;
    }

    // get one doctor data
    public static DoctorInfo getDoctorInfo(List priKeyList) {
        String where = "d_name='" + priKeyList.get(1) + "'";
        if (getD_ID() != null) {
            where = "d_ID='" + priKeyList.get(0) + "'";
        }
        DoctorInfo doctorInfo = new DoctorInfo();
        ResultSet resultSet = Dao.findForResultSet("SELECT * FROM Doctor WHERE " + where);
        try {
            if (resultSet.next()) {
                doctorInfo.setD_ID(resultSet.getInt("d_ID"));
                doctorInfo.setD_name(resultSet.getString("d_name").trim());
                doctorInfo.setD_dept(resultSet.getString("d_dept").trim());
                doctorInfo.setD_gender(resultSet.getString("d_gender").trim());
                doctorInfo.setD_phone_number(resultSet.getString("d_phone_number").trim());
                doctorInfo.setD_year_of_experience(resultSet.getInt("d_year_of_experience"));
                doctorInfo.setD_salary(resultSet.getDouble("d_salary"));
                doctorInfo.setD_is_on_job(resultSet.getBoolean("d_is_on_job"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorInfo;
    }

    // get multiple doctors data
    public static List getDoctorInfos() {
        List list = Dao.findForList("SELECT d_ID,d_name FROM Doctor");
        return list;
    }

}
