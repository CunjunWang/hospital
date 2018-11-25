package com.cunjunwang.hospital.init_version_2016.MainFrames;


import com.cunjunwang.hospital.init_version_2016.*;
import com.cunjunwang.hospital.init_version_2016.Login.LoginDialog;
import com.cunjunwang.hospital.init_version_2016.MenuBars.MenuBar;
import com.cunjunwang.hospital.init_version_2016.ToolBars.Toolbar;
import com.cunjunwang.hospital.init_version_2016.DesktopPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

/**
 * Created by CunjunWang on 16/10/18.
 */
public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel frameContentPane = null;
    private com.cunjunwang.hospital.init_version_2016.MenuBars.MenuBar frameMenuBar = null;
    private Toolbar toolbar = null;
    private DesktopPanel desktopPanel = null;
    private static JLabel loginStateLabel = null;
    private JPanel statePanel;
    private JLabel stateLabel = null;


    public static void main(String[] args) {
        JFrame login = new LoginDialog();
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        login.setVisible(true);
    }

    public MainFrame() {
        super();
        initialize();
    }

    private void initialize() {
        int mainFrameWidth = 800;
        int mainFrameHeight = 600;
        this.setSize(mainFrameWidth, mainFrameHeight);
        Dimension size = getToolkit().getScreenSize();
        setLocation((size.width - mainFrameWidth) / 2, (size.height - mainFrameHeight) / 2);
        this.setTitle("CPSC304_HospitalDB_ManagerFrame");
        this.setJMenuBar(getFrameMenuBar());
        this.setContentPane(getFrameContentPane());
    }

    private Toolbar getToolBar() {
        if (toolbar == null) {
            toolbar = new Toolbar(getFrameMenuBar());
            toolbar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        return toolbar;
    }

    private com.cunjunwang.hospital.init_version_2016.MenuBars.MenuBar getFrameMenuBar() {
        if (frameMenuBar == null) {
            frameMenuBar = new MenuBar(getDesktopPanel(), getStateLabel());
        }
        return frameMenuBar;
    }

    private DesktopPanel getDesktopPanel() {
        if (desktopPanel == null) {
            desktopPanel = new DesktopPanel();
        }
        return desktopPanel;
    }

    private JPanel getStatePanel() {
        if (statePanel == null) {
            statePanel = new JPanel();

            JLabel nowDateLabel;
            JLabel nameLabel;

            nowDateLabel = new JLabel();
            Date now = new Date();
            nowDateLabel.setText(String.format("%tF", now));

            nameLabel = new JLabel("HospitalDB   TeamMember: Kunpeng, Berk, Cunjun");

            statePanel.add(getStateLabel());
            statePanel.add(nameLabel);
            statePanel.add(nowDateLabel);
        }
        return statePanel;
    }

    public static JLabel getLoginStateLabel() {
        if (loginStateLabel == null) {
            loginStateLabel = new JLabel();
        }
        return loginStateLabel;
    }

    private JLabel getStateLabel() {
        if (stateLabel == null) {
            stateLabel = new JLabel();
            stateLabel.setText("No window selected");
        }
        return stateLabel;
    }

    private JPanel getFrameContentPane() {
        if (frameContentPane == null) {
            frameContentPane = new JPanel();
            frameContentPane.setLayout(new BorderLayout());
            frameContentPane.add(getStatePanel(), SOUTH);
            frameContentPane.add(getToolBar(), NORTH);
            frameContentPane.add(getDesktopPanel(), CENTER);
        }
        return frameContentPane;
    }

}
