package com.util;

import javax.swing.*;
import java.awt.*;

public class UiUtil {
    /**
     * 设置标签字体和内容的方法，然后加到面板里面
     * @param name  标签的内容
     * @return 返回一个面板
     */
    public static JPanel setBiaoQian(String name){
        JLabel jLabel=new JLabel(name);
        jLabel.setFont(new Font(null, Font.PLAIN, 25));
        JPanel jPanel1=new JPanel();
        jPanel1.add(jLabel);
        return jPanel1;
    }
}
