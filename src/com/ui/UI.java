package com.ui;

import com.dao.Dao;
import com.enetiy.Student;
import com.util.UiUtil;
import com.util.YanZheng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UI {
    public static final UI ui = new UI();

    /**
     * 用户登录界面
     */
    public void dengLuMian() {
        JFrame jFrame = new JFrame();
        GridLayout gridLayout = new GridLayout(3, 3);//设置网格几行几列
        jFrame.setLayout(gridLayout);
        jFrame.setVisible(true);//显示界面


        jFrame.setDefaultCloseOperation(3);//关闭窗口程序即结束

        //登录界面的第一行
        JPanel jPanel1 = UiUtil.setBiaoQian("用户名");
        JTextField text = new JTextField(8);
        jPanel1.add(text);
        jFrame.add(jPanel1);
        JLabel j3 = new JLabel();
        jFrame.add(j3);

        //登录界面的第二行
        JPanel jPanel2 = UiUtil.setBiaoQian("密码   ");
        JPasswordField ps = new JPasswordField(8);
        jPanel2.add(ps);
        jFrame.add(jPanel2);
        JLabel j4 = new JLabel();
        jFrame.add(j4);



        //添加单击事件
        JButton bt2 = new JButton("注册");
        bt2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.zhuCeMian();
            }
        });
        //添加单击事件
        JButton bt1 = new JButton("登录");
        bt1.addActionListener(new AbstractAction() {
            /**
             * 用户登录方法判断用户名和密码是否匹配，若成功则登录成功
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok = Dao.login(text.getText(), ps.getText());
                if (ok) {
                    //如果登录成功调用查询界面
                    ui.selectMian();
                } else {
                    //登录失败弹出提示窗口
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "登录失败，用户名和密码不匹配",
                            "登录失败",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

        });

        //登录界面的第三行
        JPanel jPanel3 = new JPanel();
        jPanel3.add(bt1);
        jPanel3.add(bt2);
        jFrame.add(jPanel3);

        //登录界面的第四行
        JLabel j5 = new JLabel("是否已有账号，若无则先注册");
        jFrame.add(j5);


        //窗体显示位置设置
        jFrame.setBounds(800, 300, 900, 720);
        //窗体大小不可变
        jFrame.setResizable(false);
        //窗体自适应
        jFrame.pack();

    }

    /**
     * 用户注册界面
     * 有用户名，密码，，确认密码，邮箱等
     * 用户名必须处于6-14位之间
     * 密码和确认密码需要保证一致
     * 同一个用户名不能注册两次
     * 失去焦点则判断用户输入是否合法，如不合法再次获得焦点则清空
     */
    public void zhuCeMian() {
        JFrame jFrame = new JFrame("注册");
        GridLayout gridLayout = new GridLayout(5, 2);//网格布局设置五列两行
        jFrame.setLayout(gridLayout);//设置界面使用网格布局


        JPanel jPanel1 = UiUtil.setBiaoQian("用户名   ");
        JTextField text = new JTextField(8);//用户名文本框长度为8
        JLabel user = new JLabel();//此标签用来显示用户名状态信息
        jPanel1.add(text);
        jFrame.add(jPanel1);
        jFrame.add(user);




        JPanel jPanel2 = UiUtil.setBiaoQian("密   码    ");
        JLabel pass1 = new JLabel();//此标签用来显示密码状态信息
        JPasswordField ps1 = new JPasswordField(8);//密码框长度为8
        jPanel2.add(ps1);
        jFrame.add(jPanel2);
        jFrame.add(pass1);



        JPanel jPanel3 = UiUtil.setBiaoQian("确认密码");
        JPasswordField ps2 = new JPasswordField(8);//确认密码框长度为8
        JLabel pass2 = new JLabel();//此标签用来显示确认密码状态信息
        jPanel3.add(ps2);
        jFrame.add(jPanel3);
        jFrame.add(pass2);



        JPanel jPanel4 = UiUtil.setBiaoQian("邮   箱    ");
        JTextField email = new JTextField(8);//邮箱文本框长度为8
        JLabel Email = new JLabel();//此标签用来显示邮箱状态信息
        jPanel4.add(email);
        jFrame.add(jPanel4);
        jFrame.add(Email);






        JButton button = new JButton("注册");
        JPanel jPanel5 = new JPanel();
        jPanel5.add(button);
        jFrame.add(jPanel5);




        //用户名文本框添加焦点事件
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                boolean ok = YanZheng.yanUser(text.getText());
                if (!ok) {
                    text.setText("");
                    user.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                boolean ok = YanZheng.yanUser(text.getText());
                if (!ok) {
                    user.setText("用户名不合法");
                }
            }
        });




        //确认密码框添加获得和失去焦点事件
        ps2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String password1 = ps1.getText();
                String password2 = ps2.getText();
                boolean ok = YanZheng.yanPassword(password1, password2);
                if (!ok) {
                    pass2.setText("");
                    ps2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password1 = ps1.getText();
                String password2 = ps2.getText();
                boolean ok = YanZheng.yanPassword(password1, password2);
                if (!ok) {
                    pass2.setText("两次密码输入不一致");
                }
            }
        });



        //邮箱文本框添加获得和失去焦点事件
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //判断邮箱是否匹配成功
                boolean ok = YanZheng.yanEmail(email.getText());
                if (!ok) {
//                    获取焦点如果匹配失败清空输入框和提示信息
                    Email.setText("");
                    email.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                boolean ok = YanZheng.yanEmail(email.getText());
                //如果匹配不成功弹出提示信息
                if (!ok) {
                    Email.setText("邮箱格式不合法");
                }
            }
        });


        //注册按钮添加单击事件
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = text.getText();
                String password1 = ps1.getText();
                String password2 = ps2.getText();
                String email1 = email.getText();
                boolean ok = Dao.zhuCe(username, password1, password2, email1);
                if (ok) {
                    //注册成功弹出提示窗口
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "注册成功",
                            "注册成功",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    //注册失败弹出提示窗口
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "注册失败，可能是该用户名太受欢迎，已经被其他小伙伴使用了哦",
                            "注册失败",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        //密码框添加焦点事件
        ps1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //这里的逻辑同上
                String password=ps1.getText();
                boolean ok=YanZheng.yanMiMa(password);
                if (!ok){
                    pass1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password=ps1.getText();
                boolean ok=YanZheng.yanMiMa(password);
                if (!ok){
                    pass1.setText("密码不能为空");
                }
            }
        });


        jFrame.setVisible(true);//设置窗体可见
        jFrame.setDefaultCloseOperation(2);
        jFrame.setBounds(900, 300, 900, 720);
        jFrame.setResizable(false);
        jFrame.pack();
    }
    /**
     * 查询界面，界面设计，第一个网格里面是一个面板，面板里面存放的是一个标签和一个文本框
     * 第二个网格里面存放的也是一个面板，里面什么也没有，为了美观
     */
    public void selectMian(){
        JFrame jFrame=new JFrame("学生信息查询");
        GridLayout gridLayout = new GridLayout(8, 2);//网格布局设置五列两行
        jFrame.setLayout(gridLayout);//使用网格布局
        JPanel jPanel1= UiUtil.setBiaoQian("学   号");
        JTextField idText=new JTextField(8);
        jPanel1.add(idText);


        JPanel jPanel2=UiUtil.setBiaoQian("姓   名");
        JTextField nameText=new JTextField(8);
        jPanel2.add(nameText);

        JPanel jPanel3=UiUtil.setBiaoQian("性   别");
        JTextField sexText=new JTextField(8);
        jPanel3.add(sexText);

        JPanel jPanel4=UiUtil.setBiaoQian("民   族");
        JTextField minZuText=new JTextField(8);
        jPanel4.add(minZuText);

        JPanel jPanel5=UiUtil.setBiaoQian("所在系");
        JTextField suoZaiXiText=new JTextField(8);
        jPanel5.add(suoZaiXiText);

        JPanel jPanel6=UiUtil.setBiaoQian("专业名");
        JTextField zhuanYeText=new JTextField(8);
        jPanel6.add(zhuanYeText);

        JPanel jPanel9=UiUtil.setBiaoQian("班  级");
        JTextField banJiText=new JTextField(8);
        jPanel9.add(banJiText);

        JButton bt1=new JButton("查询此学生");
        //为按钮添加点击事件
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dao dao=new Dao();
                Student student=dao.selectStudent(Integer.valueOf(idText.getText()));
                if (student==null){
                    idText.setText("学号不存在哦");
                    nameText.setText("");
                    sexText.setText("");
                    minZuText.setText("");
                    suoZaiXiText.setText("");
                    zhuanYeText.setText("");
                    banJiText.setText("");
                }else {
                    nameText.setText(student.getName());
                    sexText.setText(student.getSex());
                    minZuText.setText(student.getMinZu());
                    suoZaiXiText.setText(student.getSuoZaiXi());
                    zhuanYeText.setText(student.getZhuanYeMing());
                    banJiText.setText(student.getBanJi());
                }
            }
        });
        JPanel jPanel7=new JPanel();
        jPanel7.add(bt1);


        JButton bt2=new JButton("添加学生信息");
        //为按钮添加点击事件
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ui.addStudentMian();
            }
        });
        JPanel jPanel8=new JPanel();
        jPanel8.add(bt2);

        jFrame.add(jPanel1);
        JPanel jPanel=new JPanel();
        jPanel.add(new JLabel("输入学号之后点击按钮查询学生信息"));
        jFrame.add(jPanel);
        jFrame.add(jPanel2);
        jFrame.add(new JPanel());
        jFrame.add(jPanel3);
        jFrame.add(new JPanel());
        jFrame.add(jPanel4);
        jFrame.add(new JPanel());
        jFrame.add(jPanel5);
        jFrame.add(new JPanel());
        jFrame.add(jPanel6);
        jFrame.add(new JPanel());
        jFrame.add(jPanel9);
        jFrame.add(new JPanel());
        jFrame.add(jPanel7);
        jFrame.add(jPanel8);
        //设置窗口在屏幕上的显示位置
        jFrame.setBounds(900, 300, 900, 720);
        jFrame.setVisible(true);//设置窗体可见
        //关闭窗口即程序结束
        jFrame.setDefaultCloseOperation(3);
//        窗口大小不可变
        jFrame.setResizable(false);
        jFrame.pack();
    }

    /**
     * 学生信息录入方法
     */
    public void addStudentMian(){
        JFrame jFrame=new JFrame("学生信息录入");
        GridLayout gridLayout = new GridLayout(8, 2);//网格布局设置五列两行
        jFrame.setLayout(gridLayout);//使用网格布局


        JPanel jPanel1= UiUtil.setBiaoQian("学   号");
        JTextField idText=new JTextField(8);
        jPanel1.add(idText);


        JPanel jPanel2=UiUtil.setBiaoQian("姓   名");
        JTextField nameText=new JTextField(8);
        jPanel2.add(nameText);

        JPanel jPanel3=UiUtil.setBiaoQian("性   别");
        JTextField sexText=new JTextField(8);
        jPanel3.add(sexText);

        JPanel jPanel4=UiUtil.setBiaoQian("民   族");
        JTextField minZuText=new JTextField(8);
        jPanel4.add(minZuText);

        JPanel jPanel5=UiUtil.setBiaoQian("所在系");
        JTextField suoZaiXiText=new JTextField(8);
        jPanel5.add(suoZaiXiText);

        JPanel jPanel6=UiUtil.setBiaoQian("专业名");
        JTextField zhuanYeText=new JTextField(8);
        jPanel6.add(zhuanYeText);


        JPanel jPanel8=UiUtil.setBiaoQian("班  级");
        JTextField banJiText=new JTextField(8);
        jPanel8.add(banJiText);

        JButton bt1=new JButton("录入信息");
        //为按钮添加点击事件
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id= Integer.valueOf(idText.getText());
                String name=nameText.getText();
                String sex=sexText.getText();
                String minZu=minZuText.getText();
                String suoZaiXi=suoZaiXiText.getText();
                String zhuanYeMing=zhuanYeText.getText();
                String banJi=banJiText.getText();
                Student student=new Student(id,name,sex,minZu,suoZaiXi,zhuanYeMing,banJi);
                Dao dao=new Dao();
                int result= dao.addStudent(student);
                if (result==1){
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "学生信息录入成功",
                            "录入成功",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }else {
                    JOptionPane.showMessageDialog(
                            jFrame,
                            "学生信息录入失败",
                            "录入失败",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        JPanel jPanel7=new JPanel();
        jPanel7.add(bt1);

        jFrame.add(jPanel1);
        JPanel jPanel=new JPanel();
        jPanel.add(new JLabel("输入全部信息之后点击按钮录入学生信息"));
        jFrame.add(jPanel);
        jFrame.add(jPanel2);
        jFrame.add(new JPanel());
        jFrame.add(jPanel3);
        jFrame.add(new JPanel());
        jFrame.add(jPanel4);
        jFrame.add(new JPanel());
        jFrame.add(jPanel5);
        jFrame.add(new JPanel());
        jFrame.add(jPanel6);
        jFrame.add(new JPanel());
        jFrame.add(jPanel8);
        jFrame.add(new JPanel());
        jFrame.add(jPanel7);
        //设置窗口在屏幕上的显示位置
        jFrame.setBounds(900, 300, 900, 720);
        jFrame.setVisible(true);//设置窗体可见
        //关闭窗口程序不结束
        jFrame.setDefaultCloseOperation(2);
//        窗口大小不可变
        jFrame.setResizable(false);
        jFrame.pack();
    }

}
