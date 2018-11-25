package com.cunjunwang.hospital.GUIFrames;

import com.cunjunwang.hospital.DataAccess.Models.RoomInfo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/30.
 */
public class RoomList extends JInternalFrame {
    private final JTable table;
    private int numberOfRoom = 0;
    private JTextField textFilter;

    public RoomList(){
        super();
        setClosable(true);
        setTitle("Room Status Overview");
        setBounds(100, 100, 345, 400);
        setLayout(null);
        textFilter = new JTextField();
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        initTable();

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10,70,300,275);
        add(scrollPanel);

        JLabel numberLabel = new JLabel("Number Of Rooms: ");
        JLabel numberOfRoomField = new JLabel();
        numberLabel.setBounds(10,10,150,20);
        numberOfRoomField.setBounds(160,10,50,20);
        numberOfRoomField.setText(String.valueOf(numberOfRoom));
        add(numberLabel);
        add(numberOfRoomField);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(10,40,70,20);
        textFilter.setBounds(90,40,200,20);
        add(searchLabel);
        add(textFilter);
    }


    private void initTable(){
        String[] columnNames = {"Room Number","Is Assigned?"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = table.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List roomInfos = RoomInfo.getRoomInfos();
        for(int i=0; i<roomInfos.size(); i++){
            List thisInfo = (List) roomInfos.get(i);
            RoomInfo roomInfo = RoomInfo.getRoomInfo(thisInfo);
            Object[] row = new Object[columnNames.length];
            if(roomInfo.getRoom_number() != null && roomInfo.getRoom_number() != 0){
                row[0] = roomInfo.getRoom_number();
                row[1] = roomInfo.getRoom_is_assigned();
                defaultTableModel.addRow(row);
                numberOfRoom++;
            }
        }

        // Row sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(defaultTableModel);
        table.setRowSorter(sorter);

        // Row Filter to search the table
        // http://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
        textFilter.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textFilter.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textFilter.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
