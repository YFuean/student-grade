package com.sx.service.impl;

import com.sx.entity.Student;
import com.sx.factory.ServiceFactory;
import com.sx.service.StudentService;
import com.sx.utils.ResultEntity;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceImplTest {
    private StudentService studentService = ServiceFactory.getStudentServiceInstance();

    @Test
    public void studentLogin() {
        ResultEntity resultEntity = studentService.studentLogin("1802341101", "123456");
        System.out.println(resultEntity);
    }

    @Test
    public void selectAll() {
        List<Student> studentList = studentService.selectAll();
        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void selectByDepartment() {
        List<Student> studentList = studentService.selectByDepartment("计算机与软件学院");
        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void selectByKeywords() {
        List<Student> studentList = studentService.selectByKeywords("甲");
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
        studentService.insertStudent(student);
    }

    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId("100");
        student.setPassword("111111");
        student.setName("岳凡");
        student.setDepartment("体育部");
        student.setGender("男");
        student.setBirthday(new Date());
        student.setAdmissionTime(new Date());
        student.setAvatar("https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/9c2436e7-551f-427b-91f5-d6a11b92885d.jpg");
        student.setAddress("纽约");
        studentService.updateStudent(student);
    }

    @Test
    public void deleteStudent() {
        studentService.deleteStudent("100");
    }
}