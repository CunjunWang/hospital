package com.cunjunwang.hospital.MenuBars;

import CPSC304_HospitalDB.GUIFrames.*;
import com.cunjunwang.hospital.GUIFrames.*;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CunjunWang on 16/10/18.
 */
public class MenuBar extends JMenuBar{


    private JMenu overviewMenu = null;
    private JMenuItem doctorOverview = null;
    private JMenuItem nurseOverview = null;
    private JMenuItem roomOverview;
    private JMenuItem medicineOverview;
    private JMenuItem patientOverview;

    private JMenu managementMenu = null;
    private JMenuItem salaryManagement = null;

    private JMenu doctorManagement;
    private JMenuItem addDoctor = null;
    private JMenuItem updateDoctor;
    private JMenuItem dismissDoctor;

    private JMenu medicineManagement = null;
    private JMenuItem addMedicine = null;
    private JMenuItem updateMedicine = null;

    private JMenu nurseManagement = null;
    private JMenuItem addNurse = null;
    private JMenuItem updateNurse = null;
    private JMenuItem dismissNurse = null;

    private JMenu patientMagement = null;
    private JMenuItem addPatient = null;
    private JMenuItem updatePatient = null;
    private JMenuItem deletePatient;

    private JMenu showStats;
    private JMenuItem showStatistics;
    private JMenuItem someOtherInterestingStat;


    private Map<JMenuItem, JInternalFrame> iFrames = null;

    private JDesktopPane desktopPanel = null;

    private JLabel stateLabel = null;

    private JMenuItem exitItem = null;

    private int nextFrameX = 0;

    private int nextFrameY = 0;



    private MenuBar() {
    }

    public MenuBar(JDesktopPane desktopPanel, JLabel label) {
        super();
        iFrames = new HashMap<JMenuItem, JInternalFrame>();
        this.desktopPanel = desktopPanel;
        this.stateLabel = label;
        initialize();
    }

    private void initialize() {
        this.setSize(new Dimension(600, 24));
        add(getOverlookMenu());
        add(getManagementMenu());
        add(getShowStats());
    }

    public JMenu getOverlookMenu() {
        if (overviewMenu == null) {
            overviewMenu = new JMenu("Data Overview");
            overviewMenu.add(getDoctorOverview());
            overviewMenu.add(getNurseOverview());
            overviewMenu.add(getMedicineOverview());
            overviewMenu.add(getPatientOverview());
            overviewMenu.add(getRoomOverview());
        }
        return overviewMenu;
    }

    public JMenuItem getDoctorOverview() {
        if (doctorOverview == null) {
            doctorOverview = new JMenuItem("Doctor Overview");
            doctorOverview.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(doctorOverview, DoctorList.class);
                }
            });
        }
        return doctorOverview;
    }

    public JMenuItem getNurseOverview(){
        if (nurseOverview == null) {
            nurseOverview = new JMenuItem("Nurse Overview");
            nurseOverview.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(nurseOverview, NurseList.class);
                }
            });
        }
        return nurseOverview;
    }

    public JMenuItem getRoomOverview(){
        if (roomOverview == null) {
            roomOverview = new JMenuItem();
            roomOverview.setText("Room Status Overview");
            roomOverview.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(roomOverview, RoomList.class);
                }
            });
        }
        return roomOverview;
    }

    public JMenuItem getMedicineOverview(){
        if (medicineOverview == null) {
            medicineOverview = new JMenuItem();
            medicineOverview.setText("Medicine In Stock Overview");
            medicineOverview.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(medicineOverview, MedicineList.class);
                }
            });
        }
        return medicineOverview;
    }

    public JMenuItem getPatientOverview(){
        if (patientOverview == null) {
            patientOverview = new JMenuItem();
            patientOverview.setText("Patient Overview");
            patientOverview.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(patientOverview, PatientList.class);
                }
            });
        }
        return patientOverview;
    }

    public JMenu getManagementMenu(){
        if(managementMenu == null){
            managementMenu = new JMenu("Hospital Management");
            managementMenu.add(getSalaryManagement());
            managementMenu.add(getDoctorManagement());
            managementMenu.add(getNurseManagement());
            managementMenu.add(getMedicineManagement());
            managementMenu.add(getPatientManagement());
        }
        return managementMenu;
    }

    public JMenuItem getSalaryManagement(){
        if(salaryManagement == null){
            salaryManagement = new JMenuItem("Salary Management");
            salaryManagement.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(salaryManagement, SalaryManagementFrame.class);
                }
            });
        }
        return salaryManagement;
    }

    public JMenu getDoctorManagement() {
        if(doctorManagement == null){
            doctorManagement = new JMenu("Doctor Management");
            doctorManagement.add(getAddDoctor());
            doctorManagement.add(getUpdateDoctor());
            doctorManagement.add(getDismissDoctor());
        }
        return doctorManagement;
    }

    public JMenuItem getAddDoctor(){
        if(addDoctor == null) {
            addDoctor = new JMenuItem("Add Doctor");
            addDoctor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createIFrame(addDoctor, AddDoctorFrame.class);
                }
            });
        }
        return addDoctor;
    }

    public JMenuItem getUpdateDoctor() {
        if(updateDoctor == null){
            updateDoctor = new JMenuItem("Update Doctor");
            updateDoctor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createIFrame(updateDoctor, UpdateDoctorFrame.class);
                }
            });
        }
        return updateDoctor;
    }

    public JMenuItem getDismissDoctor(){
        if (dismissDoctor == null) {
            dismissDoctor = new JMenuItem("Dismiss Doctor");
            dismissDoctor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(nurseManagement, DismissDoctorFrame.class);
                }
            });
        }
        return dismissDoctor;
    }

    public JMenu getMedicineManagement(){
        if(medicineManagement == null){
            medicineManagement = new JMenu("Medicine Management");
            medicineManagement.add(getAddMedicine());
            medicineManagement.add(getUpdateMedicine());
        }
        return medicineManagement;
    }

    public JMenuItem getAddMedicine() {
        if (addMedicine == null) {
            addMedicine = new JMenuItem("Add Medicine");
            addMedicine.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(medicineManagement, AddMedicineFrame.class);
                }
            });
        }
        return addMedicine;
    }

    public JMenuItem getUpdateMedicine() {
        if (updateMedicine == null) {
            updateMedicine = new JMenuItem("Update Medicine");
            updateMedicine.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(medicineManagement, UpdateMedicineFrame.class);
                }
            });
        }
        return updateMedicine;
    }

    public JMenu getPatientManagement(){
        if(patientMagement == null){
            patientMagement = new JMenu("Patient Management");
            patientMagement.add(getAddPatient());
            patientMagement.add(getUpdatePatient());
            patientMagement.add(getDeletePatient());
        }
        return patientMagement;
    }

    public JMenuItem getAddPatient() {
        if (addPatient == null) {
            addPatient = new JMenuItem("Add Patient");
            addPatient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(patientMagement, AddPatientFrame.class);
                }
            });
        }
        return addPatient;
    }

    public JMenuItem getUpdatePatient() {
        if (updatePatient == null) {
            updatePatient = new JMenuItem("Update Patient");
            updatePatient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(patientMagement, UpdatePatientFrame.class);
                }
            });
        }
        return updatePatient;
    }

    public JMenuItem getDeletePatient() {
        if(deletePatient == null){
            deletePatient = new JMenuItem("Delete Patient");
            deletePatient.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createIFrame(deletePatient,DeletePatientFrame.class);
                }
            });
        }
        return deletePatient;
    }

    //Nurse
    public JMenu getNurseManagement(){
        if(nurseManagement == null){
            nurseManagement = new JMenu("Nurse Management");
            nurseManagement.add(getAddNurse());
            nurseManagement.add(getUpdateNurse());
            nurseManagement.add(getDismissNurse());
        }
        return nurseManagement;
    }

    public JMenuItem getAddNurse() {
        if (addNurse == null) {
            addNurse = new JMenuItem("Add Nurse");
            addNurse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(nurseManagement, AddNurseFrame.class);
                }
            });
        }
        return addNurse;
    }

    public JMenuItem getUpdateNurse() {
        if (updateNurse == null) {
            updateNurse = new JMenuItem("Update Nurse");
            updateNurse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(nurseManagement, UpdateNurseFrame.class);
                }
            });
        }
        return updateNurse;
    }

    public JMenuItem getDismissNurse(){
        if (dismissNurse == null) {
            dismissNurse = new JMenuItem("Dismiss Nurse");
            dismissNurse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(nurseManagement, DismissNurseFrame.class);
                }
            });
        }
        return dismissNurse;
    }

    public JMenu getShowStats() {
        if(showStats == null){
            showStats = new JMenu("Show Stats");
            showStats.add(getShowStatistics());
            showStats.add(getSomeOtherInterestingStat());
        }
        return showStats;
    }

    public JMenuItem getShowStatistics() {
        if(showStatistics == null){
            showStatistics = new JMenuItem("You may interested in...");
            showStatistics.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createIFrame(showStatistics, StatisticsFrame.class);
                }
            });
        }
        return showStatistics;
    }

    public JMenuItem getSomeOtherInterestingStat() {
        if(someOtherInterestingStat == null){
            someOtherInterestingStat = new JMenuItem("Some Other Interesting Fact");
            someOtherInterestingStat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createIFrame(someOtherInterestingStat, SomeOtherInterestingFactFrame.class);
                }
            });
        }
        return someOtherInterestingStat;
    }

    public JMenuItem getExitItem(){
        if (exitItem == null) {
            exitItem = new JMenuItem("Exit");
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
        }
        return exitItem;
    }

    // Credit to a Chinese Java Tutorial book "Java from beginning to expert",
    // ISBN 978-7-302-28756-8
    // Chapter 28
    // Source code can be viewed at http://en.verysource.com/code/3903826_1/menubar.java.html
    private JInternalFrame createIFrame(JMenuItem item, Class clazz) {
        Constructor constructor = clazz.getConstructors()[0];
        JInternalFrame iFrame = iFrames.get(item);
        try {
            if (iFrame == null || iFrame.isClosed()) {
                iFrame = (JInternalFrame) constructor
                        .newInstance(new Object[] {});
                iFrames.put(item, iFrame);
                iFrame.setFrameIcon(item.getIcon());
                iFrame.setLocation(nextFrameX, nextFrameY);
                int frameH = iFrame.getPreferredSize().height;
                int panelH = iFrame.getContentPane().getPreferredSize().height;
                int fSpacing = frameH - panelH;
                nextFrameX += fSpacing;
                nextFrameY += fSpacing;
                if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
                    nextFrameX = 0;
                if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
                    nextFrameY = 0;
                desktopPanel.add(iFrame);
                iFrame.setResizable(false);
                iFrame.setMaximizable(false);
                iFrame.setVisible(true);
            }
            iFrame.setSelected(true);
            stateLabel.setText(iFrame.getTitle());
            iFrame.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameActivated(InternalFrameEvent e) {
                    super.internalFrameActivated(e);
                    JInternalFrame frame = e.getInternalFrame();
                    stateLabel.setText(frame.getTitle());
                }

                public void internalFrameDeactivated(InternalFrameEvent e) {
                    stateLabel.setText("No window selected");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iFrame;
    }
}
