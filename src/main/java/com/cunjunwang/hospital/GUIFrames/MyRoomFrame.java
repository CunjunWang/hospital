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
 * Created by nancyyoung on 2016-11-09.
 */
public class MyRoomFrame extends JInternalFrame {
    private String userName;
    private String userType;
    private String userID;
    private JButton OKButton;

    private JLabel roomLabel;
    private JTable roomTable;

    private JLabel searchLabel;
    private JTextField textFilter;

    private int n_ID;
    private String n_name;

    private Dao dao = new Dao();


    public MyRoomFrame(String userName, String userID){
        super();
        setClosable(true);
        setBounds(10,10,500,320);
        setTitle("My Room");
        setLayout(null);
        setVisible(true);

//        this.userName = userName;
//        this.userType = userType;
        this.userID = userID;
        this.userName = userName;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //getDoctorData();

        add(getTextFilter());
        roomTable = new JTable();
        roomTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        initRoomTable();

        JScrollPane roomScrollPanel = new JScrollPane(roomTable);
        roomScrollPanel.setBounds(10,40,450,180);
        add(roomScrollPanel);

        add(getRoomLabel());
        add(getSearchLabel());
        add(getOKButton());

//        List myRoomInfos = getMyRooms();
//        System.out.println(myRoomInfos);
//        System.out.println(userID);
//        System.out.println(userName);

    }

    public JLabel getRoomLabel() {
        if(roomLabel == null){
            roomLabel = new JLabel("Rooms: ");
            roomLabel.setBounds(10,10,80,20);
        }
        return roomLabel;
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

    public void initRoomTable(){
        String[] columnNames = {"Room Number","Patient ID","Patient name"};

        DefaultTableModel defaultTableModel = (DefaultTableModel) roomTable.getModel();

        defaultTableModel.setColumnIdentifiers(columnNames);

        JTextField readOnlyField = new JTextField(0);
        readOnlyField.setEditable(false);
        DefaultCellEditor readOnlyEditor = new DefaultCellEditor(readOnlyField);

        for(int i=0; i<columnNames.length; i++){
            TableColumn tableColumn = roomTable.getColumnModel().getColumn(i);
            tableColumn.setCellEditor(readOnlyEditor);
        }

        List myRoomInfos = getMyRooms();
        for(int i=0; i<myRoomInfos.size(); i++){
            List thisInfo = (List) myRoomInfos.get(i);
            String p_ID = (String) thisInfo.get(1);
            Object[] row = new Object[columnNames.length];
            if(p_ID != null && Integer.parseInt(p_ID) != 0){
                row[0] = thisInfo.get(0);
                row[1] = thisInfo.get(1);
                row[2] = thisInfo.get(2);
                defaultTableModel.addRow(row);
            }
        }

        // Row sorter
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<TableModel>(defaultTableModel);
        Comparator numberSorter = new NumberSorter();
        tableRowSorter.setComparator(0, numberSorter);
        tableRowSorter.setComparator(1, numberSorter);
        roomTable.setRowSorter(tableRowSorter);

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


    public List getMyRooms() {
        String sql = "SELECT room_number, r_p_ID, p_name FROM Room_livein rl, Patient p WHERE rl.r_p_ID " +
                "= p.p_ID AND room_number IN (SELECT govern_room_number FROM Govern WHERE govern_n_ID="+ userID + ");";
        List list = Dao.findForList(sql);
        return list;
    }
}
