package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

/**
 * Created by CunjunWang on 16/11/3.
 */

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;

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
public class DeletePatientFrame extends JInternalFrame {
    private JLabel IDLabel;
    private JTextField IDField;

    private JButton deleteButton;
    private JButton cancelButton;
    private Dao dao = new Dao();

    public DeletePatientFrame() {
        super();
        setClosable(true);
        setBounds(10, 10, 360, 170);
        setTitle("Delete Patient");
        setLayout(null);
        setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(getIDLabel());
        add(getIDField());

        add(getDeleteButton());
        add(getCancelButton());


    }

    public JLabel getIDLabel(){
        if(IDLabel == null){
            IDLabel = new JLabel("Patient ID");
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

    public JButton getDeleteButton(){
        if(deleteButton == null){
            deleteButton = new JButton("Delete");
            deleteButton.setBounds(190,80,80,30);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int p_ID = 0;
                    try{
                        p_ID = Integer.parseInt(IDField.getText().trim());
                        // String string_p_ID = String.valueOf(p_ID);
                        if(!checkPatientIDExist(p_ID)){
                            JOptionPane.showMessageDialog(null,"We don't have patient with given ID!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            deleteThisPatient(p_ID);
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
        return deleteButton;
    }

    public JButton getCancelButton(){
        if(cancelButton == null){
            cancelButton = new JButton("Cancel");
            cancelButton.setBounds(80,80,80,30);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancelButton;
    }

    public boolean checkPatientIDExist(int p_ID){
        String query = "SELECT p_ID FROM Patient;";
        List patientID = Dao.findForList(query);
        List<Integer> existingPatientID = new ArrayList<Integer>();
        int patientIDSize = patientID.size();
        for(int i=0; i<patientIDSize; i++){
            int thisID = Integer.parseInt((String) ((List) patientID.get(i)).get(0));
            existingPatientID.add(thisID);
        }
        return existingPatientID.contains(p_ID);
    }

    public void deleteThisPatient(int p_ID) throws SQLException{
        String query1 = "UPDATE Patient SET p_is_cured = false " + "WHERE p_ID=" + p_ID + ";";
        String query2 = "DELETE FROM Diagnosis WHERE diag_p_ID=" + p_ID + ";";
        String query3 = "DELETE FROM Needed_knows WHERE need_p_ID=" + p_ID + ";";
        PreparedStatement preparedStmt1 = dao.getConnection().prepareStatement(query1);
        PreparedStatement preparedStmt2 = dao.getConnection().prepareStatement(query2);
        PreparedStatement preparedStmt3 = dao.getConnection().prepareStatement(query3);
        preparedStmt1.executeUpdate();
        preparedStmt2.executeUpdate();
        preparedStmt3.executeUpdate();
    }
}
