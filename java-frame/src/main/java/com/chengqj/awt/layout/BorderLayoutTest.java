package com.chengqj.awt.layout;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/10 9:34
 * @Desc 边界布局管理器
 */
public class BorderLayoutTest {
    public static void main(String[] args) {
        // BorderLayout布局管理器 (默认)
        // Panel ScrollPane Frame 默认使用此布局
        Frame frame = new Frame();

        /**
         * 五方定位(上北 下南 左西 右东) 默认CENTER
         */
        frame.add(new Button("1"), BorderLayout.NORTH); // 北
        frame.add(new Button("2"), BorderLayout.SOUTH); // 南
        frame.add(new Button("3"), BorderLayout.EAST); // 东
        frame.add(new Button("4"), BorderLayout.WEST); // 西
        frame.add(new Button("5"), BorderLayout.CENTER); // 中
        frame.setSize(600,400);
        frame.setLocation(800, 300);
        frame.setVisible(true);
    }
}
