package com.zzz.robot.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class RightPanel extends BasePanel {

    private JTextArea jTextArea;
    private JScrollPane jScrollPane;

    private JScrollBar sBar;
    public RightPanel(int x,int y,int w,int h,MainPanel mainPanel){
        super(x,y,w,h,mainPanel);
        this.setBackground(Color.darkGray);
        initTextArea();
    }

    public void initTextArea(){
        jTextArea = new JTextArea();
        jTextArea.setBackground(Color.gray);
        jTextArea.setFont(new Font("宋体",Font.TRUETYPE_FONT,16));
        jTextArea.setBounds(0,0,488,580);
        jTextArea.setLineWrap(true);        //激活自动换行功能
        jTextArea.setWrapStyleWord(true);            // 激活断行不断字功能
        jScrollPane =  new JScrollPane(jTextArea);
        jScrollPane.setBounds(0,0,488,580);
        sBar = jScrollPane.getVerticalScrollBar();
        this.add(jScrollPane);
    }

    public void showContent(String content) {
        jTextArea.setText(content);
        jTextArea.setCaretPosition(0);
    }

    public void appendContent(String content) {
        jTextArea.append(content);
        jTextArea.setCaretPosition(0);
    }
    public void insertContent(String content) {
        jTextArea.insert(content,0);
        jTextArea.setCaretPosition(0);
    }
}
