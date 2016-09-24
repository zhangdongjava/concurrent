package com.zzz.robot.view;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
    public static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("xs.data"));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void appendContent(String content){
        right.appendContent(content);
    }
    public void insertContent(String content){
        right.insertContent(content);
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new MainPanel().setVisible(true);
    }

    public void loadZj(String currText) {
        left.initList(properties.get(currText).toString());
    }

    public void addXs(String name,String val){
        properties.put(name,val);
        saveXs();
    }

    public void saveXs(){

        try {
            FileOutputStream out = new FileOutputStream("xs.data");
            properties.store(out,"");
            out.close();
        } catch (Exception e) {
            showContent("保存失败!"+e.toString());
        }
    }


    public LeftPanel getLeft() {
        return left;
    }

    public RightPanel getRight() {
        return right;
    }

    public TopPanel getTop() {
        return top;
    }
}
