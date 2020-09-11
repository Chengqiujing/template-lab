package com.chengqj.awt.layout;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/11 9:34
 * @Desc 绝对定位
 * 即将布局管理器设置为空,用组件位置定位
 * ps: 会使GUI丧失跨平台性
 */
public class AbsoluteLayoutTest {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setLayout(null); // 布局管理器设置为空就是绝对定位

        Button button = new Button("button");
        button.setBounds(50, 50, 100, 50);

        Button button1 = new Button("button1");
        button1.setBounds(40, 40, 100, 50);

        frame.add(button);
        frame.add(button1);

        frame.setBounds(0, 0, 300, 200);
        frame.setVisible(true);
    }



}
