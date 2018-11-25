package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Dao;
import com.cunjunwang.hospital.NumberSorter;

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
 * Created by CunjunWang on 16/11/12.
 */
public class StatisticsFrame extends JInternalFrame{

    private String userName;
    private String userType;
    private String userID;

    private JLabel whatToQuery;
    private JRadioButton doctorSalary;
    private JRadioButton nurseSalary;
    private ButtonGroup buttonGroup1;

    private JLabel whatCriteria;
    private JRadioButton highest;
    private JRadioButton lowest;
    private ButtonGroup buttonGroup2;
    private JCheckBox groupByDeptBox;
    private JCheckBox findAverageBox;
    private JButton searchButton;
    private JButton cancelButton;

    private JLabel resultLabel;
    private JTable resultTable;

    private JLabel searchInResult;
    private JTextField textFilter;

    public StatisticsFrame(){
        super();
        setClosable(true);
        setBounds(10,10,500,500);
        setTitle("You may be interested in :)");
        setLayout(null);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultTable = new JTable();

        JScrollPane resultScrollPanel = new JScrollPane(resultTable);
        resultScrollPanel.setBounds(10,260,450,180);
        add(resultScrollPanel);

        add(getWhatToQuery());
        getButtonGroup1();
        add(getDoctorSalary());
        add(getNurseSalary());
        add(getWhatCriteria());
        getButtonGroup2();
        add(getHighest());
        add(getLowest());
        add(getGroupByDeptBox());
        add(getFindAverageBox());
        add(getSearchButton());
        add(getCancelButton());
        add(getResultLabel());
        add(getSearchInResult());
        add(getTextFilter());
    }

    public JLabel getWhatToQuery() {
        if(whatToQuery == null){
            whatToQuery = new JLabel("What to query?");
            whatToQuery.setBounds(10,10,100,20);
        }
        return whatToQuery;
    }

    public JRadioButton getDoctorSalary() {
        if(doctorSalary == null){
            doctorSalary = new JRadioButton("Doctor Salary");
            doctorSalary.setBounds(10,40,120,20);
        }
        return doctorSalary;
    }

    public JRadioButton getNurseSalary() {
        if(nurseSalary == null){
            nurseSalary = new JRadioButton("Nurse Salary");
            nurseSalary.setBounds(140,40,120,20);
        }
        return nurseSalary;
    }

    public ButtonGroup getButtonGroup1() {
        if(buttonGroup1 == null){
            buttonGroup1 = new ButtonGroup();
            buttonGroup1.add(getDoctorSalary());
            buttonGroup1.add(getNurseSalary());
        }
        return buttonGroup1;
    }

    public JLabel getWhatCriteria() {
        if(whatCriteria == null){
            whatCriteria = new JLabel("What Criteria?");
            whatCriteria.setBounds(10,70,100,20);
        }
        return whatCriteria;
    }

    public JRadioButton getHighest() {
        if(highest == null){
            highest = new JRadioButton("Highest");
            highest.setBounds(10,100,120,20);
        }
        return highest;
    }

    public JRadioButton getLowest() {
        if(lowest == null){
            lowest = new JRadioButton("Lowest");
            lowest.setBounds(140,100,120,20);
        }
        return lowest;
    }

    public ButtonGroup getButtonGroup2() {
        if(buttonGroup2 == null){
            buttonGroup2 = new ButtonGroup();
            buttonGroup2.add(getHighest());
            buttonGroup2.add(getLowest());
        }
        return buttonGroup2;
    }

    public JCheckBox getGroupByDeptBox() {
        if(groupByDeptBox == null){
            groupByDeptBox = new JCheckBox("Group by department");
            groupByDeptBox.setBounds(270,100,180,20);
        }
        return groupByDeptBox;
    }

    public JCheckBox getFindAverageBox() {
        if(findAverageBox == null){
            findAverageBox = new JCheckBox("Find Average");
            findAverageBox.setBounds(270,130,180,20);
        }
        return findAverageBox;
    }

    public JButton getSearchButton() {
        if(searchButton == null){
            searchButton = new JButton("Search");
            searchButton.setBounds(130,170,80,20);
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String query = formQuery(doctorSalary,nurseSalary,highest,lowest,groupByDeptBox, findAverageBox);
                    if(groupByDeptBox.isSelected() && findAverageBox.isSelected()){
                        initAvgTable(query);
                    }
                    else {
                        initResultTable(query);
                    }
                }
            });
        }
        return searchButton;
    }

    public JButton getCancelButton() {
        if(cancelButton == null){
            cancelButton = new JButton("Cancel");
            cancelButton.setBounds(250,170,80,20);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doDefaultCloseAction();
                }
            });
        }
        return cancelButton;
    }

    public JLabel getResultLabel() {
        if(resultLabel == null){
            resultLabel = new JLabel("Results: ");
            resultLabel.setBounds(10,230,80,20);
        }
        return resultLabel;
    }

    public JLabel getSearchInResult() {
        if(searchInResult == null){
            searchInResult = new JLabel("Search in Results: ");
            searchInResult.setBounds(100,230,160,20);
        }
        return searchInResult;
    }

    public JTextField getTextFilter() {
        if(textFilter == null){
            textFilter = new JTextField();
            textFilter.setBounds(270,230,100,20);
        }
        return textFilter;
    }

    public String formQuery(JRadioButton doctorSalary, JRadioButton nurseSalary, JRadioButton highest,
                                JRadioButton lowest, JCheckBox groupByDeptBox, JCheckBox findAverage) {
        String query = "";
        if(!groupByDeptBox.isSelected()){
            if(doctorSalary.isSelected() && highest.isSelected()){
                if(findAverage.isSelected()){
                    JOptionPane.showMessageDialog(null,"Meaningless to find individual salary with AVG, \n" +
                            "please uncheck Find Average", "Invalid query", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    query = "SELECT * FROM Doctor D1 WHERE D1.d_salary >= ALL (SELECT D2.d_salary From Doctor D2);";
                }
            }
            else if(doctorSalary.isSelected() && lowest.isSelected()){
                if(findAverage.isSelected()){
                    JOptionPane.showMessageDialog(null,"Meaningless to find individual salary with AVG, \n" +
                            "please uncheck Find Average", "Invalid query", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    query = "SELECT * FROM Doctor D1 WHERE D1.d_salary <= ALL (SELECT D2.d_salary From Doctor D2);";
                }
            }
            else if(nurseSalary.isSelected() && highest.isSelected()){
                if(findAverage.isSelected()){
                    JOptionPane.showMessageDialog(null,"Meaningless to find individual salary with AVG, \n" +
                            "please uncheck Find Average", "Invalid query", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    query = "SELECT * FROM Nurse N1 WHERE N1.n_salary >= ALL (SELECT N2.n_salary From Nurse N2);";
                }
            }
            else if(nurseSalary.isSelected() && lowest.isSelected()){
                if(findAverage.isSelected()){
                    JOptionPane.showMessageDialog(null,"Meaningless to find individual salary with AVG, \n" +
                            "please uncheck Find Average", "Invalid query", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    query = "SELECT * FROM Nurse N1 WHERE N1.n_salary <= ALL (SELECT N2.n_salary From Nurse N2);";
                }
            }
        }
        else{
            if(doctorSalary.isSelected() && highest.isSelected()){
                if(findAverage.isSelected()){
                    query = "SELECT D2.d_dept, y.avg FROM (SELECT d_dept,AVG(d_salary) AS avg " +
                            "FROM Doctor D1 GROUP BY d_dept) y, Doctor D2 WHERE D2.d_dept=y.d_dept AND y.avg >= ALL" +
                            " (SELECT MAX(x.avg) FROM (SELECT d_dept, AVG(d_salary) AS avg FROM Doctor GROUP BY d_dept) x);";
                }
                else{
                    query = "SELECT * FROM Doctor D1 WHERE D1.d_salary >= ALL " +
                            "( SELECT d_salary FROM Doctor D2 WHERE D1.d_dept = D2.d_dept GROUP BY D2.d_dept);";
                }
            }
            else if(doctorSalary.isSelected() && lowest.isSelected()){
                if(findAverage.isSelected()){
                    query = "SELECT D2.d_dept, y.avg FROM (SELECT d_dept,AVG(d_salary) AS avg " +
                            "FROM Doctor D1 GROUP BY d_dept) y, Doctor D2 WHERE D2.d_dept=y.d_dept AND y.avg <= ALL " +
                            "(SELECT MIN(x.avg) FROM (SELECT d_dept, AVG(d_salary) AS avg FROM Doctor GROUP BY d_dept) x)";
                }
                else{
                    query = "SELECT * FROM Doctor D1 WHERE D1.d_salary <= ALL " +
                            "( SELECT d_salary FROM Doctor D2 WHERE D1.d_dept = D2.d_dept GROUP BY D2.d_dept);";
                }
            }
            else if(nurseSalary.isSelected() && highest.isSelected()){
                if(findAverage.isSelected()){
                    query = "SELECT N2.n_dept, y.avg FROM (SELECT n_dept,AVG(n_salary) AS avg " +
                            "FROM Nurse N1 GROUP BY n_dept) y, Nurse N2 WHERE N2.n_dept=y.n_dept AND y.avg >= ALL " +
                            "(SELECT MAX(x.avg) FROM (SELECT n_dept, AVG(n_salary) AS avg FROM Nurse GROUP BY n_dept) x)";
                }
                else{
                    query = "SELECT * FROM Nurse N1 WHERE N1.n_salary >= ALL " +
                            "( SELECT n_salary FROM Nurse N2 WHERE N1.n_dept = N2.n_dept GROUP BY N2.n_dept);";
                }
            }
            else if(nurseSalary.isSelected() && lowest.isSelected()){
                if(findAverage.isSelected()){
                    query = "SELECT DISTINCT N2.n_dept, y.avg FROM (SELECT n_dept,AVG(n_salary) AS avg " +
                            "FROM Nurse N1 GROUP BY n_dept) y, Nurse N2 WHERE N2.n_dept=y.n_dept AND y.avg <= ALL " +
                            "(SELECT MIN(x.avg) FROM (SELECT n_dept, AVG(n_salary) AS avg FROM Nurse GROUP BY n_dept) x)";
                }
                else{
                    query = "SELECT * FROM Nurse N1 WHERE N1.n_salary <= ALL " +
                            "( SELECT n_salary FROM Nurse N2 WHERE N1.n_dept = N2.n_dept GROUP BY N2.n_dept);";
                }
            }
        }
        return query;
    }

    public void initResultTable(String query){
        if(!query.equals("")) {
            String[] columnNames = {"ID","Name","Department","Gender","Phone Number","Year of experience",
            "Salary","Is on job?"};

            DefaultTableModel defaultTableModel = (DefaultTableModel) resultTable.getModel();

            defaultTableModel.setRowCount(0);

            defaultTableModel.setColumnIdentifiers(columnNames);

            JTextField readOnlyField = new JTextField(0);
            readOnlyField.setEditable(false);
            DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

            for(int i=0; i<columnNames.length; i++){
                TableColumn tableColumn = resultTable.getColumnModel().getColumn(i);
                tableColumn.setCellEditor(readOnlyEditor);
            }

            List result = Dao.findForList(query);
            System.out.println(result);

            int resultLength = result.size();
            for(int i=0; i<resultLength; i++){
                List thisInfo = (List) result.get(i);
                String ID = (String) thisInfo.get(0);
                Object[] row = new Object[columnNames.length];
                if(ID != null && Integer.parseInt(ID) != 0){
                    row[0] = thisInfo.get(0);
                    row[1] = thisInfo.get(1);
                    row[2] = thisInfo.get(2);
                    row[3] = thisInfo.get(3);
                    row[4] = thisInfo.get(4);
                    row[5] = thisInfo.get(5);
                    row[6] = thisInfo.get(6);
                    row[7] = thisInfo.get(7);
                    defaultTableModel.addRow(row);
                }
            }

            // Row sorter
            TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
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
                }
            });
        }
        else{
            JOptionPane.showMessageDialog(null,"Empty query!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void initAvgTable(String query){
        if(!query.equals("")) {
            String[] columnNames = {"Department", "Avg Salary"};

            DefaultTableModel defaultTableModel = (DefaultTableModel) resultTable.getModel();

            defaultTableModel.setRowCount(0);

            defaultTableModel.setColumnIdentifiers(columnNames);

            JTextField readOnlyField = new JTextField(0);
            readOnlyField.setEditable(false);
            DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

            for(int i=0; i<columnNames.length; i++){
                TableColumn tableColumn = resultTable.getColumnModel().getColumn(i);
                tableColumn.setCellEditor(readOnlyEditor);
            }

            List result = Dao.findForList(query);
            System.out.println(result);

            int resultLength = result.size();
            for(int i=0; i<resultLength; i++){
                List thisInfo = (List) result.get(i);
                String ID = (String) thisInfo.get(0);
                Object[] row = new Object[columnNames.length];
                row[0] = thisInfo.get(0);
                row[1] = thisInfo.get(1);
                defaultTableModel.addRow(row);
            }

            // Row sorter
            TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
            Comparator numberSorter = new NumberSorter();
            tableRowSorter.setComparator(1, numberSorter);
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
                }
            });
        }
        else{
            JOptionPane.showMessageDialog(null,"Empty query!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}

