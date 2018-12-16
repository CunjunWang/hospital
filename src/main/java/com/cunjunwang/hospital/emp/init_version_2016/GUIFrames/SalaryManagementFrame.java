package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/30.
 */
public class SalaryManagementFrame extends JInternalFrame{

    private JLabel clerkType;
    private ButtonGroup buttonGroup;
    private JRadioButton doctorType;
    private JRadioButton nurseType;

    private JLabel clerkID;
    private JTextField clerkIDField;

    private JLabel newSalaryLabel;
    private JTextField newSalaryField;

    private JButton cancel;
    private JButton submit;

    private double newSalary;
    private String toAdjustSalaryClerkID;
    private String toQueryTable;

    private Dao dao = new Dao();

    public SalaryManagementFrame(){
        super();
        setClosable(true);
        setBounds(10,10,350,240);
        setTitle("Salary Management");
        setLayout(null);
        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(getClerkType());
        getButtonGroup();
        add(getDoctorType());
        add(getNurseType());

        add(getClerkID());
        add(getClerkIDField());

        add(getNewSalaryLabel());
        add(getNewSalaryField());

        add(getCancel());
        add(getSubmit());
    }

    public JLabel getClerkType(){
        if(clerkType == null){
            clerkType = new JLabel("Clerk Type: ");
            clerkType.setBounds(10,10,80,30);
        }
        return clerkType;
    }

    public JRadioButton getDoctorType(){
        if(doctorType == null){
            doctorType = new JRadioButton("Doctor");
            doctorType.setBounds(120,10,80,30);
        }
        return doctorType;
    }

    public JRadioButton getNurseType(){
        if(nurseType == null){
            nurseType = new JRadioButton("Nurse");
            nurseType.setBounds(230,10,80,30);
        }
        return nurseType;
    }

    public ButtonGroup getButtonGroup(){
        if(buttonGroup == null){
            buttonGroup = new ButtonGroup();
            buttonGroup.add(getDoctorType());
            buttonGroup.add(getNurseType());
        }
        return buttonGroup;
    }

    public JLabel getClerkID(){
        if(clerkID == null){
            clerkID = new JLabel("Clerk ID");
            clerkID.setBounds(10,50,80,30);
        }
        return clerkID;
    }

    public JTextField getClerkIDField(){
        if(clerkIDField == null){
            clerkIDField = new JTextField();
            clerkIDField.setBounds(120,50,100,30);
        }
        return clerkIDField;
    }

    public JLabel getNewSalaryLabel(){
        if(newSalaryLabel == null){
            newSalaryLabel = new JLabel("New Salary");
            newSalaryLabel.setBounds(10,90,80,30);
        }
        return newSalaryLabel;
    }

    public JTextField getNewSalaryField(){
        if(newSalaryField == null){
            newSalaryField = new JTextField();
            newSalaryField.setBounds(120,90,100,30);
        }
        return newSalaryField;
    }

    public JButton getSubmit(){
        if(submit == null){
            submit = new JButton("Submit");
            submit.setBounds(190,150,80,30);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // System.out.println("Submit is clicked");
                    try {
                        int clerkID = Integer.parseInt(toAdjustSalaryClerkID = clerkIDField.getText());
                        if (doctorType.isSelected()) {
                            if (!checkDoctorIDExist(toAdjustSalaryClerkID)) {
                                JOptionPane.showMessageDialog(null, "We don't have doctor with given ID!", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                toQueryTable = "Doctor";
                            }
                        } else {
                            if (!checkNurseIDExist(toAdjustSalaryClerkID)) {
                                JOptionPane.showMessageDialog(null, "We don't have nurse with given ID!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                toQueryTable = "Nurse";
                            }
                        }
                    }
                    catch (NumberFormatException ne){
                        JOptionPane.showMessageDialog(null,"Please type number as ID!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    try{
                        newSalary = Double.parseDouble(newSalaryField.getText());
                    }
                    catch(NumberFormatException ne){
                        JOptionPane.showMessageDialog(null,"Please type number as a Salary!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    // System.out.println(newSalary);
                    try {
                        updateClerkSalary(toQueryTable, toAdjustSalaryClerkID, newSalary);
                    }
                    catch (SQLException sqle){
                        sqle.getStackTrace();
                    }

                    // doDefaultCloseAction();
                }
            });
        }
        return submit;
    }

    public JButton getCancel(){
        if(cancel == null){
            cancel = new JButton("Cancel");
            cancel.setBounds(80,150,80,30);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancel;
    }

    public double getNewSalary(){
        return newSalary;
    }

    public boolean checkDoctorIDExist(String toAdjustSalaryClerkID){
        String query1 = "SELECT d_ID FROM Doctor;";
        List doctorID = Dao.findForList(query1);
        List<Integer> existingDoctorID = new ArrayList<Integer>();
        int doctorIDSize = doctorID.size();
        for(int i=0; i<doctorIDSize; i++){
            int thisID = Integer.parseInt((String) ((List) doctorID.get(i)).get(0));
            existingDoctorID.add(thisID);
        }
        return existingDoctorID.contains(toAdjustSalaryClerkID);
    }

    public boolean checkNurseIDExist(String toAdjustSalaryClerkID){
        String query2 = "SELECT n_ID From Nurse;";
        List nurseID = Dao.findForList(query2);
        List<Integer> existingNurseID = new ArrayList<Integer>();
        int nurseIDSize = nurseID.size();
        for(int i=0; i<nurseIDSize; i++){
            int thisID = Integer.parseInt((String) ((List) nurseID.get(i)).get(0));
            existingNurseID.add(thisID);
        }
        return existingNurseID.contains(toAdjustSalaryClerkID);
    }

    public void updateClerkSalary (String toQueryTable, String toAdjustSalaryClerkID, double newSalary)
            throws SQLException{
        String query = "UPDATE " + toQueryTable + " SET ";
        if(toQueryTable != null) {
            if (toQueryTable.equals("Doctor")) {
                query = query + "d_salary=" + newSalary + " WHERE d_ID=" + toAdjustSalaryClerkID;
            } else {
                query = query + "n_salary=" + newSalary + " WHERE n_ID=" + toAdjustSalaryClerkID;
            }
            System.out.println(query);
            PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
            preparedStmt.executeUpdate();
        }
        else{
            JOptionPane.showMessageDialog(null,"Can not find table to query!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
