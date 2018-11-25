package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.PatientInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/3.
 */
public class MyProfileFrame extends JInternalFrame {

    private JLabel IDLable;
    private JLabel IDValueLabel;

    private JLabel nameLabel;
    private JLabel nameValueLabel;

    private JLabel cityLabel;
    private JLabel cityValueLabel;

    private JLabel genderLabel;
    private JLabel genderValueLabel;

    private JLabel ageLabel;
    private JLabel ageValueLabel;

    private JLabel roomLabel;
    private JLabel roomValueLabel;

    private JLabel balanceLabel;
    private JLabel balanceValueLabel;

    private JButton OKButton;

    private String userName;
    private String userType;
    private String userID;

    private int p_ID;
    private String p_name;
    private String p_city;
    private String p_gender;
    private int p_age;
    private int roomNumber;
    private double p_account_balance;

    private Dao dao = new Dao();

    public MyProfileFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,360,400);
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

        getPatientData();

        add(getIDLable());
        add(getIDValueLabel());
        add(getNameLabel());
        add(getNameValueLabel());
        add(getRoomLabel());
        add(getRoomValueLabel());
        add(getAgeLabel());
        add(getAgeValueLabel());
        add(getGenderLabel());
        add(getGenderValueLabel());
        add(getAddressLabel());
        add(getCityValueLabel());
        add(getBalanceLabel());
        add(getBalanceValueLabel());
        add(getOKButton());
    }

    public void getPatientData(){
        List patientInfos = PatientInfo.getPatientInfos();
        int patientInfosSize = patientInfos.size();
        for(int i=0; i<patientInfosSize; i++){
            ArrayList thisData = (ArrayList) patientInfos.get(i);

            if(thisData.get(0).equals(userID) && thisData.get(1).equals(userName)){
                PatientInfo patientInfo = PatientInfo.getPatientInfo(thisData);
                // System.out.println(patientInfo);
                p_ID = patientInfo.getP_ID();
                p_name = patientInfo.getP_name();
                p_age = patientInfo.getP_age();
                p_gender = patientInfo.getP_gender();
                p_city = patientInfo.getP_city();
                roomNumber = Integer.parseInt( (String) ((ArrayList) getMyRoom().get(0)).get(0));
                p_account_balance = patientInfo.getP_account_balance();
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
            if(p_ID == 0){
                IDLable.setText("No your profile!");
            }
            IDValueLabel.setText(String.valueOf(p_ID));
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
            nameValueLabel = new JLabel(p_name);
            nameValueLabel.setBounds(150,50,120,30);
        }
        return nameValueLabel;
    }

    public JLabel getRoomLabel(){
        if(roomLabel == null){
            roomLabel = new JLabel("Room#");
            roomLabel.setBounds(10,90,120,30);
        }
        return roomLabel;
    }

    public JLabel getRoomValueLabel(){
        if(roomValueLabel == null){
            roomValueLabel = new JLabel(String.valueOf(roomNumber));
            roomValueLabel.setBounds(150,90,120,30);
        }
        return roomValueLabel;
    }

    public JLabel getAgeLabel() {
        if(ageLabel == null) {
            ageLabel = new JLabel("Age: ");
            ageLabel.setBounds(10,130,120,30);
        }
        return ageLabel;
    }

    public JLabel getAgeValueLabel() {
        if(ageValueLabel == null){
            ageValueLabel = new JLabel(String.valueOf(p_age));
            ageValueLabel.setBounds(150,130,120,30);
        }
        return ageValueLabel;
    }

    public JLabel getGenderLabel() {
        if(genderLabel == null){
            genderLabel = new JLabel("Gender: ");
            genderLabel.setBounds(10,170,120,30);
        }
        return genderLabel;
    }

    public JLabel getGenderValueLabel() {
        if(genderValueLabel == null){
            genderValueLabel = new JLabel(p_gender);
            genderValueLabel.setBounds(150,170,120,30);
        }
        return genderValueLabel;
    }

    public JLabel getAddressLabel() {
        if(cityLabel == null){
            cityLabel = new JLabel("Address: ");
            cityLabel.setBounds(10,210,120,30);
        }
        return cityLabel;
    }

    public JLabel getCityValueLabel() {
        if(cityValueLabel == null){
            cityValueLabel = new JLabel(p_city);
            cityValueLabel.setBounds(150,210,120,30);
        }
        return cityValueLabel;
    }

    public JLabel getBalanceLabel() {
        if(balanceLabel == null){
            balanceLabel = new JLabel("Balance: ");
            balanceLabel.setBounds(10,250,120,30);
        }
        return balanceLabel;
    }

    public JLabel getBalanceValueLabel() {
        if(balanceValueLabel == null){
            balanceValueLabel = new JLabel(String.valueOf(p_account_balance));
            balanceValueLabel.setBounds(150,250,120,30);
        }
        return balanceValueLabel;
    }

    public JButton getOKButton() {
        if(OKButton == null){
            OKButton = new JButton("OK");
            OKButton.setBounds(125,310,80,30);
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return OKButton;
    }

    public List getMyRoom(){
        String sql = "SELECT room_number from Room_livein WHERE Room_livein.r_p_ID=" + p_ID + ";";
        List list = Dao.findForList(sql);
        return list;
    }

}
