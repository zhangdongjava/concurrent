package com.zzz.robot.view;


import com.zzz.robot.util.ParseUtil;
import com.zzz.robot.util.impl.ZjgetUtil;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class LeftPanel extends BasePanel {

    private JList jList;
    private  JScrollPane jScrollPane;

    private java.util.List<Element> as;


    private  ZjgetUtil zjgetUtil = new ZjgetUtil();

    public LeftPanel(int x,int y,int w,int h,MainPanel mainPanel){
        super(x,y,w,h,mainPanel);

        zjgetUtil.LINE_LENGTH = 29;
        this.setBackground(Color.gray);
    }

    public void initList(String url){
        Vector<String> zjs =  getZj(url);
        if(jList!=null){
            jList.removeAll();
            jList.setListData(zjs);
        }else{
            jList = new JList(zjs);
            jList.addMouseListener(new ListClick());
            jList.setBackground(this.getBackground());
            jList.setBounds(0,0,300,450);
            jScrollPane =  new JScrollPane(jList);
            jScrollPane.setBounds(0,0,300,451);
        }
        this.add(jScrollPane);
        this.repaint();
    }

    public Vector<String> getZj(String url){
        as = ParseUtil.getZjList(url);
        Vector<String> zjs =new Vector<>();
        Collections.reverse(as);
        as.stream()
                .forEach((e)->zjs.add(e.text()));
        return zjs;
    }

    private class ListClick extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            getZj(as.get(jList.getSelectedIndex()));
        }
    }

    private void getZj(Element e){
        String content;
        String url = e.attr("href");
        try {
            content =  ParseUtil.getContent(url);
        } catch (Exception e1) {
            content = e.toString();
        }
        mainPanel.showContent(content);
    }


}












