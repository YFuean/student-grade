package com.sx.service.impl;

import com.sx.dao.StudentGradeVODAO;
import com.sx.entity.Grade;
import com.sx.entity.StudentGradeVO;
import com.sx.factory.DAOFactory;
import com.sx.service.StuGraVOService;

import java.sql.SQLException;
import java.util.List;

public class StuGraVOServiceImpl implements StuGraVOService {
    private StudentGradeVODAO studentGradeVODAO = DAOFactory.getStudentGradeVODAOInstance();
    @Override
    public List<StudentGradeVO> selectAll() {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByCourseId(int courseId) {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByCourseId(courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByCourseName(String courseName) {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByCourseName(courseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByStudentId(String studentId) {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByStudentId(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByStudentName(String studentName) {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByStudentName(studentName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByTeacherId(String teacherId) {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByTeacherId(teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByTeacherName(String teacherName) {
        List<StudentGradeVO> studentGradeVOList = null;
        try {
            studentGradeVOList = studentGradeVODAO.selectByTeacherName(teacherName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradeVOList;
    }

    @Override
    public int insertGrade(Grade grade) {
        int count = 0;
        try {
            count = studentGradeVODAO.insertGrade(grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int updateGrade(Grade grade) {
        int count = 0;
        try {
            count = studentGradeVODAO.updateGrade(grade);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteGrade(int id) {
        int count = 0;
        try {
            count = studentGradeVODAO.deleteGrade(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
