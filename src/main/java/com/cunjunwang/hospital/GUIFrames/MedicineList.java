package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Models.MedicineInfo;
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
public class MedicineList extends JInternalFrame {
    private final JTable table;
    private int numberOfMedicine = 0;
    private JTextField textFilter;

    public MedicineList(){
        super();
        setClosable(true);
        setTitle("Medicine Management");
        setBounds(100, 100, 750, 400);
        setLayout(null);
        textFilter = new JTextField();
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        initTable();

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10,70,705,275);
        add(scrollPanel);

        JLabel numberLabel = new JLabel("Number Of Medicines: ");
        JLabel numberOfMedicineField = new JLabel();
        numberLabel.setBounds(10,10,150,20);
        numberOfMedicineField.setBounds(160,10,50,20);
        numberOfMedicineField.setText(String.valueOf(numberOfMedicine));
        add(numberLabel);
        add(numberOfMedicineField);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(10,40,70,20);
        textFilter.setBounds(90,40,200,20);
        add(searchLabel);
        add(textFilter);
    }


    private void initTable(){
        String[] columnNames = {"ID","Price","Name","Description","Stock"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = table.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List medicineInfos = MedicineInfo.getMedicineInfos();
        for(int i=0; i<medicineInfos.size(); i++){
            List thisInfo = (List) medicineInfos.get(i);
            MedicineInfo medicineInfo = MedicineInfo.getMedicineInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(medicineInfo.getM_ID() != null && medicineInfo.getM_ID() != 0){
                row[0] = medicineInfo.getM_ID();
                row[1] = medicineInfo.getM_price();
                row[2] = medicineInfo.getM_name();
                row[3] = medicineInfo.getM_description();
                row[4] = medicineInfo.getM_stock();
                defaultTableModel.addRow(row);
                numberOfMedicine++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(1,numberSorter);
        tableRowSorter.setComparator(4,numberSorter);
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
