package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/3.
 */
public class DismissDoctorFrame extends JInternalFrame {
    private JLabel IDLabel;
    private JTextField IDField;

    private JButton dismiss;
    private JButton cancel;
    private Dao dao = new Dao();

    public DismissDoctorFrame() {
        super();
        setClosable(true);
        setBounds(10, 10, 360, 170);
        setTitle("Dismiss Doctor");
        setLayout(null);
        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(getIDLabel());
        add(getIDField());

        add(getDismiss());
        add(getCancel());


    }

    public JLabel getIDLabel(){
        if(IDLabel == null){
            IDLabel = new JLabel("DoctorID");
            IDLabel.setBounds(50,10,80,30);
        }
        return IDLabel;
    }

    public JTextField getIDField() {
        if(IDField == null){
            IDField = new JTextField();
            IDField.setBounds(160,10,80,30);
        }
        return IDField;
    }

    public JButton getDismiss(){
        if(dismiss == null){
            dismiss = new JButton("Dismiss");
            dismiss.setBounds(190,80,80,30);
            dismiss.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int d_ID = 0;
                    try{
                        d_ID = Integer.parseInt(IDField.getText().trim());
                        String string_d_ID = String.valueOf(d_ID);
                        if(!checkDoctorIDExist(string_d_ID)){
                            JOptionPane.showMessageDialog(null,"We don't have doctor with given ID!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            deleteThisDoctor(d_ID);
                        }
                        catch (SQLException sqle){
                            sqle.getStackTrace();
                        }
                    }
                    catch(NumberFormatException ne){
                        JOptionPane.showMessageDialog(null,"Please type number as ID!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }
        return dismiss;
    }

    public JButton getCancel(){
        if(cancel == null){
            cancel = new JButton("Cancel");
            cancel.setBounds(80,80,80,30);
            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancel;
    }

    public boolean checkDoctorIDExist(String d_ID){
        String query = "SELECT d_ID FROM Doctor;";
        List doctorID = Dao.findForList(query);
        List<Integer> existingDoctorID = new ArrayList<Integer>();
        int doctorIDSize = doctorID.size();
        for(int i=0; i<doctorIDSize; i++){
            int thisID = Integer.parseInt((String) ((List) doctorID.get(i)).get(0));
            existingDoctorID.add(thisID);
        }
        return existingDoctorID.contains(d_ID);
    }

    public void deleteThisDoctor(int d_ID) throws SQLException{
        String query = "UPDATE Doctor SET d_is_on_job = 'false' " + "WHERE d_ID=" + d_ID + ");";
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }
}
