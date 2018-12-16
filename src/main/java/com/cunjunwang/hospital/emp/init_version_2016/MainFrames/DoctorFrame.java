package com.cunjunwang.hospital.emp.init_version_2016.MainFrames;

import com.cunjunwang.hospital.emp.init_version_2016.DesktopPanel;
import com.cunjunwang.hospital.emp.init_version_2016.GUIFrames.AssignMedicineFrame;
import com.cunjunwang.hospital.emp.init_version_2016.GUIFrames.DoctorAddPatientFrame;
import com.cunjunwang.hospital.emp.init_version_2016.GUIFrames.DoctorProfileFrame;
import com.cunjunwang.hospital.emp.init_version_2016.GUIFrames.MyPatientFrame;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CunjunWang on 16/10/30.
 */
public class DoctorFrame extends JFrame {

    private JButton myProfileButton;
    private JButton myPatientButton;
    private JButton addPatientButton;
    private JButton assignMedicineButton;

    private JButton exit;

    private Panel panel = new Panel();

    private JInternalFrame myProfileFrame;
    private JInternalFrame myPatientFrame;
    private JInternalFrame doctorAddPatientFrame;
    private JInternalFrame assignMedicineFrame;

    private DesktopPanel desktop;

    private String userName;
    private String userType;
    private String userID;

    private int nextFrameX = 0;
    private int nextFrameY = 0;

    public DoctorFrame(String userName, String userType, String userID) {
        super();
        setTitle("CPSC304_HospitalDB_DoctorFrame");
        this.userName = userName;
        this.userType = userType;
        this.userID = userID;

        int doctorFrameWidth = 800;
        int doctorFrameHeight = 600;
        setSize(doctorFrameWidth, doctorFrameHeight);
        Dimension size = getToolkit().getScreenSize();
        setLocation((size.width - doctorFrameWidth) / 2, (size.height - doctorFrameHeight) / 2);

        panel.add(getMyProfileButton());
        panel.add(getMyPatientButton());
        panel.add(getAddPatientButton());
        panel.add(getAssignMedicineButton());
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

    private JButton getMyPatientButton(){
        if(myPatientButton == null){
            myPatientButton = new JButton("My Patient");
            panel.add(myPatientButton);
            myPatientButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createMyPatientFrame();
                }
            });
        }
        return myPatientButton;
    }

    public JButton getAddPatientButton() {
        if(addPatientButton == null){
            addPatientButton = new JButton("Add Patient");
            panel.add(addPatientButton);
            addPatientButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createDoctorAddPatientFrame();
                }
            });
        }
        return addPatientButton;
    }

    public JButton getAssignMedicineButton() {
        if(assignMedicineButton == null){
            assignMedicineButton = new JButton("Assign Medicine");
            panel.add(assignMedicineButton);
            assignMedicineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createAssignMedicineFrame();
                }
            });
        }
        return assignMedicineButton;
    }

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
            myProfileFrame = new DoctorProfileFrame(userName,userType,userID);
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

    public JInternalFrame createMyPatientFrame(){
        try {
            myPatientFrame = new MyPatientFrame(userName,userType,userID);
            myPatientFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = myPatientFrame.getPreferredSize().height;
            int panelH = myPatientFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + myPatientFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + myPatientFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(myPatientFrame);
            myPatientFrame.setResizable(false);
            myPatientFrame.setMaximizable(false);
            myPatientFrame.setVisible(true);

            myPatientFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            myPatientFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return myPatientFrame;
    }

    public JInternalFrame createAssignMedicineFrame(){

        try {
            assignMedicineFrame = new AssignMedicineFrame(userName,userType,userID);
            assignMedicineFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = assignMedicineFrame.getPreferredSize().height;
            int panelH = assignMedicineFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + assignMedicineFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + assignMedicineFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(assignMedicineFrame);
            assignMedicineFrame.setResizable(false);
            assignMedicineFrame.setMaximizable(false);
            assignMedicineFrame.setVisible(true);

            assignMedicineFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            assignMedicineFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return assignMedicineFrame;
    }

    public JInternalFrame createDoctorAddPatientFrame(){

        try {
            doctorAddPatientFrame = new DoctorAddPatientFrame(userName,userType,userID);
            doctorAddPatientFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = doctorAddPatientFrame.getPreferredSize().height;
            int panelH = doctorAddPatientFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + doctorAddPatientFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + doctorAddPatientFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(doctorAddPatientFrame);
            doctorAddPatientFrame.setResizable(false);
            doctorAddPatientFrame.setMaximizable(false);
            doctorAddPatientFrame.setVisible(true);

            doctorAddPatientFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            doctorAddPatientFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return doctorAddPatientFrame;
    }
}
