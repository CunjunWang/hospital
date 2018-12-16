package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.DoctorInfo;
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
public class MyPatientFrame extends JInternalFrame {

    private String userName;
    private String userType;
    private String userID;
    private JButton OKButton;

    private JLabel patientLabel;
    private JTable patientTable;

    private JLabel searchLabel;
    private JTextField textFilter;

    private int d_ID;

    private Dao dao = new Dao();


    public MyPatientFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,500,320);
        setTitle("My Patient");
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

        getDoctorData();

        add(getTextFilter());
        patientTable = new JTable();
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        initPatientTable();

        JScrollPane patientScrollPanel = new JScrollPane(patientTable);
        patientScrollPanel.setBounds(10,40,450,180);
        add(patientScrollPanel);

        add(getPatientLabel());
        add(getSearchLabel());
        add(getOKButton());

    }

    public JLabel getPatientLabel() {
        if(patientLabel == null){
            patientLabel = new JLabel("Patients: ");
            patientLabel.setBounds(10,10,80,20);
        }
        return patientLabel;
    }

    public JLabel getSearchLabel() {
        if(searchLabel == null){
            searchLabel = new JLabel("Search: ");
            searchLabel.setBounds(100,10,80,20);
        }
        return searchLabel;
    }

    public JTextField getTextFilter() {
        if(textFilter == null){
            textFilter = new JTextField();
            textFilter.setBounds(190,10,80,20);
        }
        return textFilter;
    }

    public JButton getOKButton() {
        if(OKButton == null){
            OKButton = new JButton("OK");
            OKButton.setBounds(200,250,80,20);
            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return OKButton;
    }

    public void initPatientTable(){
        String[] columnNames = {"ID","Name","City","Gender","Age",
                "Account Balance", "Is Cured?"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) patientTable.getModel();

        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = patientTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List myPatientInfos = getMyPatients();
        for(int i=0; i<myPatientInfos.size(); i++){
            List thisInfo = (List) myPatientInfos.get(i);
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
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(5, numberSorter);
        patientTable.setRowSorter(tableRowSorter);

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

    public void getDoctorData(){
        List doctorInfos = DoctorInfo.getDoctorInfos();
        int doctorInfosSize = doctorInfos.size();
        for(int i=0; i<doctorInfosSize; i++){
            ArrayList thisData = (ArrayList) doctorInfos.get(i);

            if(thisData.get(0).equals(userID) && thisData.get(1).equals(userName)){
                DoctorInfo doctorInfo = DoctorInfo.getDoctorInfo(thisData);
                // System.out.println(patientInfo);
                d_ID = doctorInfo.getD_ID();
                break;
            }
        }
    }

    public List getMyPatients() {
        String sql = "SELECT * FROM Patient WHERE p_ID IN (SELECT diag_p_ID " +
                "FROM Diagnosis WHERE diag_d_ID=" + d_ID + ");";
        List list = Dao.findForList(sql);
        return list;
    }
}
