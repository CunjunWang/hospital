package com.cunjunwang.hospital.GUIFrames;
import com.cunjunwang.hospital.DataAccess.Dao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * * Created by nancyyoung on 2016-11-03.
 */
public class UpdateMedicineFrame extends JInternalFrame {

    private JLabel IDLabel;
    private JTextField IDField;

    private JLabel nameLabel;
    private JTextField nameField;
    private JCheckBox updateName;

    private JLabel priceLabel;
    private JTextField priceField;
    private JCheckBox updatePrice;

    private JLabel descriptionLabel;
    private JTextArea descriptionArea;
    private JCheckBox updateDescription;

    private JLabel stockLabel;
    private JTextField stockField;
    private JCheckBox updateStock;

    private JButton cancelButton;
    private JButton submitButton;

    private Dao dao = new Dao();

    private int m_ID;
    private String m_name;
    private double m_price;
    private String m_description;
    private int m_stock;

    public UpdateMedicineFrame(){
        super();
        setTitle("Update Medicine");
        setClosable(true);
        setBounds(10,10,410,400);
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
        add(getUpdateName());
        add(getPriceLabel());
        add(getPriceField());
        add(getUpdatePrice());
        add(getDescriptionLabel());
        add(getDescriptionArea());
        add(getUpdateDescription());
        add(getStockLabel());
        add(getStockField());
        add(getUpdateStock());
        add(getCancelButton());
        add(getSubmitButton());

    }


    public JLabel getIDLabel() {
        if(IDLabel == null){
            IDLabel = new JLabel("Medicine ID: ");
            IDLabel.setBounds(10,10,120,30);
        }
        return IDLabel;
    }

    public JTextField getIDField() {
        if (IDField == null) {
            IDField = new JTextField();
            IDField.setBounds(130,10,80,30);
        }
        return IDField;
    }

    public JLabel getNameLabel() {
        if(nameLabel == null){
            nameLabel = new JLabel("Name:");
            nameLabel.setBounds(10,50,120,30);
        }
        return nameLabel;
    }

    public JTextField getNameField() {
        if(nameField == null){
            nameField = new JTextField();
            nameField.setBounds(130,50,80,30);
            nameField.setEnabled(false);
        }
        return nameField;
    }

    public JCheckBox getUpdateName(){
        if(updateName == null){
            updateName = new JCheckBox("Update Name");
            updateName.setBounds(220,50,180,30);
            updateName.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    nameField.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                }
            });
        }
        return updateName;
    }

    public JLabel getPriceLabel() {
        if(priceLabel == null){
            priceLabel = new JLabel("Price: ");
            priceLabel.setBounds(10,90,120,30);
        }
        return priceLabel;
    }

    public JTextField getPriceField() {
        if (priceField == null) {
            priceField = new JTextField();
            priceField.setBounds(130,90,80,30);
            priceField.setEnabled(false);
        }
        return priceField;
    }

    public JCheckBox getUpdatePrice() {
        if(updatePrice == null){
            updatePrice= new JCheckBox("Update Price");
            updatePrice.setBounds(220,90,180,30);
            updatePrice.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    priceField.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                }
            });
        }
        return updatePrice;
    }

    public JLabel getDescriptionLabel() {
        if(descriptionLabel == null){
            descriptionLabel = new JLabel("Description:");
            descriptionLabel.setBounds(10,130,100,30);
        }
        return descriptionLabel;
    }

    public JTextArea getDescriptionArea() {
        if(descriptionArea == null){
            descriptionArea = new JTextArea();
            descriptionArea.setBounds(10,170,360,60);
            descriptionArea.setEnabled(false);
        }
        return descriptionArea;
    }

    public JCheckBox getUpdateDescription() {
        if(updateDescription == null){
            updateDescription = new JCheckBox("Update Description");
            updateDescription.setBounds(220,130,180,30);
            updateDescription.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    descriptionArea.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                }
            });
        }
        return updateDescription;
    }

    public JLabel getStockLabel() {
        if(stockLabel == null){
            stockLabel = new JLabel("Stock: ");
            stockLabel.setBounds(10,240,120,30);
        }
        return stockLabel;
    }

    public JTextField getStockField() {
        if (stockField == null) {
            stockField = new JTextField();
            stockField.setBounds(130, 240, 80, 30);
            stockField.setEnabled(false);
        }
        return stockField;
    }

    public JCheckBox getUpdateStock() {
        if(updateStock == null){
            updateStock = new JCheckBox("Update Stock");
            updateStock.setBounds(220,240,180,30);
            updateStock.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    stockField.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                }
            });
        }
        return updateStock;
    }

    public JButton getSubmitButton(){
        if(submitButton == null){
            submitButton = new JButton("Submit");
            submitButton.setBounds(250,300,80,30);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    m_ID = 0;
                    try {
                        m_ID = Integer.parseInt(IDField.getText().trim());
                        if(!checkExistingMedicineID(m_ID)){
                            JOptionPane.showMessageDialog(null,"We do not have medicine with given ID!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(m_ID<=0){
                            JOptionPane.showMessageDialog(null,"Please give a valid ID!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        if(IDField.getText().equals("")){
                            JOptionPane.showMessageDialog(null,"ID can not be empty!","Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        m_name = (String) ((List) findThisMedicineName(m_ID).get(0)).get(0);
                        if(nameField.isEnabled()) {
                            m_name = nameField.getText().trim();
                            if (m_name.equals("")) {
                                JOptionPane.showMessageDialog(null, "Name can not be empty!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        m_price = Double.parseDouble((String) ((List) findThisMedicinePrice(m_ID).get(0)).get(0));
                        if(priceField.isEnabled()) {
                            try {
                                m_price = Double.parseDouble(priceField.getText());
                                if (m_price <= 0) {
                                    JOptionPane.showMessageDialog(null, "Please give a valid number as price!", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            catch (NumberFormatException ne){
                                JOptionPane.showMessageDialog(null,"Please type number as price!","Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        m_description = (String) ((List) findThisMedicineDescription(m_ID).get(0)).get(0);
                        if(nameField.isEnabled()) {
                            m_description = descriptionArea.getText().trim();
                        }

                        m_stock = Integer.parseInt((String) ((List) findThisMedicineStock(m_ID).get(0)).get(0));
                        if(stockField.isEnabled()) {
                            try{
                                m_stock = Integer.parseInt(stockField.getText());
                                if (m_stock <= 0) {
                                    JOptionPane.showMessageDialog(null, "Please give a valid number as stock!", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            catch(NumberFormatException ne) {
                                JOptionPane.showMessageDialog(null, "Please type number as stock!", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        try {
                            updateResult(m_ID, m_name, m_price, m_description, m_stock);
                        }
                        catch (SQLException sqle){
                            sqle.getStackTrace();
                        }

                    }catch (NumberFormatException e1){
                        JOptionPane.showMessageDialog(null, "Please type number as a price or stock!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                 }
            });
        }
        return submitButton;
    }

    public JButton getCancelButton(){
        if(cancelButton == null){
            cancelButton = new JButton("Cancel");
            cancelButton.setBounds(100,300,80,30);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancelButton;
    }

    public List findThisMedicineName(int m_ID){
        List thisMedicineName = Dao.findForList("SELECT m_name FROM Medicine WHERE m_ID=" + m_ID);
        return thisMedicineName;
    }

    public List findThisMedicinePrice(int m_ID){
        List thisMedicinePrice = Dao.findForList("SELECT m_price FROM Medicine WHERE m_ID=" + m_ID);
        return thisMedicinePrice;
    }

    public List findThisMedicineDescription(int m_ID){
        List thisMedicineDescription = Dao.findForList("SELECT m_description FROM Medicine WHERE m_ID=" + m_ID);
        return thisMedicineDescription;
    }

    public List findThisMedicineStock(int m_ID){
        List thisMedicineStock = Dao.findForList("SELECT m_stock FROM Medicine WHERE m_ID=" + m_ID);
        return thisMedicineStock;
    }

    public void updateResult(int m_ID, String m_name, double m_price, String m_description, int m_stock)
            throws SQLException {
        String query = "UPDATE Medicine SET ";
        if(updateName.isSelected()){
            query = query + "m_name='" + m_name + "',";
        }
        if(updatePrice.isSelected()){
            query = query + "m_price=" + m_price + ",";
        }
        if(updateDescription.isSelected()){
            query = query + "m_description='" + m_description + "',";
        }
        if(updateStock.isSelected()){
            query = query + "m_stock=" + m_stock + ",";
        }

        String where = " WHERE m_ID=" + m_ID + ";";
        query = query + where;

        query = rearrangeQuery(query);

        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public String rearrangeQuery(String query){
        String newQuery = "";
        int splitPosition = query.indexOf(" WHERE");
        String part1 = query.substring(0,splitPosition-1);
        String part2 = query.substring(splitPosition);
        newQuery = part1 + part2;
        System.out.println(newQuery);
        return newQuery;
    }

    public boolean checkExistingMedicineID(int inputID){
        List existedIDList = dao.findForList("SELECT m_ID FROM Medicine");
        int existedDataSize = existedIDList.size();
        ArrayList<Integer> collectIDList = new ArrayList<>();
        for(int i=0; i<existedDataSize; i++){
            List thisData = (List) existedIDList.get(i);
            int thisID = Integer.parseInt((String) thisData.get(0));
            collectIDList.add(thisID);
        }

        return collectIDList.contains(inputID);
    }
}
