package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.NurseInfo;
import com.cunjunwang.hospital.init_version_2016.NumberSorter;

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
public class NurseList extends JInternalFrame {
    private final JTable table;
    private int numberOfNurse = 0;
    private JTextField textFilter;

    public NurseList(){
        super();
        setClosable(true);
        setTitle("Nurse Overview");
        setBounds(100, 100, 750, 400);
        setLayout(null);
        textFilter = new JTextField();
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        initTable();

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10,70,705,275);
        add(scrollPanel);

        JLabel numberLabel = new JLabel("Number Of Nurses: ");
        JLabel numberOfNurseField = new JLabel();
        numberLabel.setBounds(10,10,150,20);
        numberOfNurseField.setBounds(160,10,50,20);
        numberOfNurseField.setText(String.valueOf(numberOfNurse));
        add(numberLabel);
        add(numberOfNurseField);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(10,40,70,20);
        textFilter.setBounds(90,40,200,20);
        add(searchLabel);
        add(textFilter);
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

        List nurseInfos = NurseInfo.getNurseInfos();
        for(int i=0; i<nurseInfos.size(); i++){
            List thisInfo = (List) nurseInfos.get(i);
            NurseInfo nurseInfo = NurseInfo.getNurseInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(nurseInfo.getN_ID() != null && nurseInfo.getN_ID() != 0){
                row[0] = nurseInfo.getN_ID();
                row[1] = nurseInfo.getN_name();
                row[2] = nurseInfo.getN_dept();
                row[3] = nurseInfo.getN_gender();
                row[4] = nurseInfo.getN_phone_number();
                row[5] = nurseInfo.getN_year_of_experience();
                row[6] = nurseInfo.getN_salary();
                row[7] = nurseInfo.getN_is_on_job();
                defaultTableModel.addRow(row);
                numberOfNurse++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
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
                //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
