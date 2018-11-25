package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by nancyyoung on 2016-11-02.
 */
public class AddMedicineFrame extends JInternalFrame {

    private JLabel IDLabel;
    private JTextField IDField;

    private JLabel nameLabel;
    private JTextField nameField;

    private JLabel priceLabel;
    private JTextField priceField;

    private JLabel descriptionLabel;
    private JTextArea descriptionArea;

    private JLabel stockLabel;
    private JTextField stockField;

    private JLabel asteriskLabel1;
    private JLabel asteriskLabel2;
    private JLabel asteriskInfoLabel;

    private JButton submit;
    private JButton cancel;
    private Dao dao = new Dao();

    public AddMedicineFrame(){
        super();
        setClosable(true);
        setBounds(10,10,360,480);
        setTitle("Add Medicine");
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
        add(getPriceLabel());
        add(getPriceField());
        add(getDescriptionLabel());
        add(getDescriptionArea());
        add(getStockLabel());
        add(getStockField());
        add(getAsteriskLabel1());
        add(getAsteriskLabel2());
        add(getAsteriskInfoLabel());
        add(getSubmit());
        add(getCancel());
    }

    public JLabel getIDLabel(){
        if(IDLabel == null){
            IDLabel = new JLabel("Medicine ID:");
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
            nameLabel = new JLabel("Name:");
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

    public JLabel getPriceLabel(){
        if(priceLabel == null){
            priceLabel = new JLabel("Price:");
            priceLabel.setBounds(10,90,80,30);
        }
        return priceLabel;
    }

    public JTextField getPriceField(){
        if(priceField == null){
            priceField = new JTextField();
            priceField.setBounds(120,90,80,30);
        }
        return priceField;
    }

    public JLabel getDescriptionLabel() {
        if(descriptionLabel == null){
            descriptionLabel = new JLabel("Description: ");
            descriptionLabel.setBounds(10,130,120,30);
        }
        return descriptionLabel;
    }

    public JTextArea getDescriptionArea() {
        if (descriptionArea == null) {
            descriptionArea = new JTextArea();
            descriptionArea.setBounds(10, 170, 320, 80);
            descriptionArea.setWrapStyleWord(true);
            descriptionArea.setLineWrap(true);
        }
        return descriptionArea;
    }

    public JLabel getStockLabel() {
        if(stockLabel == null){
            stockLabel = new JLabel("Stock: ");
            stockLabel.setBounds(10,260,80,30);
        }
        return stockLabel;
    }

    public JTextField getStockField() {
        if (stockField == null) {
            stockField = new JTextField();
            stockField.setBounds(120, 260, 80, 30);
        }
        return stockField;
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
                    int m_ID = 0;
                    try{
                        m_ID = Integer.parseInt(IDField.getText().trim());

                        if(!checkUniqueMedicineID(m_ID)){
                            JOptionPane.showMessageDialog(null,"This ID already existed!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(m_ID <= 0){
                            JOptionPane.showMessageDialog(null,"Please give a valid number as ID!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(IDField.getText().equals("")){
                            JOptionPane.showMessageDialog(null,"ID can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        String m_name = nameField.getText().trim();
                        if(m_name.equals("")){
                            JOptionPane.showMessageDialog(null,"Name can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        double m_price = 0;
                        try {
                            m_price = Double.parseDouble(priceField.getText().trim());
                            if(m_price <= 0){
                                JOptionPane.showMessageDialog(null,"Please give a valid number as price!","Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                            String m_description = descriptionArea.getText().trim();

                            int m_stock = 0;
                            try{
                                m_stock = Integer.parseInt(stockField.getText().trim());
                                if(m_stock <= 0){
                                    JOptionPane.showMessageDialog(null,"Please give a valid number as stock!","Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                                try {
                                    updateResult(m_ID, m_name, m_price, m_description, m_stock);
                                }
                                catch (SQLException sqle){
                                    sqle.getStackTrace();
                                }
                            }
                            catch(NumberFormatException ne) {
                                JOptionPane.showMessageDialog(null, "Please type number as a stock!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        catch(NumberFormatException ne) {
                            JOptionPane.showMessageDialog(null, "Please type number as price!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(NumberFormatException ne) {
                        JOptionPane.showMessageDialog(null, "Please type number as ID!", "Error",
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

    public void updateResult(int ID, String name, double price, String desc, int stock) throws SQLException {
        String query = "INSERT INTO Medicine VALUES (" + ID + ",'" + name + "'," + price + ",'" + desc + "'," + stock + ")";
        // System.out.println(query);
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public boolean checkUniqueMedicineID(int inputID){
        List existedIDNamelist = dao.findForList("SELECT m_ID,m_name FROM Medicine");
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

