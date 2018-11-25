package com.cunjunwang.hospital.MainFrames;

import com.cunjunwang.hospital.DesktopPanel;
import CPSC304_HospitalDB.GUIFrames.*;
import com.cunjunwang.hospital.GUIFrames.*;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CunjunWang on 16/10/30.
 */

public class PatientFrame extends JFrame {

    private JButton myProfileButton;
    private JButton myMedicineButton;
    private JButton payMyBill;
    private JButton addMoney;
    private JButton whoTakesCareofMe;
    private JButton exit;

    private Panel panel = new Panel();

    private JInternalFrame myProfileFrame;
    private JInternalFrame myMedicineFrame;
    private JInternalFrame payMyBillFrame;
    private JInternalFrame addMoneyFrame;
    private JInternalFrame whoTakesCareOfMEFrame;

    private DesktopPanel desktop;

    private String userName;
    private String userType;
    private String userID;

    private int nextFrameX = 0;
    private int nextFrameY = 0;

    public PatientFrame(String userName, String userType, String userID) {
        super();
        setTitle("CPSC304_HospitalDB_PatientFrame");
        this.userName = userName;
        this.userType = userType;
        this.userID = userID;

        int patientFrameWidth = 800;
        int patientFrameHeight = 600;
        setSize(patientFrameWidth, patientFrameHeight);
        Dimension size = getToolkit().getScreenSize();
        setLocation((size.width - patientFrameWidth) / 2, (size.height - patientFrameHeight) / 2);

        panel.add(getMyProfileButton());
        panel.add(getMyMedicineButton());
        panel.add(getPayMyBillButton());
        panel.add(getAddMoneyButton());
        panel.add(getWhoTakesCareOfMeButton());
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

    private JButton getMyMedicineButton(){
        if(myMedicineButton == null){
            myMedicineButton = new JButton("My Medicine");
            panel.add(myMedicineButton);
            myMedicineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createMyMedicineFrame();
                }
            });
        }
        return myMedicineButton;
    }

    private JButton getPayMyBillButton(){
        if(payMyBill == null){
            payMyBill = new JButton("Pay my Bill");
            panel.add(payMyBill);
            payMyBill.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createPayMyBillFrame();
                }
            });
        }
        return payMyBill;
    }

    private JButton getAddMoneyButton(){
        if(addMoney == null){
            addMoney = new JButton("Add Money");
            panel.add(addMoney);
            addMoney.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createAddMoneyFrame();
                }
            });
        }
        return addMoney;
    }

    private JButton getWhoTakesCareOfMeButton(){
        if(whoTakesCareofMe == null){
            whoTakesCareofMe = new JButton("Who Takes Care Of ME");
            panel.add(whoTakesCareofMe);
            whoTakesCareofMe.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createWhoTakesCareOfMeFrame();
                }
            });
        }
        return whoTakesCareofMe;
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
            myProfileFrame = new MyProfileFrame(userName,userType,userID);
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

    public JInternalFrame createMyMedicineFrame(){
        try {
            myMedicineFrame = new MyMedicineFrame(userName,userType,userID);
            myMedicineFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = myMedicineFrame.getPreferredSize().height;
            int panelH = myMedicineFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + myMedicineFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + myMedicineFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(myMedicineFrame);
            myMedicineFrame.setResizable(false);
            myMedicineFrame.setMaximizable(false);
            myMedicineFrame.setVisible(true);

            myMedicineFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            myMedicineFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return myMedicineFrame;
    }

    public JInternalFrame createPayMyBillFrame(){

        try {
            payMyBillFrame = new PayMyBillFrame(userName,userType,userID);
            payMyBillFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = payMyBillFrame.getPreferredSize().height;
            int panelH = payMyBillFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + payMyBillFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + payMyBillFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(payMyBillFrame);
            payMyBillFrame.setResizable(false);
            payMyBillFrame.setMaximizable(false);
            payMyBillFrame.setVisible(true);

            payMyBillFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            payMyBillFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return payMyBillFrame;
    }

    public JInternalFrame createAddMoneyFrame(){

        try {
            addMoneyFrame = new AddMoneyFrame(userName,userType,userID);
            addMoneyFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = addMoneyFrame.getPreferredSize().height;
            int panelH = addMoneyFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + addMoneyFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + addMoneyFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(addMoneyFrame);
            addMoneyFrame.setResizable(false);
            addMoneyFrame.setMaximizable(false);
            addMoneyFrame.setVisible(true);

            addMoneyFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            addMoneyFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return addMoneyFrame;
    }

    private JInternalFrame createWhoTakesCareOfMeFrame(){
        try {
            whoTakesCareOfMEFrame = new WhoTakesCareOfMeFrame(userName,userType,userID);
            whoTakesCareOfMEFrame.setLocation(nextFrameX, nextFrameY);

            int frameH = whoTakesCareOfMEFrame.getPreferredSize().height;
            int panelH = whoTakesCareOfMEFrame.getContentPane().getPreferredSize().height;
            int fSpacing = frameH - panelH;
            nextFrameX += fSpacing;
            nextFrameY += fSpacing;
            if (nextFrameX + whoTakesCareOfMEFrame.getWidth() > desktop.getWidth())
                nextFrameX = 0;
            if (nextFrameY + whoTakesCareOfMEFrame.getHeight() > desktop.getHeight())
                nextFrameY = 0;
            desktop.add(whoTakesCareOfMEFrame);
            whoTakesCareOfMEFrame.setResizable(false);
            whoTakesCareOfMEFrame.setMaximizable(false);
            whoTakesCareOfMEFrame.setVisible(true);

            whoTakesCareOfMEFrame.setSelected(true);
            // stateLabel.setText(iFrame.getTitle());
            whoTakesCareOfMEFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
        return whoTakesCareOfMEFrame;
    }
}
