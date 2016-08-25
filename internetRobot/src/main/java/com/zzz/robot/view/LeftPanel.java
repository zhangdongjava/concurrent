package com.zzz.robot.view;


import com.zzz.robot.util.ParseUtil;
import com.zzz.robot.util.ZjgetUtil;
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

    private java.util.List<Element> as;


    private  ZjgetUtil zjgetUtil = new ZjgetUtil();

    public LeftPanel(int x,int y,int w,int h,MainPanel mainPanel){
        super(x,y,w,h,mainPanel);

        zjgetUtil.LINE_LENGTH = 29;
        this.setBackground(Color.gray);
        initList();
    }

    public void initList(){
        Vector<String> zjs =  getZj("http://www.biquge66.com/10_10292");
        jList = new JList(zjs);
        jList.addMouseListener(new ListClick());
        jList.setBackground(this.getBackground());
        jList.setBounds(0,0,300,450);
        JScrollPane jScrollPane =  new JScrollPane(jList);
        jScrollPane.setBounds(0,0,300,451);
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











