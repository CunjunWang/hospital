package com.cunjunwang.hospital.emp.init_version_2016.Login;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 16/10/18.
 */
public class LoginPanel extends JPanel{
    public int width;
    public int height;
    private Image img;

    public LoginPanel(){
        super();
    }

    public void paintComponents(Graphics g){
        super.paintComponents(g);
        g.drawImage(img,0,0,this);
    }
}
