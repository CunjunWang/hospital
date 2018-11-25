package com.cunjunwang.hospital.init_version_2016.DataAccess.Models;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/11.
 */
public class PatientInfo {

    private static Integer p_ID;
    private static String p_name;
    private static String p_city;
    private static String p_gender;
    private static Integer p_age;
    private static Double p_account_balance;
    private static Boolean p_is_cured;

    // default constructor
    public PatientInfo(){}

    // minimal constructor
    public PatientInfo(Integer p_ID){
        this.p_ID = p_ID;
    }

    // full constructor
    public PatientInfo(Integer p_ID, String p_name, String p_city, String p_gender, Integer p_age,
                       Double p_account_balance, Boolean p_is_cured){
        this.p_ID = p_ID;
        this.p_name = p_name;
        this.p_city = p_city;
        this.p_gender = p_gender;
        this.p_age = p_age;
        this.p_account_balance = p_account_balance;
        this.p_is_cured = p_is_cured;
    }

    public static Integer getP_ID() {
        return p_ID;
    }

    public static String getP_name() {
        return p_name;
    }

    public static String getP_city() {
        return p_city;
    }

    public static String getP_gender() {
        return p_gender;
    }

    public static Integer getP_age() {
        return p_age;
    }

    public static Double getP_account_balance() {
        return p_account_balance;
    }

    public static Boolean getP_is_cured() {
        return p_is_cured;
    }

    public static void setP_ID(Integer p_ID) {
        PatientInfo.p_ID = p_ID;
    }

    public static void setP_name(String p_name) {
        PatientInfo.p_name = p_name;
    }

    public static void setP_city(String p_city) {
        PatientInfo.p_city = p_city;
    }

    public static void setP_gender(String p_gender) {
        PatientInfo.p_gender = p_gender;
    }

    public static void setP_age(Integer p_age) {
        PatientInfo.p_age = p_age;
    }

    public static void setP_account_balance(Double p_account_balance) {
        PatientInfo.p_account_balance = p_account_balance;
    }

    public static void setP_is_cured(Boolean p_is_cured) {
        PatientInfo.p_is_cured = p_is_cured;
    }

    // get one patient data
    public static PatientInfo getPatientInfo(List priKeyList) {
        String where = "p_name='" + priKeyList.get(1) + "'";
        if (getP_ID() != null) {
            where = "p_ID='" + priKeyList.get(0) + "'";
        }
        PatientInfo patientInfo = new PatientInfo();
        ResultSet resultSet = Dao.findForResultSet("SELECT * FROM Patient WHERE " + where);
        try {
            if (resultSet.next()) {
                patientInfo.setP_ID(resultSet.getInt("p_ID"));
                patientInfo.setP_name(resultSet.getString("p_name").trim());
                patientInfo.setP_city(resultSet.getString("p_city").trim());
                patientInfo.setP_gender(resultSet.getString("p_gender").trim());
                patientInfo.setP_age(resultSet.getInt("p_age"));
                patientInfo.setP_account_balance(resultSet.getDouble("p_account_balance"));
                patientInfo.setP_is_cured(resultSet.getBoolean("p_is_cured"));
            }
        } catch (SQLException sqle) {
            System.out.println("Dao::GetPatientInfo...Caught SQLEXception..." + sqle.getMessage());
        } catch (NullPointerException ne) {
            System.out.println("Dao::GetPatientInfo...Caught NullPointerException..." + ne.getMessage());
        }

        return patientInfo;
    }

    // get list of patient data
    public static List getPatientInfos() {
        List list = Dao.findForList("SELECT p_ID,p_name FROM Patient");
        return list;
    }

}
