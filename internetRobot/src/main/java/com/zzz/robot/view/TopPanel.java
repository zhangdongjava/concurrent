package com.zzz.robot.view;

import javax.swing.*;
import java.util.Set;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class TopPanel extends BasePanel {


    private  JComboBox comboBox;
    private  Set<String> set;

    private String currText;

    public TopPanel(int x, int y, int w, int h, MainPanel mainPanel) {
        super(x, y, w, h, mainPanel);
        addXs();

    }

    public void addXs(){
        comboBox=new JComboBox();
        set = mainPanel.map.keySet();
        for (String s : set) {
            comboBox.addItem(s);
        }
        comboBox.setBounds(30,30,80,30);
        comboBox.addItemListener((e)->{
            String text = e.getItem().toString();
            if(!text.equals(currText)){
                mainPanel.loadZj(text);
                currText = text;
            }
        });
        this.add(comboBox);
        mainPanel.repaint();
    }

}
