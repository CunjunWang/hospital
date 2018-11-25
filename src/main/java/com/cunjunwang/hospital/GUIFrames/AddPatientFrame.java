package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by nancyyoung on 2016-11-03.
 */

public class AddPatientFrame extends JInternalFrame {

    private JLabel IDLabel;
    private JTextField IDField;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel cityLabel;
    private JTextField cityField;

    private JLabel genderLabel;
    private JComboBox<String> genderBox;

    private JLabel ageLabel;
    private JTextField ageField;

    private JLabel accountBalanceLabel;
    private JTextField accountBalanceField;

    private JLabel isCuredLabel;
    private JComboBox<String> isCuredBox;

    private JLabel asteriskLabel1;
    private JLabel asteriskLabel2;
    private JLabel asteriskInfoLabel;

    private JButton submit;
    private JButton cancel;
    private Dao dao = new Dao();

    public AddPatientFrame(){
        super();
        setClosable(true);
        setBounds(10,10,360,480);
        setTitle("Add Patient");
        setLayout(null);
        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(getIDLabel());
        add(getIDField());
        add(getNameLabel());
        add(getNameField());
        add(getCityLabel());
        add(getCityField());
        add(getGenderLabel());
        add(getGenderBox());
        add(getAgeLabel());
        add(getAgeField());
        add(getAccountBalanceLabel());
        add(getAccountBalanceField());
        add(getIsCuredLabel());
        add(getIsCuredBox());
        add(getAsteriskLabel1());
        add(getAsteriskLabel2());
        add(getAsteriskInfoLabel());
        add(getSubmit());
        add(getCancel());
    }

    public JLabel getIDLabel(){
        if(IDLabel == null){
            IDLabel = new JLabel("Nurse ID");
            IDLabel.setBounds(10,10,80,30);
        }
        return IDLabel;
    }

    public JTextField getIDField() {
        if(IDField == null){
            IDField = new JTextField();
            IDField.setBounds(120,10,80,30);
        }
        return IDField;
    }

    public JLabel getNameLabel(){
        if(nameLabel == null){
            nameLabel = new JLabel("Name");
            nameLabel.setBounds(10,50,80,30);
        }
        return nameLabel;
    }

    public JTextField getNameField(){
        if(nameField == null){
            nameField = new JTextField();
            nameField.setBounds(120,50,80,30);
        }
        return nameField;
    }

    public JLabel getCityLabel() {
        if(cityLabel == null){
            cityLabel = new JLabel("City:");
            cityLabel.setBounds(10,90,80,30);
        }
        return cityLabel;
    }

    public JTextField getCityField() {
        if(cityField == null){
            cityField = new JTextField();
            cityField.setBounds(120,90,80,30);
        }
        return cityField;
    }

    public JLabel getGenderLabel(){
        if(genderLabel == null){
            genderLabel = new JLabel("Gender");
            genderLabel.setBounds(10,130,80,30);
        }
        return genderLabel;
    }

    public JComboBox<String> getGenderBox(){
        if(genderBox == null){
            String[] genders = {"Male","Female"};
            genderBox = new JComboBox<>(genders);
            genderBox.setBounds(120,130,100,30);
        }
        return genderBox;
    }

    public JLabel getAgeLabel() {
        if(ageLabel == null){
            ageLabel = new JLabel("Age:");
            ageLabel.setBounds(10,170,80,30);
        }
        return ageLabel;
    }

    public JTextField getAgeField() {
        if(ageField == null){
            ageField = new JTextField();
            ageField.setBounds(120,170,80,30);
        }
        return ageField;
    }

    public JLabel getAccountBalanceLabel() {
        if(accountBalanceLabel == null){
            accountBalanceLabel = new JLabel("Balance:");
            accountBalanceLabel.setBounds(10,210,80,30);
        }
        return accountBalanceLabel;
    }

    public JTextField getAccountBalanceField() {
        if(accountBalanceField == null){
            accountBalanceField = new JTextField();
            accountBalanceField.setBounds(120,210,80,30);
        }
        return accountBalanceField;
    }

    public JLabel getIsCuredLabel() {
        if(isCuredLabel == null){
            isCuredLabel = new JLabel("Is cured?");
            isCuredLabel.setBounds(10,250,80,30);
        }
        return isCuredLabel;
    }

    public JComboBox<String> getIsCuredBox() {
        if(isCuredBox == null){
            String[] status = {"True","False"};
            isCuredBox = new JComboBox<>(status);
            isCuredBox.setBounds(120,250,80,30);
        }
        return isCuredBox;
    }

    public JLabel getAsteriskLabel1() {
        if(asteriskLabel1 == null){
            asteriskLabel1 = new JLabel("*");
            asteriskLabel1.setBounds(210,10,30,30);
            asteriskLabel1.setForeground(Color.RED);
        }
        return asteriskLabel1;
    }

    public JLabel getAsteriskLabel2() {
        if(asteriskLabel2 == null){
            asteriskLabel2 = new JLabel("*");
            asteriskLabel2.setBounds(210,50,30,30);
            asteriskLabel2.setForeground(Color.RED);
        }
        return asteriskLabel2;
    }

    public JLabel getAsteriskInfoLabel(){
        if(asteriskInfoLabel == null){
            asteriskInfoLabel = new JLabel("<html><font color='red'>'*'</font> means can not missing.</html>");
            asteriskInfoLabel.setBounds(150,400,180,30);
        }
        return asteriskInfoLabel;
    }

    public JButton getSubmit(){
        if(submit == null){
            submit = new JButton("Submit");
            submit.setBounds(190,360,80,30);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int p_ID = 0;
                    try{
                        p_ID = Integer.parseInt(IDField.getText().trim());

                        if(!CheckUniquePatientID(p_ID)){
                            JOptionPane.showMessageDialog(null,"This ID already existed!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(p_ID <= 0){
                            JOptionPane.showMessageDialog(null,"Please give a valid number as ID!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(IDField.getText().equals("")){
                            JOptionPane.showMessageDialog(null,"ID can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        String p_name = nameField.getText().trim();
                        if(nameField.getText().equals("")){
                            JOptionPane.showMessageDialog(null,"Name can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        String p_city = cityField.getText().trim();
                        String p_gender = genderBox.getSelectedItem().toString().trim();

                        int p_age = 0;
                        try {
                            p_age = Integer.parseInt(ageField.getText().trim());
                            if(p_age <= 0){
                                JOptionPane.showMessageDialog(null,"Please give a valid number as age!","Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            double p_account_balance = 0;
                            try{
                                p_account_balance = Double.parseDouble(accountBalanceField.getText().trim());
                                if(p_account_balance <= 0){
                                    JOptionPane.showMessageDialog(null,"Please give a valid number as balance!","Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                                String p_is_cured = isCuredBox.getSelectedItem().toString().trim();

                                try {
                                    AddThisPatient(p_ID, p_name, p_city, p_gender,p_age,p_account_balance, p_is_cured);
                                }
                                catch (SQLException sqle){
                                    sqle.getStackTrace();
                                }
                            }
                            catch(NumberFormatException ne){
                                JOptionPane.showMessageDialog(null,"Please type number as balance!","Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        catch(NumberFormatException ne){
                            JOptionPane.showMessageDialog(null,"Please type number as age!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(NumberFormatException ne){
                        JOptionPane.showMessageDialog(null,"Please type number as ID!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }
        return submit;
    }

    public JButton getCancel(){
        if(cancel == null){
            cancel = new JButton("Cancel");
            cancel.setBounds(80,360,80,30);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancel;
    }

    public void AddThisPatient(int p_ID, String p_name, String p_city, String p_gender,
                               int p_age, double p_account_balance, String p_is_cured)
            throws SQLException{
        String query = "INSERT INTO Patient VALUES(" + p_ID + ",'" + p_name + "','" + p_city + "','" + p_gender
                + "'," + p_age + "," + p_account_balance + "," + p_is_cured + ");";
        System.out.println(query);
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public boolean CheckUniquePatientID(int inputID){
        List existedIDNamelist = dao.findForList("SELECT p_ID,p_name FROM Patient");
        int existedDataSize = existedIDNamelist.size();
        ArrayList<Integer> existedIDList = new ArrayList<>();
        for(int i=0; i<existedDataSize; i++){
            ArrayList thisData = (ArrayList) existedIDNamelist.get(i);
            int thisID = Integer.parseInt((String) thisData.get(0));
            existedIDList.add(thisID);
        }

        if(existedIDList.contains(inputID)){
            return false;
        }
        else{
            return true;
        }
    }
}

