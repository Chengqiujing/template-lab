package com.chengqj.awt.component;

import javax.swing.*;
import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/11 11:18
 * @Desc 简单的组件
 */
public class ComponentTest {
    Frame f = new Frame("测试");
    // button
    Button ok = new Button("确认");

    // CheckBox
    CheckboxGroup cbg = new CheckboxGroup();
    Checkbox male = new Checkbox("男",cbg,true); // true 选中装态
    Checkbox female = new Checkbox("女",cbg,false);

    // 复选框
    Checkbox married = new Checkbox("是否已婚?", false);

    // 下拉选
    Choice colorChooser = new Choice();
    // 列表选择框
    List colorList = new List(6,true);
    // 文本域 5行20列
    TextArea ta = new TextArea(5,20);
    // 单行文本域 50列
    TextField name = new TextField(50);

    public void init(){
        colorChooser.add("红色");
        colorChooser.add("绿色");
        colorChooser.add("蓝色");
        colorList.add("红色");
        colorList.add("绿色");
        colorList.add("蓝色");

        // 创建一个装载了文本框,按钮的Panel
        Panel bottom = new Panel();
        bottom.add(name);
        bottom.add(ok);
        f.add(bottom,BorderLayout.SOUTH);

        // 创建一个装载了下拉选择框,三个Checkbox的Panel
        Panel checkPanel = new Panel();
        checkPanel.add(colorChooser);
        checkPanel.add(male);
        checkPanel.add(female);
        checkPanel.add(married);

        // 创建一个垂直排列组件的Box,盛装多行文本域,Panel
        Box topLeft = Box.createVerticalBox();
        topLeft.add(ta);
        topLeft.add(checkPanel);

        // 创建一个水平排列组件的Box,盛装topLeft,colorList
        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);
        // 将top Box容器添加到窗口的中间
        f.add(top);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new ComponentTest().init();
    }

}
