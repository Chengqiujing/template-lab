package com.chengqj.awt.layout;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/9 15:10
 * @Desc
 */
public class LayoutTest {
    public static void main(String[] args) {


        // FlowLayout布局管理器
//        Frame frame = new Frame();
//        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20,5));
//        for (int i = 0; i < 10; i++) {
//            frame.add(new Button("button"+i));
//        }
//        frame.setLocation(800, 300);
//        frame.pack();// 调整到最佳大小
//        frame.setVisible(true);


        // BorderLayout布局管理器 (默认)
//        Frame frame = new Frame();
//        frame.add(new Button("1"), BorderLayout.NORTH);
//        frame.add(new Button("2"), BorderLayout.SOUTH);
//        frame.add(new Button("3"), BorderLayout.EAST);
//        frame.add(new Button("4"), BorderLayout.WEST);
//        frame.add(new Button("5"), BorderLayout.CENTER);
//        frame.setSize(600,400);
//        frame.setLocation(800, 300);
//        frame.setVisible(true);


        // GridLayout布局管理器
        Frame frame = new Frame("计算器");
        Panel panel = new Panel();
        panel.add(new TextField(30));
        frame.add(panel,BorderLayout.NORTH);

        Panel panel2 = new Panel();
        // 设置GridLayout布局管理器
        panel2.setLayout(new GridLayout(3,5,4,4));
        String[] name = {"0","1","2","3","4","5","6","7","8","9","+","-","*","/","."};
        for (int i = 0; i < name.length; i++) {
            panel2.add(new Button(name[i]));
        }
        frame.add(panel2);
        //frame.setBounds(600, 400, 600, 400);
        frame.pack();
        frame.setVisible(true);

    }
}
