package com.zzz.robot.view;

import javax.swing.*;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class BasePanel extends JPanel {


    protected MainPanel mainPanel;

    public BasePanel(int x,int y,int w,int h,MainPanel mainPanel){
        this.setLayout(null);
        this.mainPanel = mainPanel;
        this.setBounds(x,y,w,h);
    }
}
