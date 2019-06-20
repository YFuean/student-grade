package com.sx.dao.impl;

import com.sx.dao.StudentDAO;
import com.sx.entity.Student;
import com.sx.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class StudentDAOImplTest {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Test
    public void getStudentById() {
        try {
            Student student = studentDAO.getStudentById("1802341101");
            if (student != null) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        List<Student> studentList = null;
        try {
            studentList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void selectByDepartment() {
        List<Student> studentList = null;
        try {
            studentList = studentDAO.selectByDepartment("计算机与软件学院");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void selectByKeywords() {
        List<Student> studentList = null;
        try {
            studentList = studentDAO.selectByKeywords("江苏");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setId("100");
        student.setPassword("123456");
        student.setName("yuefan");
        student.setDepartment("体育部");
        student.setGender("女");
        student.setBirthday(new Date());
        student.setAdmissionTime(new Date());
        student.setAvatar("假头像");
        student.setAddress("澳大利亚");
        try {
            studentDAO.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteStudent() {
        try {
            studentDAO.deleteStudent("100");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId("100");
        student.setPassword("000000");
        student.setName("yuefan");
        student.setDepartment("体育部");
        student.setGender("女");
        student.setBirthday(new Date());
        student.setAdmissionTime(new Date());
        student.setAvatar("头像");
        student.setAddress("北京");
        try {
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}