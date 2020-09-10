package com.chengqj.awt.layout;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * @Author chengqj
 * @Date 2020/9/10 14:48
 * @Desc 卡片布局管理器
 * 类似将所有组件叠成卡片,只有最上边可见
 */
public class CardLayoutTest {
    Frame f = new Frame("CardLayoutTest测试");
    String[] name = {"第一张","第二张","第三张","第四张","第五张"};
    Panel pl = new Panel();
    
    public void init(){
        final CardLayout c = new CardLayout();
        pl.setLayout(c);
        for (int i = 0; i < name.length; i++) {
            Button button = new Button(name[i]);
            pl.add(name[i],button);
        }
        Panel p = new Panel();
        ActionListener listener = e ->{
            switch (e.getActionCommand()){
                case "上一张":
                    c.previous(pl);
                    break;
                case "下一张":
                    c.next(pl);
                    break;
                case "第一张":
                    c.last(pl);
                    break;
                case "第三张":
                    c.show(pl,"第三张");
                    break;
            }
        };
        // 控制显示上一张的按钮
        Button previous = new Button("上一张");
        previous.setFont(Font.getFont("SimSun"));
        previous.addActionListener(listener);
        previous.setLocale(Locale.CHINESE);
        p.add(previous);
        // 控制显示下一张的按钮
        Button next = new Button("下一张");
        next.addActionListener(listener);
        p.add(next);
        // 控制显示第一张的按钮
        Button first = new Button("下一张");
        first.addActionListener(listener);
        p.add(first);
        // 控制显示最后一张的按钮
        Button last = new Button("最后一张");
        last.addActionListener(listener);
        p.add(last);
        // 控制显示第三张的按钮
        Button thrid = new Button("第三张");
        thrid.addActionListener(listener);
        p.add(last);
        f.add(pl);
        f.add(p,BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);


    }

    public static void main(String[] args) {
        new CardLayoutTest().init();

    }
}
