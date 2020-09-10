package com.chengqj.awt.layout;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/9 15:10
 * @Desc 网格布局管理器
 */
public class GridLayoutTest {
    public static void main(String[] args) {
        // GridLayout布局管理器
        Frame frame = new Frame("计算器");
        Panel panel = new Panel();
        panel.add(new TextField(30));
        frame.add(panel,BorderLayout.NORTH);

        Panel panel2 = new Panel();
        // 设置GridLayout布局管理器
        panel2.setLayout(new GridLayout(3,5,4,4)); // 几行几列,上下间距,左右间距
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
