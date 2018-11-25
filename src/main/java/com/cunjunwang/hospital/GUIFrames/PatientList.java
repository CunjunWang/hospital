package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Models.PatientInfo;
import com.cunjunwang.hospital.NumberSorter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/30.
 */
public class PatientList extends JInternalFrame {
    private final JTable table;
    private int numberofPatient = 0;
    private JTextField textFilter;

    public PatientList(){
        super();
        setClosable(true);
        setTitle("Patient Overview");
        setBounds(100, 100, 750, 400);
        setLayout(null);
        textFilter = new JTextField();
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        initTable();

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10,70,705,275);
        add(scrollPanel);

        JLabel numberLabel = new JLabel("Number Of Patients: ");
        JLabel numberOfPatientField = new JLabel();
        numberLabel.setBounds(10,10,150,20);
        numberOfPatientField.setBounds(160,10,50,20);
        numberOfPatientField.setText(String.valueOf(numberofPatient));
        add(numberLabel);
        add(numberOfPatientField);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(10,40,70,20);
        textFilter.setBounds(90,40,200,20);
        add(searchLabel);
        add(textFilter);
    }


    private void initTable(){
        String[] columnNames = {"ID","Name","City","Gender","Age",
                "Account Balance", "Is Cured?"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = table.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List patientInfos = PatientInfo.getPatientInfos();
        for(int i=0; i<patientInfos.size(); i++){
            List thisInfo = (List) patientInfos.get(i);
            PatientInfo patientInfo = PatientInfo.getPatientInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(patientInfo.getP_ID() != null && patientInfo.getP_ID() != 0){
                row[0] = patientInfo.getP_ID();
                row[1] = patientInfo.getP_name();
                row[2] = patientInfo.getP_city();
                row[3] = patientInfo.getP_gender();
                row[4] = patientInfo.getP_age();
                row[5] = patientInfo.getP_account_balance();
                row[6] = patientInfo.getP_is_cured();
                defaultTableModel.addRow(row);
                numberofPatient++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(4, numberSorter);
        tableRowSorter.setComparator(5, numberSorter);
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
}
