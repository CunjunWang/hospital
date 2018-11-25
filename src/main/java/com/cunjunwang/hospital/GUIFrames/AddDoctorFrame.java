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
 * Created by CunjunWang on 16/11/2.
 */
public class AddDoctorFrame extends JInternalFrame {

    private JLabel IDLabel;
    private JTextField IDField;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel departmentLabel;
    private JComboBox<String> departmentBox;

    private JLabel genderLabel;
    private JComboBox<String> genderBox;

    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;

    private JLabel yearOfExperienceLabel;
    private JTextField yearOfExperienceField;

    private JLabel salaryLabel;
    private JTextField salaryField;

    private JLabel isOnJobLabel;
    private JComboBox<String> isOnJobBox;

    private JLabel asteriskLabel1;
    private JLabel asteriskLabel2;
    private JLabel asteriskInfoLabel;

    private JButton submit;
    private JButton cancel;
    private Dao dao = new Dao();

    public AddDoctorFrame(){
        super();
        setClosable(true);
        setBounds(10,10,360,480);
        setTitle("Add Doctor");
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
        add(getDepartmentLabel());
        add(getDepartmentBox());
        add(getGenderLabel());
        add(getGenderBox());
        add(getPhoneNumberLabel());
        add(getPhoneNumberField());
        add(getYearOfExperienceLabel());
        add(getYearOfExperienceArea());
        add(getSalaryLabel());
        add(getSalaryField());
        add(getIsOnJobLabel());
        add(getIsOnJobBox());
        add(getAsteriskLabel1());
        add(getAsteriskLabel2());
        add(getAsteriskInfoLabel());
        add(getSubmit());
        add(getCancel());
    }

    public JLabel getIDLabel(){
        if(IDLabel == null){
            IDLabel = new JLabel("Doctor ID");
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

    public JLabel getDepartmentLabel(){
        if(departmentLabel == null){
            departmentLabel = new JLabel("Department");
            departmentLabel.setBounds(10,90,80,30);
        }
        return departmentLabel;
    }

    public JComboBox<String> getDepartmentBox(){
        if(departmentBox == null){
            String[] departments = {"Out-patient department", "In-patient department",
                    "Nursing department", "Admission office", "Discharge office",
                    "Registration office", "Reception room", "Consultation room",
                    "Isolation room", "Delivery room", "Emergency room", "Ward",
                    "Department of internal medicine", "Department of surgery",
                    "Department of pediatrics", "Department of obstetrics and gynecology",
                    "Department of neurology", "Department of ophtalmology", "E.N.T.department",
                    "Department of stomatology", "Department of urology", "Department of orthopedic",
                    "Department of traumatology", "Department of endocrinology",
                    "Department of anesthesiology", "Department of dermatology",
                    "Department of infectious diseases", "Department of pathology",
                    "Department of psychiatry", "Department of orthopacdic surgery",
                    "Department of cardiac surgery", "Department of cerebral surgery",
                    "Department of thoracic surgery", "Pharmacy dispensary",
                    "Nutrition department", "Diet-preparation department",
                    "Therapeutic department", "Operating room/Theater", "Blood-bank",
                    "Supply-room", "Record room",  "Department of plastic surgery",
                    "Department of physiotherapy", "electrotherapy room", "heliotherapy room",
                    "wax-therapy room", "hydrotherapy room", "central laboratory", "clinical laboratory",
                    "bacteriological laboratory", "biochemical laboratory",
                    "serological laboratory", "X-ray room", "doctor’s office",
                    "nurse’s office"};
            departmentBox = new JComboBox<>(departments);
            departmentBox.setBounds(120,90,200,30);
        }
        return departmentBox;
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

    public JLabel getPhoneNumberLabel() {
        if(phoneNumberLabel == null){
            phoneNumberLabel = new JLabel("Phone#");
            phoneNumberLabel.setBounds(10,170,80,30);
        }
        return phoneNumberLabel;
    }

    public JTextField getPhoneNumberField() {
        if(phoneNumberField == null){
            phoneNumberField = new JTextField();
            phoneNumberField.setBounds(120,170,100,30);
        }
        return phoneNumberField;
    }

    public JLabel getYearOfExperienceLabel() {
        if(yearOfExperienceLabel == null){
            yearOfExperienceLabel = new JLabel("Experience");
            yearOfExperienceLabel.setBounds(10,210,80,30);
        }
        return yearOfExperienceLabel;
    }

    public JTextField getYearOfExperienceArea() {
        if(yearOfExperienceField == null){
            yearOfExperienceField = new JTextField();
            yearOfExperienceField.setBounds(120,210,80,30);
        }
        return yearOfExperienceField;
    }

    public JLabel getSalaryLabel() {
        if(salaryLabel == null){
            salaryLabel = new JLabel("Salary");
            salaryLabel.setBounds(10,250,80,30);
        }
        return salaryLabel;
    }

    public JTextField getSalaryField() {
        if(salaryField == null){
            salaryField = new JTextField();
            salaryField.setBounds(120,250,80,30);
        }
        return salaryField;
    }

    public JLabel getIsOnJobLabel() {
        if(isOnJobLabel == null){
            isOnJobLabel = new JLabel("Is on job?");
            isOnJobLabel.setBounds(10,290,80,30);
        }
        return isOnJobLabel;
    }

    public JComboBox<String> getIsOnJobBox() {
        if(isOnJobBox == null){
            String[] status = {"True","False"};
            isOnJobBox = new JComboBox<>(status);
            isOnJobBox.setBounds(120,290,80,30);
        }
        return isOnJobBox;
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
                    int d_ID = 0;
                    try{
                        d_ID = Integer.parseInt(IDField.getText().trim());

                        if(!checkUniqueDoctorID(d_ID)){
                            JOptionPane.showMessageDialog(null,"This ID already existed!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(d_ID <= 0){
                            JOptionPane.showMessageDialog(null,"Please give a valid ID!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(IDField.getText().equals("")){
                            JOptionPane.showMessageDialog(null,"ID can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        String d_name = nameField.getText().trim();
                        if(nameField.getText().equals("")){
                            JOptionPane.showMessageDialog(null,"Name can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        String d_dept = departmentBox.getSelectedItem().toString().trim();
                        String d_gender = genderBox.getSelectedItem().toString().trim();

                        long d_phone_number = 0;
                        try {
                            d_phone_number = Integer.parseInt(phoneNumberField.getText().trim());
                            if(d_phone_number <= 0){
                                JOptionPane.showMessageDialog(null,"Please give a valid Phone#!","Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            int d_year_of_experience = 0;
                            try{
                                d_year_of_experience = Integer.parseInt(yearOfExperienceField.getText().trim());
                                if(d_year_of_experience <= 0){
                                    JOptionPane.showMessageDialog(null,"Please give a valid number as year!","Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }

                                double d_salary = 0;
                                try{
                                    d_salary = Double.parseDouble(salaryField.getText().trim());
                                    if(d_salary <= 0){
                                        JOptionPane.showMessageDialog(null,"Please give a valid number as salary!","Error",
                                                JOptionPane.ERROR_MESSAGE);
                                    }

                                    String d_is_on_job = isOnJobBox.getSelectedItem().toString().trim();

                                    try {
                                        AddThisDoctor(d_ID, d_name, d_dept,d_gender,d_phone_number,d_year_of_experience,d_salary,d_is_on_job);
                                    }
                                    catch (SQLException sqle){
                                        sqle.getStackTrace();
                                    }
                                }
                                catch(NumberFormatException ne){
                                    JOptionPane.showMessageDialog(null,"Please type number as a Salary!","Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            catch(NumberFormatException ne){
                                JOptionPane.showMessageDialog(null,"Please type number as ID!","Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        catch(NumberFormatException ne){
                            JOptionPane.showMessageDialog(null,"Please type number as Phone#!","Error",
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

    public void AddThisDoctor(int d_ID, String d_name, String d_dept, String d_gender,
                              long d_phone_number, int d_year_of_experience, double d_salary, String d_is_on_job)
            throws SQLException{
        String query = "INSERT INTO Doctor VALUES(" + d_ID + ",'" + d_name + "','" + d_dept + "','" + d_gender
                + "'," + d_phone_number + ",'" + d_year_of_experience + "'," + d_salary + "," + d_is_on_job + ");";
        System.out.println(query);
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public boolean checkUniqueDoctorID(int inputID){
        List existedIDNamelist = dao.findForList("SELECT d_ID,d_name FROM Doctor");
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
