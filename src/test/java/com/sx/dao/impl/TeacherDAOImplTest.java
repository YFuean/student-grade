package com.sx.dao.impl;

import com.sx.dao.TeacherDAO;
import com.sx.entity.Student;
import com.sx.entity.Teacher;
import com.sx.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TeacherDAOImplTest {
    private TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();
    @Test
    public void getTeacherById() {
        try {
            Teacher teacher = teacherDAO.getTeacherById("1412666001");
            if (teacher != null) {
                System.out.println(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherList.forEach(teacher -> System.out.println(teacher));
    }

    @Test
    public void selectByDepartment() {
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherDAO.selectByDepartment("公共基础课部");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherList.forEach(teacher -> System.out.println(teacher));
    }

    @Test
    public void insertTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId("100");
        teacher.setPassword("123456");
        teacher.setName("yuefan");
        teacher.setDepartment("体育部");
        teacher.setGender("女");
        teacher.setJobTitle("教授");
        teacher.setEducation("本科");
        teacher.setAvatar("假头像");
        try {
            teacherDAO.insertTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTeacher() {
        try {
            teacherDAO.deleteTeacher("100");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId("1412666003");
        teacher.setPassword("123456");
        teacher.setName("yuefan");
        teacher.setDepartment("体育部");
        teacher.setGender("女");
        teacher.setJobTitle("讲师");
        teacher.setEducation("本科");
        teacher.setAvatar("https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/9c2436e7-551f-427b-91f5-d6a11b92885d.jpg");
        try {
            teacherDAO.updateTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}