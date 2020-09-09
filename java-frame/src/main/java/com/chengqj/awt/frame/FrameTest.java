package com.chengqj.awt.frame;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/9 13:50
 * @Desc
 */
public class FrameTest {
    public static void main(String[] args) {
        Frame frame = new Frame("测试窗口");
        // Panel 组件
//        Panel panel = new Panel();
//        panel.add(new TextField(20));
//        panel.add(new Button("单ji"));
//        frame.add(panel);

        // 滚动条
        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        scrollPane.add(new TextField(20));
        scrollPane.add(new Button("单机"));
        frame.add(scrollPane);


        frame.setLocation(800, 300);
        frame.pack(); // 将窗口设置为最佳大小
//        frame.setBounds(100, 100, 450, 400);
        frame.setVisible(true);


    }
}
