package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Dao;
import com.cunjunwang.hospital.DataAccess.Models.NurseInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nancyyoung on 2016-11-11.
 */
public class NurseProfileFrame extends JInternalFrame{
    private JLabel IDLabel;
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

    private JLabel isOnJobLabel;
    private JLabel IsOnJobValueLabel;

    private JButton OKButton;

    private String userName;
    private String userType;
    private String userID;

    private int n_ID;
    private String n_name;
    private String n_dept;
    private String n_gender;
    private String n_phone_number;
    private Integer n_year_of_experience;
    private double n_salary;
    private boolean n_is_on_job;

    private Dao dao = new Dao();

    public NurseProfileFrame(String userName, String userType, String userID){
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

        add(getIDLabel());
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
        add(getIsOnJobLabel());
        add(getIsOnJobValueLabel());
        add(getOKButton());
    }

    public void getDoctorData(){
        List nurseInfos = NurseInfo.getNurseInfos();
        int nurseInfosSize = nurseInfos.size();
        for(int i=0; i<nurseInfosSize; i++){
            ArrayList thisData = (ArrayList) nurseInfos.get(i);
            if(thisData.get(0).equals(userID) && thisData.get(1).equals(userName)){
                NurseInfo nurseInfo = NurseInfo.getNurseInfo(thisData);
                // System.out.println(patientInfo);
                n_ID = nurseInfo.getN_ID();
                n_name = nurseInfo.getN_name();
                n_dept = nurseInfo.getN_dept();
                n_gender = nurseInfo.getN_gender();
                n_phone_number = nurseInfo.getN_phone_number();
                n_year_of_experience = nurseInfo.getN_year_of_experience();
                n_salary = nurseInfo.getN_salary();
                n_is_on_job = nurseInfo.getN_is_on_job();

                break;
            }
        }
    }

    public JLabel getIDLabel(){
        if(IDLabel == null){
            IDLabel = new JLabel("ID: ");
            IDLabel.setBounds(10,10,120,30);
        }
        return IDLabel;
    }

    public JLabel getIDValueLabel(){
        if(IDValueLabel == null){
            IDValueLabel = new JLabel();
            if(n_ID == 0){
                IDLabel.setText("No your profile!");
            }
            IDValueLabel.setText(String.valueOf(n_ID));
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
            nameValueLabel = new JLabel(n_name);
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
            deptValueLabel = new JLabel(n_dept);
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
            genderValueLabel = new JLabel(n_gender);
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
            phoneNumberValueLabel = new JLabel(n_phone_number);
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
            experienceValueLabel = new JLabel(String.valueOf(n_year_of_experience));
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
            salaryValueLabel = new JLabel(String.valueOf(n_salary));
            salaryValueLabel.setBounds(150,250,120,30);
        }
        return salaryValueLabel;
    }

    public JLabel getIsOnJobLabel() {
        if(isOnJobLabel == null){
            isOnJobLabel = new JLabel("Salary: ");
            isOnJobLabel.setBounds(10,250,120,30);
        }
        return isOnJobLabel;
    }

    public JLabel getIsOnJobValueLabel() {
        if(IsOnJobValueLabel == null){
            IsOnJobValueLabel = new JLabel(String.valueOf(n_is_on_job));
            IsOnJobValueLabel.setBounds(150,250,120,30);
        }
        return IsOnJobValueLabel;
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
