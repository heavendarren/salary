package com.yunxiang.salary;

/**
 * Created by wangqingxiang on 2017/4/19.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class ConsolePane {
    public static void main(String[] args) {
  /*初始化一个frame*/
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(1, 2));

  /*加入一个textpane*/
        JTextPane textPane1 = new JTextPane();
        textPane1.setBorder(new LineBorder(Color.BLUE));
        frame.getContentPane().add(textPane1);
        frame.setVisible(true);

  /*读取控制台输入*/
        Scanner s = new Scanner(System.in);
        String str = "";
        String line;
        while ((line = s.nextLine()) != "") {
            str += line + "\n";
            textPane1.setText(str);
        }
    }
}