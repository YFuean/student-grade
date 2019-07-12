package com.sx.frame;

import com.sx.entity.Admin;
import com.sx.entity.Student;
import com.sx.entity.Teacher;
import com.sx.factory.ServiceFactory;
import com.sx.ui.ImgPanel;
import com.sx.ui.TranslucenceJPanel;
import com.sx.utils.ResultEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame  extends JFrame{
    private ImgPanel rootPanel;
    private TranslucenceJPanel mainPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JRadioButton AdminRadioButton;
    private JRadioButton TeacherRadioButton;
    private JRadioButton StudentRadioButton;
    private JButton LoginButton;

    public LoginFrame(){
        setTitle("管理员登录界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //单选和登录
        ButtonGroup group = new ButtonGroup();
        group.add(AdminRadioButton);
        group.add(TeacherRadioButton);
        group.add(StudentRadioButton);
        AdminRadioButton.setSelected(true);
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获得输入的账号和密码，注意密码框组件的使用方法
                String account = accountField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                if (AdminRadioButton.isSelected()){
                    ResultEntity resultEntity = ServiceFactory.getAdminServiceInstance().adminLogin(account, password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity);
                    //登录成功，进入主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new AdminMainFrame((Admin)resultEntity.getData());
                        LoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountField.setText("");
                        passwordField.setText("");
                    }
                }
                if (TeacherRadioButton.isSelected()){
                    ResultEntity resultEntity = ServiceFactory.getTeacherServiceInstance().teacherLogin(account,password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity);
                    //登录成功，进入主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new TeacherMainFrame((Teacher)resultEntity.getData());
                        LoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountField.setText("");
                        passwordField.setText("");
                    }
                }
                if (StudentRadioButton.isSelected()){
                    ResultEntity resultEntity = ServiceFactory.getStudentServiceInstance().studentLogin(account,password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity);
                    //登录成功，进入主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new StudentMainFrame((Student)resultEntity.getData());
                        LoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountField.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });
        //背景
        rootPanel.setFileName("login.png");
        rootPanel.repaint();
        mainPanel.setOpaque(false);
        //面板半透明
//        mainPanel.setTransparent(0.3f);
//        mainPanel.repaint();
    }
    public static void main(String[] args) {
        new LoginFrame();
    }
}
