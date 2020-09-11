package com.chengqj.awt.layout;

import javax.swing.*;
import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/11 9:48
 * @Desc GridBagLayout功能复杂,但是使用不方便
 *
 *  BoxLayout只需指定竖直排列还是横向排列就好
 *  1. 可以为容器指定Layout
 *  2. 也可以通过Box组件来使用(Box通过Box静态方法获取)
 *
 *  Box没有间距构造器,间距通过Glue,Strut,RigidArea这些组件来填充
 *  Glue: 可拉伸间距
 *  Strut: 可指定宽度间距
 *  RigidArea: 不可拉伸间距
 *
 */
public class BoxLayoutTest {
    public static void main(String[] args) {
        BoxLayoutTest boxLayoutTest = new BoxLayoutTest();
//        boxLayoutTest.use1(); // 为组件指定布局管理器
//        boxLayoutTest.use2(); // 一般使用
        boxLayoutTest.use3(); // 指定间距

    }

    /**
     * 设置布局管理器使用
     */
    private void use1(){
        Frame frame = new Frame();

        BoxLayout boxLayout = new BoxLayout(frame, BoxLayout.Y_AXIS);// 竖直排列
        frame.setLayout(boxLayout);

        frame.add(new Button("button1"));
        frame.add(new Button("button2"));

        frame.pack();
        frame.setVisible(true);
    }


    /**
     * 通过Box对象使用
     */
    private void use2(){
        Frame frame = new Frame();

        Box verticalBox = Box.createVerticalBox(); // 竖直排列
        Box horizontalBox = Box.createHorizontalBox(); // 水平排列

        horizontalBox.add(new Button("Button"));
        horizontalBox.add(new Button("Button"));

        verticalBox.add(new Button("Button"));
        verticalBox.add(new Button("Button"));

        frame.add(horizontalBox,BorderLayout.NORTH);
        frame.add(verticalBox);

        frame.pack();
        frame.setVisible(true);
    }


    /**
     *  指定间距
     *
     *  5个方法
     *  创建可拉伸间距
     *  Box.createHorizontalGlue();
     *  Box.createVerticalGlue();
     *
     *  创建指定间距
     *  Box.createHorizontalStrut();
     *  Box.createVerticalStrut();
     *
     *  创建不可拉伸间距
     *  Box.createRigidArea()
     */
    private void use3(){
        Frame f = new Frame();

        // 定义水平摆放组件的Box对象
        Box horizontalBox = Box.createHorizontalBox();
        // 定义竖直摆放组件的Box对象
        Box verticalBox = Box.createVerticalBox();

        horizontalBox.add(new Button("aaa"));
        horizontalBox.add(Box.createHorizontalGlue()); // 水平可拉伸间距
        horizontalBox.add(new Button("bbb"));
        horizontalBox.add(Box.createHorizontalStrut(10)); // 水平不可拉伸间距
        horizontalBox.add(new Button("ccc"));

        verticalBox.add(new Button("111"));
        verticalBox.add(Box.createVerticalGlue()); // 竖直可拉伸间距
        verticalBox.add(new Button("222"));

        f.add(horizontalBox,BorderLayout.NORTH);
        f.add(verticalBox);

        f.pack();
        f.setVisible(true);


    }
}
