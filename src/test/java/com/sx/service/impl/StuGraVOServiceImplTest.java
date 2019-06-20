package com.sx.service.impl;

import com.sx.entity.Grade;
import com.sx.entity.StudentGradeVO;
import com.sx.factory.ServiceFactory;
import com.sx.service.StuGraVOService;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StuGraVOServiceImplTest {
    private StuGraVOService stuGraVOService = ServiceFactory.getStuGraVOServiceInstance();
    @Test
    public void selectAll() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectAll();
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByCourseId() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectByCourseId(1);
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByCourseName() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectByCourseName("体育");
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByStudentId() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectByStudentId("1802341103");
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByStudentName() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectByStudentName("丁");
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByTeacherId() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectByTeacherId("1412666006");
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByTeacherName() {
        List<StudentGradeVO> studentGradeVOList = stuGraVOService.selectByTeacherName("庚午");
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void insertGrade() {
        Grade grade = new Grade();
        grade.setCourseId(8);
        grade.setStudentId("1802341102");
        grade.setTeacherId("1412666007");
        grade.setTestDate(new Date());
        grade.setScore(59);
        stuGraVOService.insertGrade(grade);
    }

    @Test
    public void updateGrade() {
        Grade grade = new Grade();
        grade.setId(26);
        grade.setScore(70);
        stuGraVOService.updateGrade(grade);
    }

    @Test
    public void deleteGrade() {
        stuGraVOService.deleteGrade(26);
    }
}