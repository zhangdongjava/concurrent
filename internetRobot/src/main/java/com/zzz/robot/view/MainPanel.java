package com.zzz.robot.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class MainPanel extends JFrame {

    private LeftPanel left;
    private RightPanel right;
    private TopPanel top;
    public static Map<String,String> map = new HashMap<>();
    public static Map<String,String> zjParse = new HashMap<>();
    public static Map<String,String> contentParse = new HashMap<>();
    static {
        map.put("大主宰","http://www.bxwx.cc/60/60915/index.html");
        map.put("逆鳞","http://www.biquge66.com/10_10292");
    }

    public MainPanel(){
        this.setTitle("小说阅读器");
        this.setLayout(null);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(Color.GRAY);
        //this.setVisible(true);
        addPanel();
    }

    public void addPanel(){
        right = new RightPanel(300,0,500,600,this);
        left = new LeftPanel(0,100,300,500,this);
        top = new TopPanel(0,0,300,100,this);
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

    public void loadZj(String currText) {
        left.initList(map.get(currText));
    }
}
