package com.sx.service.impl;

import com.sx.entity.Teacher;
import com.sx.factory.ServiceFactory;
import com.sx.service.TeacherService;
import com.sx.utils.ResultEntity;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TeacherServiceImplTest {
    private TeacherService teacherService = ServiceFactory.getTeacherServiceInstance();

    @Test
    public void teacherLogin() {
        ResultEntity resultEntity = teacherService.teacherLogin("1412666002", "123456");
        System.out.println(resultEntity);
    }

    @Test
    public void selectAll() {
        List<Teacher> teacherList = teacherService.selectAll();
        teacherList.forEach(teacher -> System.out.println(teacher));
    }

    @Test
    public void selectByDepartment() {
        List<Teacher> teacherList = teacherService.selectByDepartment("公共基础课部");
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
        teacherService.insertTeacher(teacher);
    }

    @Test
    public void updateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId("100");
        teacher.setPassword("123456");
        teacher.setName("岳凡");
        teacher.setDepartment("计软院");
        teacher.setGender("女");
        teacher.setJobTitle("教授");
        teacher.setEducation("本科");
        teacher.setAvatar("假头像");
        teacherService.updateTeacher(teacher);
    }

    @Test
    public void deleteTeacher() {
        teacherService.deleteTeacher("100");
    }
}