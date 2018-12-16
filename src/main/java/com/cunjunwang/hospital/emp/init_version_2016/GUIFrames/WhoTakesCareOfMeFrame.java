package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.DoctorInfo;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/4.
 */
public class WhoTakesCareOfMeFrame extends JInternalFrame {
    private String userName;
    private String userType;
    private String userID;
    private JButton OKButton;

    private JLabel doctorLabel;
    private JLabel nurseLabel;

    private JTable doctorTable;
    private JTable nurseTable;

    private JTextField textFilter;

    private int p_ID;
    private Dao dao = new Dao();


    public WhoTakesCareOfMeFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,500,550);
        setTitle("Who Takes Care Of Me");
        setLayout(null);
        setVisible(true);

        this.userName = userName;
        this.userType = userType;
        this.userID = userID;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        getPatientData();

        textFilter = new JTextField();
        doctorTable = new JTable();
        doctorTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        nurseTable = new JTable();
        nurseTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        initDoctorTable();
        initNurseTable();

        JScrollPane doctorScrollPanel = new JScrollPane(doctorTable);
        doctorScrollPanel.setBounds(10,40,450,180);
        add(doctorScrollPanel);

        JScrollPane nurseScrollPanel = new JScrollPane(nurseTable);
        nurseScrollPanel.setBounds(10,260,450,180);
        add(nurseScrollPanel);

        add(getDoctorLabel());
        add(getNurseLabel());
        add(getOKButton());
    }

    public JLabel getDoctorLabel() {
        if(doctorLabel == null){
            doctorLabel = new JLabel("Doctors: ");
            doctorLabel.setBounds(10,10,80,20);
        }
        return doctorLabel;
    }

    public JLabel getNurseLabel() {
        if(nurseLabel == null){
            nurseLabel = new JLabel("Nurses: ");
            nurseLabel.setBounds(10,230,80,20);
        }
        return nurseLabel;
    }

    public JButton getOKButton() {
        if(OKButton == null){
            OKButton = new JButton("OK");
            OKButton.setBounds(200,460,80,20);
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return OKButton;
    }

    public void initDoctorTable(){
        String[] columnNames = {"ID","Name","Department","Gender","Phone_Number",
                "Experience"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) doctorTable.getModel();

        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = doctorTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List myDoctorInfos = getMyDoctors();
        for(int i=0; i<myDoctorInfos.size(); i++){
            List thisInfo = (List) myDoctorInfos.get(i);
            DoctorInfo doctorInfo = DoctorInfo.getDoctorInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(doctorInfo.getD_ID() != null && doctorInfo.getD_ID() != 0){
                row[0] = doctorInfo.getD_ID();
                row[1] = doctorInfo.getD_name();
                row[2] = doctorInfo.getD_dept();
                row[3] = doctorInfo.getD_gender();
                row[4] = doctorInfo.getD_phone_number();
                row[5] = doctorInfo.getD_year_of_experience();
                defaultTableModel.addRow(row);
                // numberOfDoctor++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        doctorTable.setRowSorter(tableRowSorter);

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

    public void initNurseTable(){
        String[] columnNames = {"ID","Name","Gender","Phone_Number"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) nurseTable.getModel();

        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = nurseTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List myNurseInfos = getMyNurses();
        for(int i=0; i<myNurseInfos.size(); i++){
            List thisInfo = (List) myNurseInfos.get(i);
            NurseInfo nurseInfo = NurseInfo.getNurseInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(nurseInfo.getN_ID() != null && nurseInfo.getN_ID() != 0){
                row[0] = nurseInfo.getN_ID();
                row[1] = nurseInfo.getN_name();
                row[2] = nurseInfo.getN_gender();
                row[3] = nurseInfo.getN_phone_number();
                defaultTableModel.addRow(row);
                // numberOfNurse++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        nurseTable.setRowSorter(tableRowSorter);

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

    public List getMyDoctors() {
        String sql = "SELECT * FROM Doctor WHERE d_ID IN (SELECT diag_d_ID " +
                "from Diagnosis where Diagnosis.diag_p_ID=" + p_ID + ");";
        List list = Dao.findForList(sql);
        return list;
    }

    public List getMyNurses() {
        String sql = "SELECT * FROM Nurse WHERE n_ID IN (\n" +
                "  SELECT govern_n_ID FROM Govern WHERE govern_room_number IN(\n" +
                "    SELECT room_number FROM Room_livein WHERE r_p_ID=" + p_ID + "));";
        List list = Dao.findForList(sql);
        return list;
    }
}
