package com.cunjunwang.hospital.emp.init_version_2016;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by CunjunWang on 16/10/18.
 */
public class DesktopPanel extends JDesktopPane{

    private static final long serialVersionUID = 1L;
    private final Image backImage;

    public DesktopPanel() {
        super();
        URL url = DesktopPanel.class.getResource("/com/cunjunwang/hospital/emp/init_version_2016/Resources/back.jpg");
        backImage = new ImageIcon(url).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
        int width = getWidth();
        int height = this.getHeight();
        g.drawImage(backImage, 0, 0, width, height, this);
    }
}
