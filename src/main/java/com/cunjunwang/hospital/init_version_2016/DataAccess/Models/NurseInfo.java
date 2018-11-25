package com.cunjunwang.hospital.init_version_2016.DataAccess.Models;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/12.
 */
public class NurseInfo{

    private static Integer n_ID;
    private static String n_name;
    private static String n_dept;
    private static String n_gender;
    private static String n_phone_number;
    private static Integer n_year_of_experience;
    private static Double n_salary;
    private static Boolean n_is_on_job;

    // default constructor
    public NurseInfo(){}

    // minimal constructor
    public NurseInfo(int n_ID){
        this.n_ID = n_ID;
    }

    // full constructor
    public NurseInfo(int n_ID, String n_name, String n_dept, String n_gender, String n_phone_number,
                     int year, double n_salary, boolean n_is_on_job){
        this.n_ID = n_ID;
        this.n_name = n_name;
        this.n_dept = n_dept;
        this.n_gender = n_gender;
        this.n_phone_number = n_phone_number;
        this.n_year_of_experience = year;
        this.n_salary = n_salary;
        this.n_is_on_job = n_is_on_job;
    }

    public static Integer getN_ID() {
        return n_ID;
    }

    public static String getN_name() {
        return n_name;
    }

    public static String getN_phone_number() {
        return n_phone_number;
    }

    public static String getN_gender() {
        return n_gender;
    }

    public static Double getN_salary() {
        return n_salary;
    }

    public static Boolean getN_is_on_job() {return n_is_on_job;}

    public static Integer getN_year_of_experience() {return n_year_of_experience;}

    public static String getN_dept() {return n_dept;}

    public void setN_ID(Integer n_ID) {
        this.n_ID = n_ID;
    }

    public void setN_name(String n_name) {
        this.n_name = n_name;
    }

    public void setN_gender(String n_gender) {
        this.n_gender = n_gender;
    }

    public void setN_salary(Double n_salary) {
        this.n_salary = n_salary;
    }

    public void setN_phone_number(String n_phone_number) {
        this.n_phone_number = n_phone_number;
    }

    public void setN_is_on_job(Boolean n_is_on_job) {this.n_is_on_job = n_is_on_job;}

    public void setN_year_of_experience(Integer n_year_of_experience) {this.n_year_of_experience = n_year_of_experience;}

    public void setN_dept(String dept) {this.n_dept = dept;}

    // get one nurse data
    public static NurseInfo getNurseInfo(List priKeyList) {
        String where = "n_name='" + priKeyList.get(1) + "'";
        if (getN_ID() != null) {
            where = "n_ID='" + priKeyList.get(0) + "'";
        }
        NurseInfo nurseInfo = new NurseInfo();
        ResultSet resultSet = Dao.findForResultSet("SELECT * FROM Nurse WHERE " + where);
        try {
            if (resultSet.next()) {
                nurseInfo.setN_ID(resultSet.getInt("n_ID"));
                nurseInfo.setN_name(resultSet.getString("n_name").trim());
                nurseInfo.setN_dept(resultSet.getString("n_dept").trim());
                nurseInfo.setN_gender(resultSet.getString("n_gender").trim());
                nurseInfo.setN_phone_number(resultSet.getString("n_phone_number").trim());
                nurseInfo.setN_year_of_experience(resultSet.getInt("n_year_of_experience"));
                nurseInfo.setN_salary(resultSet.getDouble("n_salary"));
                nurseInfo.setN_is_on_job(resultSet.getBoolean("n_is_on_job"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurseInfo;
    }

    // get list of nurse data
    public static List getNurseInfos() {
        List list = Dao.findForList("SELECT n_ID,n_name FROM Nurse");
        return list;
    }

}
