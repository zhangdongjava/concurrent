package com.zzz.robot.view;

import com.zzz.robot.util.ParseUtil;
import com.zzz.robot.util.impl.DocumentUtil;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class TopPanel extends BasePanel {


    private  JComboBox comboBox;
    private  Set<Object> set;

    private String currText;

    private  JButton btn;
    private  JButton btn2;
    private  JButton btn3;

    private  int xsIndex = 0;

    public TopPanel(int x, int y, int w, int h, MainPanel mainPanel) {
        super(x, y, w, h, mainPanel);
        comboBox=new JComboBox();
        addXs();
        this.add(comboBox);
        btn = new JButton("保存本地");
        btn2 = new JButton("添加");
        btn3 = new JButton("删除");
        btn.addActionListener((e)->saveToFile());
        btn.setBounds(100,30,100,30);
        btn3.setBounds(100,65,100,30);
        btn2.setBounds(210,30,80,30);
        btn2.addActionListener((e)->new MyDialog(this,mainPanel));
        btn3.addActionListener((e)->removeItem());
        this.add(btn);
        this.add(btn2);
        this.add(btn3);
    }

    private void removeItem() {
        mainPanel.properties.keySet().remove(currText);
        mainPanel.saveXs();
        reLoadXs();
    }

    public void addXs(){
        set = mainPanel.properties.keySet();
        for (Object s : set) {
            if(currText==null)currText=s.toString();
            comboBox.addItem(s);
        }
        comboBox.setBounds(10,30,80,30);
        comboBox.addItemListener((e)->{
            String text = e.getItem().toString();
            if(!text.equals(currText)){
                try {
                    currText = text;
                    xsIndex = comboBox.getSelectedIndex();
                    mainPanel.loadZj(text);
                }catch (Exception ex){
                    mainPanel.appendContent(ex.toString());
                }

            }
        });
       try {
           mainPanel.loadZj(currText);
       }catch (Exception e){}
        mainPanel.repaint();
    }
    public void reLoadXs(){
        comboBox.removeAllItems();
        addXs();
    }




    private void saveToFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(mainPanel);
        File file = chooser.getSelectedFile();
        if(file != null){
            new Thread(()->{
                try {
                    FileWriter fileWriter = new FileWriter(file,true);
                    List<Element> as = new LinkedList<>(mainPanel.getLeft().getAs());
                    Collections.reverse(as);
                    as.stream().forEach((e)-> writeFile(fileWriter,e,0));
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    mainPanel.showContent(e.toString());
                }
            }).start();
        }

    }

    private void writeFile(FileWriter fileWriter,Element e,int count){
        try {
            if(count>0){
                mainPanel.insertContent("\n");
                mainPanel.insertContent(e.text()+"尝试"+count+"次写入！");
            }
            if(count <5){
                String content = ParseUtil.getContent(e.attr("href"));
                fileWriter.write("\n");
                fileWriter.write(e.text());
                fileWriter.write("\n");
                fileWriter.write(content);
                mainPanel.insertContent("\n");
                mainPanel.insertContent(e.text()+"写入成功!");
            }else{
                mainPanel.insertContent("\n");
                mainPanel.insertContent(e.text()+"尝试"+count+"次失败！");
            }
        } catch (Exception e1) {
            writeFile(fileWriter,e,count+1);
        }
    }

    public void addXs(String name,String val){
        mainPanel.addXs(name,val);
        reLoadXs();
    }
}
