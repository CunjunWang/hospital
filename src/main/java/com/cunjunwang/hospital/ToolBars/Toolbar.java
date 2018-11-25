package com.cunjunwang.hospital.ToolBars;


import com.cunjunwang.hospital.MenuBars.MenuBar;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CunjunWang on 16/10/18.
 */

public class Toolbar extends JToolBar{

    private MenuBar menuBar;

    private Toolbar(){}

    public Toolbar(MenuBar menuBar){
        super();
        this.menuBar = menuBar;
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(600, 24));
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        add(createToolButton(menuBar.getDoctorOverview()));
        add(createToolButton(menuBar.getNurseOverview()));
        add(createToolButton(menuBar.getPatientOverview()));
        add(createToolButton(menuBar.getMedicineOverview()));
        add(createToolButton(menuBar.getRoomOverview()));
        add(createToolButton(menuBar.getExitItem()));
    }

    private JButton createToolButton(JMenuItem item) {
        JButton button = new JButton();
        button.setText(item.getText());
        button.setToolTipText(item.getText());
        button.setIcon(item.getIcon());
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                item.doClick();
            }
        });
        return button;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
