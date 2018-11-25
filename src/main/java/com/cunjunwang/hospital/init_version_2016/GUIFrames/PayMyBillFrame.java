package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.PatientInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/3.
 */
public class PayMyBillFrame extends JInternalFrame {

    private String userName;
    private String userType;
    private String userID;
    private JButton payButton;
    private JButton cancelButton;
    private JLabel balanceLabel;
    private JLabel balanceValueLabel;
    private JLabel WarningLabel;

    private int p_ID;
    private double toPay;
    private double balance;
    private Dao dao = new Dao();

    public PayMyBillFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,360,200);
        setTitle("Pay My Bill");
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

        toPay = Double.parseDouble((String) ((ArrayList) getMyAmount().get(0)).get(0));
        JLabel toPayLabel = new JLabel("Total to pay: ");
        JLabel toPayField = new JLabel();
        toPayLabel.setBounds(10,10,100,30);
        toPayField.setBounds(130,10,50,30);
        toPayField.setText(String.valueOf(toPay));
        add(toPayLabel);
        add(toPayField);
        add(getBalanceLabel());
        add(getBalanceValueLabel());
        add(getPayButton());
        add(getCancelButton());

        if(!checkEnoughBalance()){
            add(getWarningLabel());
        }
    }

    public JLabel getBalanceLabel() {
        if(balanceLabel == null){
            balanceLabel = new JLabel("Balance: ");
            balanceLabel.setBounds(10,50,100,30);
        }
        return balanceLabel;
    }

    public JLabel getBalanceValueLabel() {
        if(balanceValueLabel == null){
            balanceValueLabel = new JLabel(String.valueOf(balance));
            balanceValueLabel.setBounds(130,50,50,30);
        }
        return balanceValueLabel;
    }

    public JLabel getWarningLabel() {
        if(WarningLabel == null){
            WarningLabel = new JLabel();
            WarningLabel.setText("Warning: inadequate balance");
            WarningLabel.setForeground(Color.RED);
            WarningLabel.setBounds(60,100,200,30);
        }
        return WarningLabel;
    }

    public JButton getPayButton() {
        if(payButton == null){
            payButton = new JButton("Pay");
            payButton.setBounds(250,10,80,30);
            payButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(checkEnoughBalance()){
                        balance = balance - toPay;
                        toPay = 0;
                        try {
                            updateNewBalanceAmount(p_ID,balance,toPay);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        doDefaultCloseAction();
                    }
                    else{
                        JOptionPane.showMessageDialog(payButton,"Inadequate Balance!",
                                "Pay failed",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        return payButton;
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

    public boolean checkEnoughBalance(){
        return balance >= toPay;
    }

    public List getMyMedicine(){
        String sql = "SELECT need_m_ID, m_name, m_price FROM Needed_Knows,Medicine WHERE " +
                "need_p_ID=" + p_ID + " AND need_m_ID=Medicine.m_ID;";
        List list = Dao.findForList(sql);
        return list;
    }

    public List getMyAmount(){
        String sql = "SELECT b_amount FROM Pay_bill WHERE b_payer_ID=" + p_ID + ";";
        List list = Dao.findForList(sql);
        return list;
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

    public void updateNewBalanceAmount(int p_ID, double balance, double toPay)
            throws SQLException{
        String query1 = "UPDATE Pay_bill SET b_amount=" + toPay + " WHERE b_payer_ID=" + p_ID + ";";
        String query2 = "UPDATE Patient SET p_account_balance=" + balance + " WHERE p_ID=" + p_ID + ";";
        PreparedStatement preparedStmt1 = dao.getConnection().prepareStatement(query1);
        preparedStmt1.executeUpdate();
        PreparedStatement preparedStmt2 = dao.getConnection().prepareStatement(query2);
        preparedStmt2.executeUpdate();
    }
}
