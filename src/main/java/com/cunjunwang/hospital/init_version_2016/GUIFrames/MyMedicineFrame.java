package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.MedicineInfo;
import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.PatientInfo;
import com.cunjunwang.hospital.init_version_2016.NumberSorter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/3.
 */
public class MyMedicineFrame extends JInternalFrame {

    private String userName;
    private String userType;
    private String userID;

    private int p_ID;

    private JTable table;
    private JTextField textFilter;
    private double totalPrice = 0;
    private double toPay = 0;

    public MyMedicineFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,480,360);
        setTitle("My Medicine");
        setLayout(null);
        setVisible(true);

        this.userName = userName;
        this.userType = userType;
        this.userID = userID;

        getPatientData();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        textFilter = new JTextField();
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        initTable();

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10,70,430,275);
        add(scrollPanel);

        JLabel priceLabel = new JLabel("Total price: ");
        JLabel priceField = new JLabel(String.valueOf(totalPrice));

        JLabel toPayLabel = new JLabel("Amount to pay: ");
        toPay = Double.parseDouble((String) ((ArrayList) getMyAmount().get(0)).get(0));
        JLabel toPayField = new JLabel(String.valueOf(toPay));

        priceLabel.setBounds(10,10,100,20);
        priceField.setBounds(120,10,50,20);
        toPayLabel.setBounds(180,10,140,20);
        toPayField.setBounds(340,10,50,20);
        add(priceLabel);
        add(priceField);
        add(toPayLabel);
        add(toPayField);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(10,40,70,20);
        textFilter.setBounds(90,40,200,20);
        add(searchLabel);
        add(textFilter);
    }

    private void initTable(){
        String[] columnNames = {"ID","Price","Name","Description"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = table.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List myMedicineList = getMyMedicine();
        for(int i=0; i<myMedicineList.size(); i++){
            List thisInfo = (List) myMedicineList.get(i);
            MedicineInfo medicineInfo = MedicineInfo.getMedicineInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(medicineInfo.getM_ID() != null && medicineInfo.getM_ID() != 0){
                row[0] = medicineInfo.getM_ID();
                row[1] = medicineInfo.getM_price();
                row[2] = medicineInfo.getM_name();
                row[3] = medicineInfo.getM_description();
                defaultTableModel.addRow(row);
            }
            totalPrice += medicineInfo.getM_price();
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(1,numberSorter);
        table.setRowSorter(tableRowSorter);

        // Row Filter to search the table
        // http://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
        textFilter.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textFilter.getText();
                if (text.trim().length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textFilter.getText();
                if (text.trim().length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
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
                break;
            }
        }
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
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double newPrice){
        this.totalPrice = newPrice;
    }

}
