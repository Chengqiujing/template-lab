package com.chengqj.awt.layout;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/10 9:32
 * @Desc 流式布局
 */
public class FlowLayoutTest {
    public static void main(String[] args) {
        // FlowLayout布局管理器
        // 由左至右 碰触边界向下排
        Frame frame = new Frame();
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20,5));
        for (int i = 0; i < 10; i++) {
            frame.add(new Button("button"+i));
        }
        frame.setLocation(800, 300);
        frame.pack();// 调整到最佳大小
        frame.setVisible(true);
    }
}
