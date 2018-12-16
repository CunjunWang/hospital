package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.NurseInfo;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.PatientInfo;
import com.cunjunwang.hospital.emp.init_version_2016.NumberSorter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/13.
 */
public class SomeOtherInterestingFactFrame extends JInternalFrame{

    private JTable resultTable;
    private JButton allMedicines;
    private JButton allRooms;

    private JLabel resultLabel;
    private JLabel searchLabel;
    private JTextField textFilter;

    public SomeOtherInterestingFactFrame(){
        super();
        setClosable(true);
        setBounds(10,10,500,350);
        setTitle("Some other interesting facts :)");
        setLayout(null);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultTable = new JTable();

        JScrollPane resultScrollPanel = new JScrollPane(resultTable);
        resultScrollPanel.setBounds(10,130,450,150);
        add(resultScrollPanel);

        add(getAllMedicines());
        add(getAllRooms());
        add(getResultLabel());
        add(getSearchLabel());
        add(getTextFilter());

    }

    public JButton getAllMedicines() {
        if(allMedicines == null){
            allMedicines = new JButton("Which patients need all medicines?");
            allMedicines.setBounds(110,10,250,30);
            allMedicines.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initResultTableByMedicines();
                }
            });
        }
        return allMedicines;
    }

    public JButton getAllRooms() {
        if(allRooms == null){
            allRooms = new JButton("Which nurses govern all rooms?");
            allRooms.setBounds(110,50,250,30);
            allRooms.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initResultTableByRooms();
                }
            });
        }
        return allRooms;
    }

    public JLabel getResultLabel() {
        if(resultLabel == null){
            resultLabel = new JLabel("Results: ");
            resultLabel.setBounds(10,90,80,30);
        }
        return resultLabel;
    }

    public JLabel getSearchLabel(){
        if(searchLabel == null){
            searchLabel = new JLabel("Search in result:");
            searchLabel.setBounds(100,90,120,30);
        }
        return searchLabel;
    }

    public JTextField getTextFilter(){
        if(textFilter == null){
            textFilter = new JTextField();
            textFilter.setBounds(230,90,80,30);
        }
        return textFilter;
    }

    public void initResultTableByMedicines(){
        String[] columnNames = {"ID","Name","City","Gender","Age",
                "Account Balance", "Is Cured?"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) resultTable.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        defaultTableModel.setRowCount(0);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = resultTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List patientInfos = getPatientInfos();
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
                // numberofPatient++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(4, numberSorter);
        tableRowSorter.setComparator(5, numberSorter);
        resultTable.setRowSorter(tableRowSorter);

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

    public void initResultTableByRooms(){
        String[] columnNames = {"ID","Name","Department","Gender","Phone_Number",
                "Year of experience","Salary","Is on job?"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) resultTable.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        defaultTableModel.setRowCount(0);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = resultTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List nurseInfos = getNurseInfos();
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
                // numberOfNurse++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(5, numberSorter);
        tableRowSorter.setComparator(6, numberSorter);
        resultTable.setRowSorter(tableRowSorter);

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

    public List getPatientInfos(){
        String query = "SELECT P.p_ID, P.p_name FROM Patient P WHERE NOT EXISTS( (SELECT M.m_ID FROM Medicine M WHERE NOT EXISTS " +
                "(SELECT N.need_m_ID FROM Needed_Knows N WHERE N.need_p_ID = P.p_ID AND n.need_m_ID = M.m_ID)));";
        List toreturn = Dao.findForList(query);
        return toreturn;
    }

    public List getNurseInfos(){
        String query = "SELECT N.n_ID, N.n_name FROM Nurse N WHERE NOT EXISTS (SELECT g1.govern_room_number FROM Govern g1 " +
                "WHERE NOT EXISTS (SELECT g2.govern_room_number FROM Govern g2 WHERE g2.govern_n_ID = N.n_ID " +
                "AND g1.govern_room_number = g2.govern_room_number));";
        List toreturn = Dao.findForList(query);
        return toreturn;
    }
}
