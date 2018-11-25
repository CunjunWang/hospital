package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nancyyoung on 2016-11-03.
 */
public class UpdatePatientFrame extends JInternalFrame {
    private JLabel pID;
    private JTextField pIDField;

    private JLabel pDateAdmitted;
    private JTextField pDateAdmittedField;

    private JLabel pDateDischarged;
    private JTextField pDateDischargedField;

    private JLabel pBalance;
    private JTextField pBalanceField;

    private JButton cancel;
    private JButton submit;

    private String ID;
    private int admittedDate;
    private int dischargedDate;
    private double balance;

    private Dao dao = new Dao();

    public UpdatePatientFrame(){
        super();
        setTitle("Update Patient");
        setClosable(true);
        setBounds(10,10,450,220);
        setLayout(null);
        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(getpID());
        add(getpIDField());

        add(getpDateDischarged());
        add(getpDateDischargedField());

        add(getpBalance());
        add(getpBalanceField());

        add(getCancel());
        add(getSubmit());
    }


    public JLabel getpID() {
        if(pID == null){
            pID = new JLabel("Patient ID: ");
            pID.setBounds(10,10,150,30);
        }
        return pID;
    }

    public JTextField getpIDField() {
        if (pIDField== null) {
            pIDField = new JTextField();
            pIDField.setBounds(200,10,200,30);
        }
        return pIDField;
    }

    public JLabel getpDateDischarged() {
        if (pDateDischarged == null) {
            pDateDischarged= new JLabel("Date Discharged : ");
            pDateDischarged.setBounds(10, 50, 150, 30);
        }
        return pDateDischarged;
    }

    public JTextField getpDateDischargedField() {
        if (pDateDischargedField == null) {
            pDateDischargedField = new JTextField();
            pDateDischargedField.setBounds(200, 50, 200, 30);
        }
        return pDateDischargedField;
    }

    public JLabel getpBalance() {
        if (pBalance == null) {
            pBalance= new JLabel("Balance: ");
            pBalance.setBounds(10, 90, 150, 30);
        }
        return pBalance;
    }

    public JTextField getpBalanceField() {
        if (pBalanceField == null) {
            pBalanceField = new JTextField();
            pBalanceField.setBounds(200, 90, 200, 30);
        }
        return pBalanceField;
    }

    public JButton getSubmit(){
        if(submit == null){
            submit = new JButton("Submit");
            submit.setBounds(250,140,80,30);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Submit is clicked");
                    ID = pIDField.getText();

                    try {
                        dischargedDate = Integer.parseInt(pDateDischargedField.getText());
                        balance = Double.parseDouble(pBalanceField.getText());
                    }catch (NumberFormatException e1){
                        JOptionPane.showMessageDialog(null,"Please type number as a date or balance!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    try {
                        updateResult(ID, dischargedDate, balance);
                    }
                    catch (SQLException sqle){
                        sqle.getStackTrace();
                    }catch (NullPointerException ne) {
                        ne.printStackTrace();
                    }

                    doDefaultCloseAction();

                }
            });
        }
        return submit;
    }

    public JButton getCancel(){
        if(cancel == null){
            cancel = new JButton("Cancel");
            cancel.setBounds(100,140,80,30);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancel;
    }

    public void updateResult(String ID, int DischargedDate, double balance) throws SQLException {
        String query = "UPDATE Patient SET p_date_discharged= " + DischargedDate
                + ", p_balance= " + balance + " WHERE p_ID= " + ID;
        System.out.println(query);
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }
}
