package com.zzz.robot.view;

import javax.swing.*;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class MainPanel extends JFrame {

    private LeftPanel left;
    private RightPanel right;
    private TopPanel top;

    public MainPanel(){
        this.setTitle("小说阅读器");
        this.setLayout(null);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addPanel();
    }

    public void addPanel(){
        top = new TopPanel(0,0,300,100,this);
        left = new LeftPanel(0,100,300,500,this);
        right = new RightPanel(300,0,500,600,this);
        this.add(top);
        this.add(left);
        this.add(right);
    }

    public void showContent(String content){
        right.showContent(content);
    }


    public static void main(String[] args) {
        new MainPanel().setVisible(true);
    }
}
