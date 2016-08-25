package com.zzz.robot.view;


import com.zzz.robot.util.DocumentUtil;
import com.zzz.robot.util.ZjgetUtil;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class LeftPanel extends BasePanel {

    private JList jList;

    private java.util.List<Element> as;

    private  DocumentUtil util;

    private  ZjgetUtil zjgetUtil = new ZjgetUtil();

    public LeftPanel(int x,int y,int w,int h,MainPanel mainPanel){
        super(x,y,w,h,mainPanel);

        zjgetUtil.LINE_LENGTH = 29;
        this.setBackground(Color.gray);
        initList();
    }

    public void initList(){
        Vector<String> zjs =  getZj("http://www.bxwx.cc/60/60915/index.html");
        jList = new JList(zjs);
        jList.addMouseListener(new ListClick());
        jList.setBackground(this.getBackground());
        jList.setBounds(0,0,300,450);
        JScrollPane jScrollPane =  new JScrollPane(jList);
        jScrollPane.setBounds(0,0,300,451);
        this.add(jScrollPane);
    }

    public Vector<String> getZj(String url){
        if(util==null){
            util = new DocumentUtil(url);
        }else{
            util.setUrl(url);
        }
        Vector<String> zjs =new Vector<>();
        util.connect();
        Collections.reverse(util.getAs());
        as = util.getAs();
        util.getAs().stream()
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
        try {
            content =  zjgetUtil.get(e.attr("href"));
        } catch (Exception e1) {
            content = e.toString();
        }
        mainPanel.showContent(content);
    }


}












