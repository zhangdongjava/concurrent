package com.zzz.robot.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MyDialog extends Dialog {
    JLabel label1 = new JLabel("名称");
    JLabel label2 = new JLabel("地址");
    JTextField name = new JTextField();
    JTextField url = new JTextField();
    JButton ok = new JButton("确定");
    JButton cancel222 = new JButton("取消");
    private TopPanel parent;

    public MyDialog(TopPanel parent, MainPanel mainPanel) {
        super(mainPanel);
        this.parent = parent;
        this.setSize(400,300);
        label1.setBounds(50,30,100,50);
        name.setBounds(156,30,200,30);
        label2.setBounds(50,100,100,50);
        url.setBounds(156,100,200,30);
        ok.setBounds(100,200,80,50);
        cancel222.setBounds(200,200,80,50);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MyDialog.this.setVisible(false);
            }
        });
        ok.addActionListener((e)->addXs());
        this.setLocationRelativeTo(null);
        this.add(label1);
        this.add(name);
        this.add(label2);
        this.add(url);
        this.add(ok);
        this.add(cancel222);
        this.setVisible(true);
    }

    private void addXs() {
        parent.addXs(name.getText(),url.getText());
        cancel();

    }
    private void cancel() {
        this.setVisible(false);
    }

}