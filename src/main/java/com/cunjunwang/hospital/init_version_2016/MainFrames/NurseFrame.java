package com.cunjunwang.hospital.init_version_2016.MainFrames;

import com.cunjunwang.hospital.init_version_2016.DesktopPanel;
import com.cunjunwang.hospital.init_version_2016.GUIFrames.*;
import com.cunjunwang.hospital.init_version_2016.GUIFrames.MyRoomFrame;
import com.cunjunwang.hospital.init_version_2016.GUIFrames.NurseProfileFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CunjunWang on 16/11/1.
 */
public class NurseFrame extends JFrame {

    private JButton myProfileButton;
    private JButton myRoomButton;
    //private JButton addRoom;
    private JButton exit;

    private Panel panel = new Panel();

    private JInternalFrame myProfileFrame;
    private JInternalFrame myRoomFrame;
    //private JInternalFrame addRoomFrame;

    private DesktopPanel desktop;

    private String userName;
    private String userType;
    private String userID;

    private int nextFrameX = 0;
    private int nextFrameY = 0;

    public NurseFrame(String userName, String userType, String userID) {
        super();
        setTitle("CPSC304_HospitalDB_NurseFrame");
        this.userName = userName;
        this.userType = userType;
        this.userID = userID;

        int patientFrameWidth = 800;
        int patientFrameHeight = 600;
        setSize(patientFrameWidth, patientFrameHeight);
        Dimension size = getToolkit().getScreenSize();
        setLocation((size.width - patientFrameWidth) / 2, (size.height - patientFrameHeight) / 2);

        panel.add(getMyProfileButton());
        panel.add(getMyRoomButton());
        //panel.add(getAddRoomButton());
        panel.add(getExit());
        add(panel, BorderLayout.NORTH);

        getDesktopPanel();
        desktop.setOpaque(true);
        add(desktop, BorderLayout.CENTER);
    }

    private DesktopPanel getDesktopPanel() {
        if (desktop == null) {
            desktop = new DesktopPanel();
        }
        return desktop;
    }

    private JButton getMyProfileButton(){
        if(myProfileButton == null){
            myProfileButton = new JButton("My Profile");
            panel.add(myProfileButton);
            myProfileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createMyProfileFrame();
                }
            });
        }
        return myProfileButton;
    }

    private JButton getMyRoomButton(){
        if(myRoomButton == null){
            myRoomButton = new JButton("My Room");
            panel.add(myRoomButton);
            myRoomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createMyRoomFrame();
                }
            });
        }
        return myRoomButton;
    }


//    private JButton getAddRoomButton(){
//        if(addRoom == null){
//            addRoom = new JButton("Add Room");
//            panel.add(addRoom);
//            addRoom.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    createAddRoomFrame();
//                }
//            });
//        }
//        return addRoom;
//    }


    public JButton getExit(){
        if(exit == null){
            exit = new JButton("Exit");
            panel.add(exit);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return exit;
    }

    public JInternalFrame createMyProfileFrame(){

        try {
            myProfileFrame = new NurseProfileFrame(userName,userType,userID);
            myProfileFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = myProfileFrame.getPreferredSize().height;
            int panelH = myProfileFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + myProfileFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + myProfileFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(myProfileFrame);
            myProfileFrame.setResizable(false);
            myProfileFrame.setMaximizable(false);
            myProfileFrame.setVisible(true);

            myProfileFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            myProfileFrame.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameActivated(InternalFrameEvent e) {
                    super.internalFrameActivated(e);
                    JInternalFrame frame = e.getInternalFrame();
                    // stateLabel.setText(frame.getTitle());
                }
                public void internalFrameDeactivated(InternalFrameEvent e) {
                    // stateLabel.setText("No window selected");
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return myProfileFrame;
    }

    public JInternalFrame createMyRoomFrame(){
        try {
            myRoomFrame = new MyRoomFrame(userName,userID);
            myRoomFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = myRoomFrame.getPreferredSize().height;
            int panelH = myRoomFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + myRoomFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + myRoomFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(myRoomFrame);
            myRoomFrame.setResizable(false);
            myRoomFrame.setMaximizable(false);
            myRoomFrame.setVisible(true);

            myRoomFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            myRoomFrame.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameActivated(InternalFrameEvent e) {
                    super.internalFrameActivated(e);
                    JInternalFrame frame = e.getInternalFrame();
                    // stateLabel.setText(frame.getTitle());
                }
                public void internalFrameDeactivated(InternalFrameEvent e) {
                    // stateLabel.setText("No window selected");
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return myRoomFrame;
    }


//    public JInternalFrame createAddRoomFrame(){
//
//        try {
//            addRoomFrame = new AddMoneyFrame(userName,userType,userID);
//            addRoomFrame.setLocation(nextFrameX, nextFrameY);
//
//            int frameH = addRoomFrame.getPreferredSize().height;
//            int panelH = addRoomFrame.getContentPane().getPreferredSize().height;
//            int fSpacing = frameH - panelH;
//            nextFrameX += fSpacing;
//            nextFrameY += fSpacing;
//            if (nextFrameX + addRoomFrame.getWidth() > desktop.getWidth())
//                nextFrameX = 0;
//            if (nextFrameY + addRoomFrame.getHeight() > desktop.getHeight())
//                nextFrameY = 0;
//            desktop.add(addRoomFrame);
//            addRoomFrame.setResizable(false);
//            addRoomFrame.setMaximizable(false);
//            addRoomFrame.setVisible(true);
//
//            addRoomFrame.setSelected(true);
//            // stateLabel.setText(iFrame.getTitle());
//            addRoomFrame.addInternalFrameListener(new InternalFrameAdapter() {
//                public void internalFrameActivated(InternalFrameEvent e) {
//                    super.internalFrameActivated(e);
//                    JInternalFrame frame = e.getInternalFrame();
//                    // stateLabel.setText(frame.getTitle());
//                }
//                public void internalFrameDeactivated(InternalFrameEvent e) {
//                    // stateLabel.setText("No window selected");
//                }
//            });
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return addRoomFrame;
//    }


}
