package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by nancyyoung on 2016-11-03.
 */
public class UpdateNurseFrame extends JInternalFrame {
    private JLabel IDLabel;
    private JTextField IDField;

    private JLabel nPhone;
    private JTextField nPhoneField;


    private JButton cancel;
    private JButton submit;

    private String ID;
    private String phone;

    private Dao dao = new Dao();

    public UpdateNurseFrame(){
        super();
        setTitle("Update Nurse");
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

        add(getnPhone());
        add(getnPhoneField());

        add(getCancel());
        add(getSubmit());
    }


    public JLabel getIDLabel() {
        if(IDLabel == null){
            IDLabel = new JLabel("Nurse ID: ");
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

    public JLabel getnPhone() {
        if( nPhone== null){
            nPhone = new JLabel("Phone: ");
            nPhone.setBounds(10,50,150,30);
        }
        return nPhone;
    }

    public JTextField getnPhoneField() {
        if (nPhoneField == null) {
            nPhoneField = new JTextField();
            nPhoneField.setBounds(200,50,200,30);
        }
        return nPhoneField;
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
                        phone = nPhoneField.getText();
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
        String query = "UPDATE Nurse SET n_phone_number= " + phone + " WHERE n_ID= " + ID;
        System.out.println(query);
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

}
