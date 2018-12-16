package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.DoctorInfo;
import com.cunjunwang.hospital.emp.init_version_2016.NumberSorter;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/29.
 */

public class DoctorList extends JInternalFrame{
    private JTable table;
    private int numberOfDoctor = 0;
    private JLabel numberLabel;
    private JLabel numberOfDoctorField;
    private JLabel searchLabel;
    private JTextField textFilter;

    public DoctorList() {
        super();
        setClosable(true);
        setTitle("Doctor Overview");
        setSize(750, 400);
        setLayout(null);
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10, 70, 705, 275);
        add(scrollPanel, BorderLayout.SOUTH);

        add(getTextFilter());
        initTable();
        add(getSearchLabel());
        add(getNumberLabel());
        add(getNumberOfDoctorField());

    }

    private void initTable(){
        String[] columnNames = {"ID","Name","Department","Gender","Phone_Number",
                "Year of experience","Salary","Is on job?"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = table.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List doctorInfos = DoctorInfo.getDoctorInfos();
        for(int i=0; i<doctorInfos.size(); i++){
            List thisInfo = (List) doctorInfos.get(i);
            DoctorInfo doctorInfo = DoctorInfo.getDoctorInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(doctorInfo.getD_ID() != null && doctorInfo.getD_ID() != 0){
                row[0] = doctorInfo.getD_ID();
                row[1] = doctorInfo.getD_name();
                row[2] = doctorInfo.getD_dept();
                row[3] = doctorInfo.getD_gender();
                row[4] = doctorInfo.getD_phone_number();
                row[5] = doctorInfo.getD_year_of_experience();
                row[6] = doctorInfo.getD_salary();
                row[7] = doctorInfo.getD_is_on_job();
                defaultTableModel.addRow(row);
                numberOfDoctor++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(5, numberSorter);
        tableRowSorter.setComparator(6, numberSorter);
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
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private JLabel getNumberLabel(){
        if(numberLabel == null){
            numberLabel = new JLabel("Number Of Doctors: ");
            numberLabel.setBounds(10,10,150,20);
        }
        return numberLabel;
    }

    private JLabel getNumberOfDoctorField(){
        if(numberOfDoctorField == null){
            numberOfDoctorField = new JLabel();
            numberOfDoctorField.setBounds(160,10,50,20);
            numberOfDoctorField.setText(String.valueOf(numberOfDoctor));
        }
        return numberOfDoctorField;
    }

    private JLabel getSearchLabel(){
        if(searchLabel == null){
            searchLabel = new JLabel("Search: ");
            searchLabel.setBounds(10, 40, 70, 20);
        }
        return searchLabel;
    }

    private JTextField getTextFilter(){
        if(textFilter == null){
            textFilter = new JTextField();
            textFilter.setBounds(90, 40, 200, 20);
        }
        return textFilter;
    }

}
