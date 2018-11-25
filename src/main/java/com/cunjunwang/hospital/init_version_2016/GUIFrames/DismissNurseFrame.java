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
public class DismissNurseFrame extends JInternalFrame {
    private JLabel IDLabel;
    private JTextField IDField;

    private JButton dismiss;
    private JButton cancel;
    private Dao dao = new Dao();

    public DismissNurseFrame() {
        super();
        setClosable(true);
        setBounds(10, 10, 360, 170);
        setTitle("Dismiss Nurse");
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
            IDLabel = new JLabel("Nurse ID");
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
                    int n_ID = 0;
                    try{
                        n_ID = Integer.parseInt(IDField.getText().trim());
                        String string_n_ID = String.valueOf(n_ID);
                        if(!checkNurseIDExist(string_n_ID)){
                            JOptionPane.showMessageDialog(null,"We don't have nurse with given ID!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        try {
                            deleteThisNurse(n_ID);
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

    public boolean checkNurseIDExist(String n_ID){
        String query = "SELECT n_ID FROM Nurse;";
        List nurseID = Dao.findForList(query);
        List<Integer> existingNurseID = new ArrayList<Integer>();
        int nurseIDSize = nurseID.size();
        for(int i=0; i<nurseIDSize; i++){
            int thisID = Integer.parseInt((String) ((List) nurseID.get(i)).get(0));
            existingNurseID.add(thisID);
        }
        return existingNurseID.contains(n_ID);
    }

    public void deleteThisNurse(int n_ID) throws SQLException{
        String query = "UPDATE Nurse SET n_is_on_job = 'false' " + "WHERE n_ID=" + n_ID + ");";
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }
}
