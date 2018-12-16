package com.cunjunwang.hospital.emp.init_version_2016.GUIFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Dao;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.DoctorInfo;
import com.cunjunwang.hospital.emp.init_version_2016.DataAccess.Models.MedicineInfo;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by CunjunWang on 16/11/4.
 */
public class AssignMedicineFrame extends JInternalFrame {

    private String userName;
    private String userType;
    private String userID;

    private JButton assignButton;
    private JButton cancelButton;

    private JLabel patientLabel;
    private JLabel medicineLabel;

    private JTable patientTable;
    private JTable medicineTable;

    private JLabel searchPatientLabel;
    private JLabel searchMedicineLabel;
    private JTextField patientFilter;
    private JTextField medicineFilter;

    private int d_ID;
    private int m_ID;

    private JLabel assignLabel;
    private JLabel medicineIDLabel;
    private JTextField medicineIDField;

    private JLabel toLabel;
    private JLabel patientIDLabel;
    private JTextField patientIDField;


    private Dao dao = new Dao();

    public AssignMedicineFrame(String userName, String userType, String userID){
        super();
        setClosable(true);
        setBounds(10,10,700,490);
        setTitle("Assign Medicine");
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
        add(getMedicineFilter());

        getDoctorData();

        patientTable = new JTable();
        patientTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        medicineTable = new JTable();
        medicineTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        initPatientTable();
        initMedicineTable();

        JScrollPane patientScrollPanel = new JScrollPane(patientTable);
        patientScrollPanel.setBounds(10,40,450,180);
        add(patientScrollPanel);

        JScrollPane medicineScrollPanel = new JScrollPane(medicineTable);
        medicineScrollPanel.setBounds(10,260,450,180);
        add(medicineScrollPanel);

        add(getPatientLabel());
        add(getMedicineLabel());
        add(getSearchPatientLabel());
        add(getSearchMedicineLabel());
        add(getAssignLabel());
        add(getMedicineIDLabel());
        add(getMedicineIDField());
        add(getToLabel());
        add(getPatientIDLabel());
        add(getPatientIDField());
        add(getAssignButton());
        add(getCancelButton());

    }

    public JLabel getPatientLabel() {
        if(patientLabel == null){
            patientLabel = new JLabel("Patients: ");
            patientLabel.setBounds(10,10,80,20);
        }
        return patientLabel;
    }

    public JLabel getMedicineLabel() {
        if(medicineLabel == null){
            medicineLabel = new JLabel("Medicines: ");
            medicineLabel.setBounds(10,230,80,20);
        }
        return medicineLabel;
    }

    public JLabel getSearchPatientLabel() {
        if(searchPatientLabel == null){
            searchPatientLabel = new JLabel("Search: ");
            searchPatientLabel.setBounds(100,10,80,20);
        }
        return searchPatientLabel;
    }

    public JLabel getSearchMedicineLabel() {
        if(searchMedicineLabel == null){
            searchMedicineLabel = new JLabel("Search: ");
            searchMedicineLabel.setBounds(100,230,80,20);
        }
        return searchMedicineLabel;
    }

    public JTextField getPatientFilter() {
        if(patientFilter == null){
            patientFilter = new JTextField();
            patientFilter.setBounds(190,10,80,20);
        }
        return patientFilter;
    }

    public JTextField getMedicineFilter() {
        if(medicineFilter == null){
            medicineFilter = new JTextField();
            medicineFilter.setBounds(190,230,80,20);
        }
        return medicineFilter;
    }

    public JLabel getAssignLabel() {
        if(assignLabel == null){
            assignLabel = new JLabel("Assign");
            assignLabel.setBounds(480,100,80,20);
        }
        return assignLabel;
    }

    public JLabel getMedicineIDLabel() {
        if(medicineIDLabel == null){
            medicineIDLabel = new JLabel("Medicine ID: ");
            medicineIDLabel.setBounds(480,130,100,20);
        }
        return medicineIDLabel;
    }

    public JTextField getMedicineIDField() {
        if(medicineIDField == null){
            medicineIDField = new JTextField();
            medicineIDField.setBounds(590,130,50,20);
        }
        return medicineIDField;
    }

    public JLabel getToLabel() {
        if(toLabel == null){
            toLabel = new JLabel("TO");
            toLabel.setBounds(480,160,50,20);
        }
        return toLabel;
    }

    public JLabel getPatientIDLabel() {
        if(patientIDLabel == null){
            patientIDLabel = new JLabel("Patient ID: ");
            patientIDLabel.setBounds(480,190,100,20);
        }
        return patientIDLabel;
    }

    public JTextField getPatientIDField() {
        if(patientIDField == null){
            patientIDField = new JTextField();
            patientIDField.setBounds(590,190,50,20);
        }
        return patientIDField;
    }

    public JButton getAssignButton() {
        if(assignButton == null){
            assignButton = new JButton("Assign");
            assignButton.setBounds(530,300,80,20);
            assignButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int p_ID = Integer.parseInt(patientIDField.getText());
                    if(!checkIHaveThisPatient(p_ID)){
                        JOptionPane.showMessageDialog(assignButton,"You are not allowed to" +
                                " assign medicine to this Patient!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    int m_ID = Integer.parseInt(medicineIDField.getText());
                    if(!checkHasThisMedicine(m_ID)){
                        JOptionPane.showMessageDialog(assignButton,"We don't have this Medicine!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    if(!checkStock(m_ID)){
                        JOptionPane.showMessageDialog(assignButton,"We don't have stock for this Medicine! \n" +
                                        "Please contact manager!",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        updatePatientsMedicine(p_ID,m_ID);
                        updatePatientAccount(p_ID,m_ID);
                        doDefaultCloseAction();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(assignButton,"Unknown Error occurs, \n" +
                                "Please Contact Manager", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        return assignButton;
    }

    public JButton getCancelButton() {
        if(cancelButton == null){
            cancelButton = new JButton("Cancel");
            cancelButton.setBounds(530,330,80,20);
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

    public void initMedicineTable(){
        String[] columnNames = {"ID","Name","Description","Stock"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) medicineTable.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = medicineTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List medicineInfos = MedicineInfo.getMedicineInfos();
        for(int i=0; i<medicineInfos.size(); i++){
            List thisInfo = (List) medicineInfos.get(i);
            MedicineInfo medicineInfo = MedicineInfo.getMedicineInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(medicineInfo.getM_ID() != null && medicineInfo.getM_ID() != 0){
                row[0] = medicineInfo.getM_ID();
                row[1] = medicineInfo.getM_name();
                row[2] = medicineInfo.getM_description();
                row[3] = medicineInfo.getM_stock();
                defaultTableModel.addRow(row);
                // numberOfMedicine++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(1,numberSorter);
        tableRowSorter.setComparator(3,numberSorter);
        medicineTable.setRowSorter(tableRowSorter);

        // Row Filter to search the table
        // http://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
        medicineFilter.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = medicineFilter.getText();
                if (text.trim().length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = medicineFilter.getText();
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

    public boolean checkHasThisMedicine(int inputID){
        List existedIDNamelist = dao.findForList("SELECT m_ID,m_name FROM Medicine");
        int existedMedicineSize = existedIDNamelist.size();
        ArrayList<Integer> existedMedicineList = new ArrayList<>();
        for(int i=0; i<existedMedicineSize; i++){
            ArrayList thisData = (ArrayList) existedIDNamelist.get(i);
            int thisID = Integer.parseInt((String) thisData.get(0));
            existedMedicineList.add(thisID);
        }
        return existedMedicineList.contains(inputID);
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

    public boolean checkStock(int inputID){
        String sql = "SELECT m_stock FROM Medicine WHERE m_ID=" + inputID + ";";
        List thisMedicine = dao.findForList(sql);
        ArrayList stockList = (ArrayList) thisMedicine.get(0);
        int stock = Integer.parseInt((String) stockList.get(0));
        return stock > 0;
    }

    public List getMyPatients() {
        String sql = "SELECT * FROM Patient WHERE p_ID IN (SELECT diag_p_ID " +
                "FROM Diagnosis WHERE diag_d_ID=" + d_ID + ");";
        List list = Dao.findForList(sql);
        return list;
    }

    public void updatePatientsMedicine(int p_ID, int m_ID) throws SQLException {
        String query = "INSERT INTO Needed_Knows VALUES(" + m_ID + "," + p_ID + ");";
        System.out.println(query);
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public void updatePatientAccount(int p_ID, int m_ID) throws SQLException {
        String query1 = "SELECT m_price FROM Medicine WHERE m_ID=" + m_ID + ";";
        List thisMedicine = dao.findForList(query1);
        ArrayList stockList = (ArrayList) thisMedicine.get(0);
        String price = (String) stockList.get(0);

        String query2 = "UPDATE Pay_bill SET amount=amount+" + price + " WHERE" +
                " payer_ID=" + p_ID + ";";
        PreparedStatement preparedStmt = dao.getConnection().prepareStatement(query2);
        preparedStmt.executeUpdate();
    }


}
