package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.DoctorInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



/**
 * * Created by CunjunWang on 16/11/3.
 */
public class DoctorProfileFrame extends JInternalFrame {

    private JLabel IDLable;
    private JLabel IDValueLabel;

    private JLabel nameLabel;
    private JLabel nameValueLabel;

    private JLabel deptLabel;
    private JLabel deptValueLabel;

    private JLabel genderLabel;
    private JLabel genderValueLabel;

    private JLabel phoneNumberLabel;
    private JLabel phoneNumberValueLabel;

    private JLabel experienceLabel;
    private JLabel experienceValueLabel;

    private JLabel salaryLabel;
    private JLabel salaryValueLabel;

    private JButton OKButton;

    private String userName;
    private String userType;
    private String userID;

    private int d_ID;
    private String d_name;
    private String d_dept;
    private String d_gender;
    private String d_phone_number;
    private int d_experience;
    private double d_salary;

    private Dao dao = new Dao();

    public DoctorProfileFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,400,480);
        setTitle("My Profile");
        setLayout(null);
        setVisible(true);

        this.userName = userName;
        this.userType = userType;
        this.userID = userID;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        getDoctorData();

        add(getIDLable());
        add(getIDValueLabel());
        add(getNameLabel());
        add(getNameValueLabel());
        add(getDeptLabel());
        add(getDeptValueLabel());
        add(getGenderLabel());
        add(getGenderValueLabel());
        add(getPhoneNumberLabel());
        add(getPhoneNumberValueLabel());
        add(getExperienceLabel());
        add(getExperienceValueLabel());
        add(getSalaryLabel());
        add(getSalaryValueLabel());
        add(getOKButton());
    }

    public void getDoctorData(){
        List doctorInfos = DoctorInfo.getDoctorInfos();
        int doctorInfosSize = doctorInfos.size();
        for(int i=0; i<doctorInfosSize; i++){
            ArrayList thisData = (ArrayList) doctorInfos.get(i);
            if(thisData.get(0).equals(userID) && thisData.get(1).equals(userName)){
                DoctorInfo doctorInfo = DoctorInfo.getDoctorInfo(thisData);
                // System.out.println(patientInfo);
                d_ID = doctorInfo.getD_ID();
                d_name = doctorInfo.getD_name();
                d_dept = doctorInfo.getD_dept();
                d_gender = doctorInfo.getD_gender();
                d_phone_number = doctorInfo.getD_phone_number();
                d_experience = doctorInfo.getD_year_of_experience();
                d_salary = doctorInfo.getD_salary();

                break;
            }
        }
    }

    public JLabel getIDLable(){
        if(IDLable == null){
            IDLable = new JLabel("ID: ");
            IDLable.setBounds(10,10,120,30);
        }
        return IDLable;
    }

    public JLabel getIDValueLabel(){
        if(IDValueLabel == null){
            IDValueLabel = new JLabel();
            if(d_ID == 0){
                IDLable.setText("No your profile!");
            }
            IDValueLabel.setText(String.valueOf(d_ID));
            IDValueLabel.setBounds(150,10,120,30);
        }
        return IDValueLabel;
    }

    public JLabel getNameLabel() {
        if(nameLabel == null){
            nameLabel = new JLabel("Name: ");
            nameLabel.setBounds(10,50,120,30);
        }
        return nameLabel;
    }

    public JLabel getNameValueLabel() {
        if(nameValueLabel == null){
            nameValueLabel = new JLabel(d_name);
            nameValueLabel.setBounds(150,50,120,30);
        }
        return nameValueLabel;
    }

    public JLabel getDeptLabel() {
        if(deptLabel == null){
            deptLabel = new JLabel("Department: ");
            deptLabel.setBounds(10,90,120,30);
        }
        return deptLabel;
    }

    public JLabel getDeptValueLabel() {
        if(deptValueLabel == null){
            deptValueLabel = new JLabel(d_dept);
            deptValueLabel.setBounds(150,90,200,30);
        }
        return deptValueLabel;
    }

    public JLabel getGenderLabel() {
        if(genderLabel == null){
            genderLabel = new JLabel("Gender: ");
            genderLabel.setBounds(10,130,120,30);
        }
        return genderLabel;
    }

    public JLabel getGenderValueLabel() {
        if(genderValueLabel == null){
            genderValueLabel = new JLabel(d_gender);
            genderValueLabel.setBounds(150,130,120,30);
        }
        return genderValueLabel;
    }

    public JLabel getPhoneNumberLabel() {
        if(phoneNumberLabel == null){
            phoneNumberLabel = new JLabel("Phone#: ");
            phoneNumberLabel.setBounds(10,170,120,30);
        }
        return phoneNumberLabel;
    }

    public JLabel getPhoneNumberValueLabel() {
        if(phoneNumberValueLabel == null){
            phoneNumberValueLabel = new JLabel(d_phone_number);
            phoneNumberValueLabel.setBounds(150,170,120,30);
        }
        return phoneNumberValueLabel;
    }

    public JLabel getExperienceLabel() {
        if(experienceLabel == null){
            experienceLabel = new JLabel("Experience: ");
            experienceLabel.setBounds(10,210,120,30);
        }
        return experienceLabel;
    }

    public JLabel getExperienceValueLabel() {
        if(experienceValueLabel == null){
            experienceValueLabel = new JLabel(String.valueOf(d_experience));
            experienceValueLabel.setBounds(150,210,200,30);
        }
        return experienceValueLabel;
    }

    public JLabel getSalaryLabel() {
        if(salaryLabel == null){
            salaryLabel = new JLabel("Salary: ");
            salaryLabel.setBounds(10,250,120,30);
        }
        return salaryLabel;
    }

    public JLabel getSalaryValueLabel() {
        if(salaryValueLabel == null){
            salaryValueLabel = new JLabel(String.valueOf(d_salary));
            salaryValueLabel.setBounds(150,250,120,30);
        }
        return salaryValueLabel;
    }

    public JButton getOKButton() {
        if(OKButton == null){
            OKButton = new JButton("OK");
            OKButton.setBounds(120,390,80,30);
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return OKButton;
    }

}