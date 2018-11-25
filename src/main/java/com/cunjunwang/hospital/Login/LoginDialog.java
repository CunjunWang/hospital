package com.cunjunwang.hospital.Login;

import com.cunjunwang.hospital.GUIFrames.MyProfileFrame;
import com.cunjunwang.hospital.MainFrames.DoctorFrame;
import com.cunjunwang.hospital.DataAccess.Dao;
import com.cunjunwang.hospital.MainFrames.MainFrame;
import com.cunjunwang.hospital.MainFrames.NurseFrame;
import com.cunjunwang.hospital.MainFrames.PatientFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by CunjunWang on 16/10/18.
 */
public class LoginDialog extends JFrame{
    private static final long serialVersionUID = 1L;

    private LoginPanel loginPanel = null;

    private JLabel usernameLabel = null;
    private JTextField usernameField = null;

    private JLabel passwordLabel = null;
    private JPasswordField passwordField = null;

    private JLabel userIDLabel = null;
    private JTextField userIDField = null;

    private static String usertype;
    private ButtonGroup buttonGroup;
    private JRadioButton managerType;
    private JRadioButton patientType;
    private JRadioButton doctorType;
    private JRadioButton nurseType;

    private JButton loginButton = null;
    private JButton exitButton = null;
    private static String userName;
    private MainFrame mainFrame;
    private DoctorFrame doctorFrame;
    private PatientFrame patientFrame;
    private NurseFrame nurseFrame;
    private MyProfileFrame myProfileFrame;


    public LoginDialog(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            mainFrame = new MainFrame();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        int loginWidth = 380;
        int loginHeight = 280;
        Dimension size = getToolkit().getScreenSize();
        setLocation((size.width - loginWidth) / 2, (size.height - loginHeight) / 2);
        setSize(loginWidth, loginHeight);
        this.setTitle("System Login");
        setContentPane(getLoginPanel());
    }

    public LoginPanel getLoginPanel() {
        if (loginPanel == null) {
            loginPanel = new LoginPanel();
            loginPanel.setLayout(null);
            Color color = new Color(0xD8DDC7);
            loginPanel.setBackground(color);

            loginPanel.add(getUsernameLabel());
            loginPanel.add(getUserNameField());
            loginPanel.add(getPasswordLabel());
            loginPanel.add(getPasswordField());

            loginPanel.add(getLoginButton());
            loginPanel.add(getExitButton());

            getButtonGroup();
            loginPanel.add(getManagerType());
            loginPanel.add(getPatientType());
            loginPanel.add(getDoctorType());
            loginPanel.add(getNurseType());

            loginPanel.add(getUserIDLabel());
            loginPanel.add(getUserIDField());
        }
        return loginPanel;
    }

    public JLabel getUsernameLabel(){
        if(usernameLabel == null){
            usernameLabel = new JLabel("UserName");
            usernameLabel.setBounds(80, 30, 80, 30);
        }
        return usernameLabel;
    }

    public JTextField getUserNameField() {
        if (usernameField == null) {
            usernameField = new JTextField();
            usernameField.setBounds(new Rectangle(170, 30, 130, 30));
        }
        return usernameField;
    }

    public JLabel getPasswordLabel(){
        if(passwordField == null) {
            passwordLabel = new JLabel("PassWord");
            passwordLabel.setBounds(80, 70, 80, 30);
        }
        return passwordLabel;
    }

    public JPasswordField getPasswordField() {
        if (passwordField == null) {
            passwordField = new JPasswordField();
            passwordField.setBounds(new Rectangle(170, 70, 130, 30));
            // press return is also clicking login
            passwordField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent keyEvent) {
                    if (keyEvent.getKeyChar() == '\n')
                        loginButton.doClick();
                }
            });
        }
        return passwordField;
    }

    public JLabel getUserIDLabel(){
        if(userIDLabel == null){
            userIDLabel = new JLabel("User ID");
            userIDLabel.setBounds(80,110,80,30);
        }
        return userIDLabel;
    }

    public JTextField getUserIDField(){
        if(userIDField == null){
            userIDField = new JTextField();
            userIDField.setBounds(170,110,130,30);
        }
        return userIDField;
    }

    private JButton getLoginButton() {
        if (loginButton == null) {
            loginButton = new JButton();
            loginButton.setBounds(new Rectangle(90, 210, 60, 30));
            loginButton.setText("Login");
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        // set local system style
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        // get username
                        userName = usernameField.getText();
                        // get password
                        String passStr = new String(passwordField.getPassword());
                        // String usertype = LoginDialog.usertype;
                        String userID = String.valueOf(userIDField.getText());
                        // check if the username and password matches
                        if(!Dao.checkLogin(userName, passStr,usertype,userID)){
                            JOptionPane.showMessageDialog(
                                    // component
                                    LoginDialog.this,
                                    // message
                                    "At least one field is wrong, \n Unable to Login!",
                                    // title
                                    "Login Failed",
                                    // message type
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        switch(usertype){

                            case "Manager":
                                mainFrame.setVisible(true);
                                mainFrame.getLoginStateLabel().setText(userName);
                                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                break;
                            case "Doctor":
                                doctorFrame = new DoctorFrame(userName,usertype,userID);
                                doctorFrame.setVisible(true);
                                // doctorFrame.getLoginStateLabel().setText(userName);
                                doctorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                break;
                            case "Nurse":
                                nurseFrame = new NurseFrame(userName,usertype,userID);
                                nurseFrame.setVisible(true);
                                // nurseFrame.getLoginStateLabel().setText(userName);
                                nurseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                break;
                            case "Patient":
                                patientFrame = new PatientFrame(userName,usertype,userID);
                                patientFrame.setVisible(true);
                                // patientFrame.getLoginStateLabel().setText(userName);
                                patientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                                myProfileFrame = new MyProfileFrame();
//                                myProfileFrame.setLoginMessage(userName,usertype,userID);
//                                myProfileFrame.getPatientData();
                                break;
                        }
                        setVisible(false);

                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            });
        }
        return loginButton;
    }

    private JButton getExitButton() {
        if (exitButton == null) {
            exitButton = new JButton();
            exitButton.setBounds(new Rectangle(230, 210, 60, 30));
            exitButton.setText("Exit");
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return exitButton;
    }

    private JRadioButton getPatientType(){
        if(patientType == null){
            patientType = new JRadioButton();
            patientType.setBounds(25,150,80,30);
            patientType.setText("Patient");
            patientType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(patientType.isSelected()){
                        usertype = "Patient";
                    }
                }
            });
        }
        return patientType;
    }

    private JRadioButton getManagerType(){
        if(managerType == null){
            managerType = new JRadioButton();
            managerType.setBounds(105,150,85,30);
            managerType.setText("Manager");
            managerType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(managerType.isSelected()){
                        usertype = "Manager";
                    }
                }
            });
        }
        return managerType;
    }

    private JRadioButton getDoctorType(){
        if(doctorType == null){
            doctorType = new JRadioButton();
            doctorType.setBounds(200,150,85,30);
            doctorType.setText("Doctor");
            doctorType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(doctorType.isSelected()){
                        usertype = "Doctor";
                    }
                }
            });
        }
        return doctorType;
    }

    private JRadioButton getNurseType(){
        if(nurseType == null){
            nurseType = new JRadioButton();
            nurseType.setBounds(285,150,370,30);
            nurseType.setText("Nurse");
            nurseType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(nurseType.isSelected()){
                        usertype = "Nurse";
                    }
                }
            });
        }
        return nurseType;
    }

    private ButtonGroup getButtonGroup(){

        if(buttonGroup == null){
            buttonGroup = new ButtonGroup();
            buttonGroup.add(getPatientType());
            buttonGroup.add(getManagerType());
            buttonGroup.add(getDoctorType());
            buttonGroup.add(getNurseType());
        }
        return buttonGroup;
    }

    private String[] getLoginData(){
        String[] toReturn = {userName,userIDField.getText(),usertype};
        return toReturn;
    }
}

