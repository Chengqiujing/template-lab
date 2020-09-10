package com.chengqj.awt.layout;

import java.awt.*;

/**
 * @Author chengqj
 * @Date 2020/9/10 9:47
 * @Desc 可调整网格合并的布局管理器
 *  强大但是复杂
 */
public class GridBagLayoutTest {
    private Frame frame = new Frame("测试");
    private GridBagLayout gb = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private Button[] buttons = new Button[10];

    public void init(){
        frame.setLayout(gb);
        for (int i = 0; i < 10; i++) {
            buttons[i] = new Button("button " + i);
        }

        // 所有组件都可以在横向和纵向上扩大
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1; // 横向扩大的比列
        addButton(buttons[0]);
        addButton(buttons[1]);
        gbc.weightx = 3; // 横向扩大的比例为3
        addButton(buttons[2]);

        // 该constraints控制的组件将会成为横向最后一个组件
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // gridwidth 控制横跨多少个网格
        addButton(buttons[3]);

        // 该constraints控制的组件将会在横向上不会扩大
        gbc.weightx = 0; // 横向扩大比列为0 其实不起作用
        addButton(buttons[4]);

        // 该constraints控制的组件将会横跨两个网格 button5也会扩大
        gbc.gridwidth = 2;
        addButton(buttons[5]);

        // 该constraints控制的组件将会 横向1 纵向2
        gbc.gridwidth  = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 横向最后一个组件
        addButton(buttons[6]);

        // 该constraints控制的组件将会 横1 纵2 纵权重 1
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weighty = 1;
        addButton(buttons[7]);

        // 该constraints控制的组件将会 纵权重 0 横排最后一个 纵1
        gbc.weighty = 0;// 纵向不扩大
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        addButton(buttons[8]);
        addButton(buttons[9]); // 其实9纵向上还是会扩大
        frame.pack();
        frame.setVisible(true);
    }

    private void addButton(Button button){
        gb.setConstraints(button, gbc);
        frame.add(button);
    }


    public static void main(String[] args) {
        new GridBagLayoutTest().init();
    }

}
