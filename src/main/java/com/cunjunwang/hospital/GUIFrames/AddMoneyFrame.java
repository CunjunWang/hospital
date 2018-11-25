package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Dao;
import com.cunjunwang.hospital.DataAccess.Models.PatientInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/4.
 */
public class AddMoneyFrame extends JInternalFrame {

    private String userName;
    private String userType;
    private String userID;
    private JButton submitButton;
    private JButton cancelButton;
    private JLabel currentBalanceLabel;
    private JLabel currentBalanceValueLabel;
    private JLabel amountToAddLabel;
    private JTextField amountToAddField;

    private int p_ID;
    private double toPay;
    private double balance;
    private Dao dao = new Dao();


    public AddMoneyFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,360,200);
        setTitle("Add Money");
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

        add(getCurrentBalanceLabel());
        add(getCurrentBalanceValueLabel());
        add(getAmountToAddLabel());
        add(getAmountToAddField());
        add(getSubmitButton());
        add(getCancelButton());
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
                balance = patientInfo.getP_account_balance();
                break;
            }
        }
    }

    public JLabel getCurrentBalanceLabel() {
        if(currentBalanceLabel == null){
            currentBalanceLabel = new JLabel("Current Balance: ");
            currentBalanceLabel.setBounds(10,10,120,30);
        }
        return currentBalanceLabel;
    }

    public JLabel getCurrentBalanceValueLabel() {
        if(currentBalanceValueLabel == null){
            currentBalanceValueLabel = new JLabel(String.valueOf(balance));
            currentBalanceValueLabel.setBounds(140,10,50,30);
        }
        return currentBalanceValueLabel;
    }

    public JLabel getAmountToAddLabel() {
        if(amountToAddLabel == null){
            amountToAddLabel = new JLabel("Amount to add: ");
            amountToAddLabel.setBounds(10,50,120,30);
        }
        return amountToAddLabel;
    }

    public JTextField getAmountToAddField() {
        if(amountToAddField == null){
            amountToAddField = new JTextField();
            amountToAddField.setBounds(140,50,100,30);
        }
        return amountToAddField;
    }

    public JButton getSubmitButton() {
        if(submitButton == null){
            submitButton = new JButton("Submit");
            submitButton.setBounds(250,10,80,30);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int toAdd;
                    try{
                        toAdd = Integer.parseInt(amountToAddField.getText().trim());
                        if(toAdd < 0){
                            JOptionPane.showMessageDialog(null,"Please type a valid number as amount!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        balance += toAdd;
                        updateBalance(balance);
                        doDefaultCloseAction();
                    }
                    catch(NumberFormatException ne){
                        JOptionPane.showMessageDialog(null,"Please type number as amount!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    catch(SQLException sqle){
                        sqle.getStackTrace();
                    }
                }
            });
        }
        return submitButton;
    }

    public JButton getCancelButton() {
        if(cancelButton == null){
            cancelButton = new JButton("Cancel");
            cancelButton.setBounds(250,50,80,30);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancelButton;
    }

    public void updateBalance(double balance) throws SQLException{
        String query = "UPDATE Patient SET balance=" + balance + " WHERE p_ID=" + p_ID + ";";
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }
}
