package com.yunxiang.salary;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

public class YFileChooser implements ActionListener {

    JFrame frame = new JFrame("自动工资发送");
    JTabbedPane tabPane = new JTabbedPane();//选项卡布局
    Container con = new Container();//布局1
    Container con1 = new Container();//布局2
    JLabel label1 = new JLabel("选择工资文件");
    JLabel label2 = new JLabel("选择邮箱文件");

    JTextField text1 = new JTextField();
    JTextField text2 = new JTextField();
    JButton button1 = new JButton("...");
    JButton button2 = new JButton("...");
    JButton button3 = new JButton("确定");
    JButton button4 = new JButton("取消");
    JTextPane textPane = new JTextPane();



    JLabel label3 = new JLabel("发送人");
    JLabel label4 = new JLabel("发送人邮箱");
    JLabel label5 = new JLabel("密码");

    JTextField text3 = new JTextField();
    JTextField text4 = new JTextField();
    JTextField text5 = new JTextField();


    JFileChooser jfc = new JFileChooser();//文件选择器

    public YFileChooser() {
        jfc.setCurrentDirectory(new File("c:\\"));//文件选择器的初始目录定为d盘
        //下面两行是取得屏幕的高度和宽度
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int) (lx / 2) - 250, (int) (ly / 2) - 270));//设定窗口出现位置
        frame.setSize(500, 550);//设定窗口大小
        frame.setContentPane(tabPane);//设置布局
        //下面设定标签等的出现位置和高宽
        label1.setBounds(10,10,100,20);
        label2.setBounds(10, 40, 100, 20);
        text1.setBounds(110,10,200,20);
        text2.setBounds(110, 40, 200, 20);
        button1.setBounds(320,10,50,20);
        button2.setBounds(320, 40, 50, 20);
        button3.setBounds(400, 10, 70, 50);
        textPane.setBounds(10,70,400,380);
        button1.addActionListener(this);//添加事件处理
        button2.addActionListener(this);//添加事件处理
        button3.addActionListener(this);//添加事件处理
        button4.addActionListener(this);//添加事件处理


        textPane.setBorder(new LineBorder(Color.BLUE));
        con.add(label1);
        con.add(label2);
        con.add(text1);
        con.add(text2);
        con.add(button1);
        con.add(button2);
        con.add(button3);
        con.add(button4);
        con.add(textPane);
        con.add(jfc);




        label3.setBounds(10,10,100,20);
        label4.setBounds(10, 40, 100, 20);
        label5.setBounds(10, 70, 100, 20);
        text3.setText("815966365@qq.com");
        text4.setText("孙冰冰");
        text3.setBounds(110,10,200,20);
        text4.setBounds(110, 40, 200, 20);
        text5.setBounds(110, 70, 200, 20);
        con1.add(label3);
        con1.add(label4);
        con1.add(label5);
        con1.add(text3);
        con1.add(text4);
        con1.add(text5);



        tabPane.add("目录/文件选择", con);//添加布局1
        tabPane.add("邮箱文件",con1);//添加布局2
        frame.setVisible(true);//窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使能关闭窗口，结束程序
    }

    public void actionPerformed(ActionEvent e) {//事件处理
        if (e.getSource().equals(button1)) {//判断触发方法的按钮是哪个
            jfc.setFileSelectionMode(0);//设定只能选择到文件夹
            int state = jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;//撤销则返回
            } else {
                File f = jfc.getSelectedFile();//f为选择到的目录
                text1.setText(f.getAbsolutePath());
            }
        }
        if (e.getSource().equals(button2)) {
            jfc.setFileSelectionMode(0);//设定只能选择到文件
            int state = jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;//撤销则返回
            } else {
                File f = jfc.getSelectedFile();//f为选择到的文件
                text2.setText(f.getAbsolutePath());
            }
        }
        if (e.getSource().equals(button3)) {
            String fromEmail=text3.getText();
            if(fromEmail==null||fromEmail.length()==0){
                JOptionPane.showMessageDialog(null,"请输入邮箱");
                return;
            }
            String fromName=text4.getText();
            if(fromName==null||fromName.length()==0){
                JOptionPane.showMessageDialog(null,"请输入发送人");
                return;
            }
            String password=text5.getText();
            if(password==null||password.length()==0){
                JOptionPane.showMessageDialog(null,"请输入密码");
                return;
            }
            excute();
        }
        if (e.getSource().equals(button4)) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }



    public boolean excute() {

        textPane.setText(textPane.getText()+"/n"+"开始读取excel文件");
        //读取excel
        Map<String, List<String>> salaryMap = ReadExcelUtil.readSalary(text1.getText());
        if (salaryMap == null) {
            textPane.setText(textPane.getText()+"/n"+"读取excel失败");
            return false;
        }
        textPane.setText(textPane.getText()+"\n"+"成功读取工资表，共读取"+salaryMap.keySet().size()+"条数据");

        textPane.setText(textPane.getText()+"\n"+"开始读取email文件");
        Map<String, String>  emailMap = ReadExcelUtil.readEmail(text2.getText());
        textPane.setText(textPane.getText()+"\n"+"成功读取email文件，共读取"+emailMap.keySet().size()+"条数据");
        textPane.setText(textPane.getText()+"\n"+"开始发送邮件");

        for (String userName : salaryMap.keySet()) {
            List<String> salary = salaryMap.get(userName);
            String email = emailMap.get(userName);
            if (email == null) {
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", userName);
            map.put("salary", salary);
            map.put("date", new Date());
            map.put("fromEmail",text3.getText());
            map.put("fromName",text4.getText());
            map.put("passWord",text5.getText());
            String templatePath = "salary.ftl";
           boolean result=SendMailUtil.sendFtlMail( email, "新兴投资有限公司工资明细", templatePath, map);
           if(result){
               textPane.setText(textPane.getText()+"\n"+"发送邮件给   "+email+"     成功！");
           }else{
               textPane.setText(textPane.getText()+"\n"+"发送邮件给   "+email+"     失败！");
           }
        }

        return true;
    }

    public static void main(String[] args) {
        YFileChooser y=new YFileChooser();
        y.excute();
    }

}