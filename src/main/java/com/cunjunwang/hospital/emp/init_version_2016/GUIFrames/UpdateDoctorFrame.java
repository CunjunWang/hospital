package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

/**
 * Created by CunjunWang on 16/11/13.
 */

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nancyyoung on 2016-11-03.
 */
public class UpdateDoctorFrame extends JInternalFrame {
    private JLabel IDLabel;
    private JTextField IDField;

    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;


    private JButton cancel;
    private JButton submit;

    private String ID;
    private String phone;

    private Dao dao = new Dao();

    public UpdateDoctorFrame(){
        super();
        setTitle("Update Doctor");
        setClosable(true);
        setBounds(10,10,450,200);
        setLayout(null);
        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(getIDLabel());
        add(getIDField());

        add(getPhoneNumberLabel());
        add(getPhoneNumberField());

        add(getCancel());
        add(getSubmit());
    }


    public JLabel getIDLabel() {
        if(IDLabel == null){
            IDLabel = new JLabel("Doctor ID: ");
            IDLabel.setBounds(10,10,150,30);
        }
        return IDLabel;
    }

    public JTextField getIDField() {
        if (IDField == null) {
            IDField = new JTextField();
            IDField.setBounds(200,10,200,30);
        }
        return IDField;
    }

    public JLabel getPhoneNumberLabel() {
        if( phoneNumberLabel == null){
            phoneNumberLabel = new JLabel("Phone: ");
            phoneNumberLabel.setBounds(10,50,150,30);
        }
        return phoneNumberLabel;
    }

    public JTextField getPhoneNumberField() {
        if (phoneNumberField == null) {
            phoneNumberField = new JTextField();
            phoneNumberField.setBounds(200,50,200,30);
        }
        return phoneNumberField;
    }


    public JButton getSubmit(){
        if(submit == null){
            submit = new JButton("Submit");
            submit.setBounds(250,100,80,30);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Submit is clicked");

                    try {
                        ID = IDField.getText();
                        phone = phoneNumberField.getText();
//                        //admittedDate = Integer.parseInt(pDateAdmitted.getText());
//                        dischargedDate = Integer.parseInt(pDateDischargedField.getText());
//                        balance = Double.parseDouble(pBalanceField.getText());
                    }catch (NumberFormatException e1){
                        JOptionPane.showMessageDialog(null,"Please type number as a phone number!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    try {
                        updateResult(ID, phone);
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
            cancel.setBounds(100,100,80,30);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancel;
    }

    public void updateResult(String ID, String phone) throws SQLException {
        String query = "UPDATE Doctor SET d_phone_number= " + phone + " WHERE d_ID= " + ID;
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

}
