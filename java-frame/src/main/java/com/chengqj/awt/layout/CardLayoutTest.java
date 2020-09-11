package com.chengqj.awt.layout;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @Author chengqj
 * @Date 2020/9/10 14:48
 * @Desc 卡片布局管理器
 * 类似将所有组件叠成卡片,只有最上边可见
 */
public class CardLayoutTest {
    Frame f = new Frame("CardLayoutTest测试");
    String[] name = {"1","2","3","4","5"};
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
                case "previous":
                    c.previous(pl);
                    break;
                case "next":
                    c.next(pl);
                    break;
                case "first":
                    c.first(pl);
                    break;
                case "last":
                    c.last(pl);
                    break;
                case "3":
                    c.show(pl,"3");
                    break;
            }
        };
        // 控制显示上一张的按钮
        Button previous = new Button("previous");
        previous.addActionListener(listener);
        p.add(previous);
        // 控制显示下一张的按钮
        Button next = new Button("next");
        next.addActionListener(listener);
        p.add(next);
        // 控制显示第一张的按钮
        Button first = new Button("first");
        first.addActionListener(listener);
        p.add(first);
        // 控制显示最后一张的按钮
        Button last = new Button("last");
        last.addActionListener(listener);
        p.add(last);
        // 控制显示第三张的按钮
        Button thrid = new Button("3");
        thrid.addActionListener(listener);
        p.add(thrid);
        f.add(pl);
        f.add(p,BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);


    }

    public static void main(String[] args) {
        new CardLayoutTest().init();

    }
}
