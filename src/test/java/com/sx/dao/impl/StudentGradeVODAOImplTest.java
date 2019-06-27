package com.sx.dao.impl;

import com.sx.dao.StudentGradeVODAO;
import com.sx.entity.Grade;
import com.sx.entity.StudentGradeVO;
import com.sx.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class StudentGradeVODAOImplTest {
    private StudentGradeVODAO studentGradeVODAO = DAOFactory.getStudentGradeVODAOInstance();
    @Test
    public void selectAll() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByCourseId() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByCourseId(7);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByCourseName() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByCourseName("体育");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByStudentId() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByStudentId("1802341101");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByStudentName() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByStudentName("乙");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByTeacherId() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByTeacherId("1412666001");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void selectByTeacherName() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByTeacherName("甲戌");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentGradeVOList.forEach(studentGradeVO -> System.out.println(studentGradeVO));
    }

    @Test
    public void insertGrade() {
        Grade grade = new Grade();
        grade.setCourseId(4);
        grade.setStudentId("1802341102");
        grade.setTeacherId("1412666007");
        grade.setTestDate(new Date());
        grade.setScore(24);
        try {
            studentGradeVODAO.insertGrade(grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateGrade() {
        Grade grade = new Grade();
        grade.setId(34);
        grade.setScore(100);
        try {
            studentGradeVODAO.updateGrade(grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteGrade() {
        try {
            studentGradeVODAO.deleteGrade(37);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}