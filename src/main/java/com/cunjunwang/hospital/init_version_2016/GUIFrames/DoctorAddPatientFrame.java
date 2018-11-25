package com.cunjunwang.hospital.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.DoctorInfo;
import com.cunjunwang.hospital.init_version_2016.DataAccess.Models.PatientInfo;
import com.cunjunwang.hospital.init_version_2016.NumberSorter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/4.
 */
public class DoctorAddPatientFrame extends JInternalFrame {

    private String userName;
    private String userType;
    private String userID;

    private JButton addButton;
    private JButton cancelButton;

    private JLabel myPatientLabel;
    private JLabel searchLabel;
    private JLabel patientIDLabel;
    private JTextField patientIDField;

    private JTable patientTable;

    private JLabel searchPatientLabel;
    private JTextField patientFilter;

    private int d_ID;
    private int m_ID;

    private Dao dao = new Dao();

    public DoctorAddPatientFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,495,330);
        setTitle("Add Patient");
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

        add(getPatientFilter());

        getDoctorData();

        patientTable = new JTable();
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        initPatientTable();

        JScrollPane patientScrollPanel = new JScrollPane(patientTable);
        patientScrollPanel.setBounds(10,90,450,180);
        add(patientScrollPanel);

        add(getPatientIDLabel());
        add(getPatientIDField());
        add(getSearchLabel());
        add(getMyPatientLabel());
        add(getAddButton());
        add(getCancelButton());

    }

    public JLabel getPatientIDLabel() {
        if(patientIDLabel == null){
            patientIDLabel = new JLabel("To add patient ID: ");
            patientIDLabel.setBounds(10,10,120,30);
        }
        return patientIDLabel;
    }

    public JTextField getPatientIDField() {
        if(patientIDField == null){
            patientIDField = new JTextField();
            patientIDField.setBounds(140,10,50,30);
        }
        return patientIDField;
    }

    public JLabel getMyPatientLabel() {
        if(myPatientLabel == null){
            myPatientLabel = new JLabel("My patients: ");
            myPatientLabel.setBounds(10,50,100,30);
        }
        return myPatientLabel;
    }

    public JLabel getSearchLabel() {
        if(searchLabel == null){
            searchLabel = new JLabel("Search: ");
            searchLabel.setBounds(120,50,80,30);
        }
        return searchLabel;
    }


    public JTextField getPatientFilter() {
        if(patientFilter == null){
            patientFilter = new JTextField();
            patientFilter.setBounds(210,50,80,30);
        }
        return patientFilter;
    }


    public JButton getAddButton() {
        if(addButton == null){
            addButton = new JButton("Add");
            addButton.setBounds(370,10,80,30);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int p_ID = Integer.parseInt(patientIDField.getText());
                    if(checkIHaveThisPatient(p_ID)){
                        JOptionPane.showMessageDialog(addButton,"You already have this patient!","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        updateDiagnosisRelation(d_ID,p_ID);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    doDefaultCloseAction();
                }
            });
        }
        return addButton;
    }

    public JButton getCancelButton() {
        if(cancelButton == null){
            cancelButton = new JButton("Cancel");
            cancelButton.setBounds(370,50,80,30);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancelButton;
    }

    public void initPatientTable(){
        String[] columnNames = {"ID","Name","Age","Gender"};

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
                row[2] = patientInfo.getP_age();
                row[3] = patientInfo.getP_gender();
                defaultTableModel.addRow(row);
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(3, numberSorter);
        patientTable.setRowSorter(tableRowSorter);

        // Row Filter to search the table
        // http://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
        patientFilter.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = patientFilter.getText();
                if (text.trim().length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = patientFilter.getText();
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

    public boolean checkIHaveThisPatient(int inputID){
        List myPatientlist = getMyPatients();
        int myPatientSize = myPatientlist.size();
        ArrayList<Integer> myPatientIDList = new ArrayList<>();
        for(int i=0; i<myPatientSize; i++){
            ArrayList thisData = (ArrayList) myPatientlist.get(i);
            int thisID = Integer.parseInt((String) thisData.get(0));
            myPatientIDList.add(thisID);
        }
        return myPatientIDList.contains(inputID);
    }

    public List getMyPatients() {
        String sql = "SELECT * FROM Patient WHERE p_ID IN (SELECT diag_p_ID " +
                "FROM Diagnosis WHERE diag_d_ID=" + d_ID + ");";
        List list = Dao.findForList(sql);
        return list;
    }

    public void updateDiagnosisRelation(int d_ID, int p_ID) throws SQLException{
        String query1 = "SELECT need_m_id FROM Needed_knows WHERE need_p_ID=" + p_ID + ";";
        List theseMedicineID = dao.findForList(query1);
        ArrayList IDList = (ArrayList) theseMedicineID.get(0);
        int temp_m_ID = Integer.parseInt((String) IDList.get(0));

        String query2 = "INSERT INTO Diagnosis VALUES(" + d_ID + "," + p_ID + ","
                + temp_m_ID + ");";
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query2);
        preparedStmt.executeUpdate();
    }

}
