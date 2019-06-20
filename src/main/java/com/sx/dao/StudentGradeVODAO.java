package com.sx.dao;

import com.sx.entity.Grade;
import com.sx.entity.StudentGradeVO;

import java.sql.SQLException;
import java.util.List;

public interface StudentGradeVODAO {
    List<StudentGradeVO> selectAll() throws SQLException;

    List<StudentGradeVO> selectByCourseId(int courseId) throws SQLException;
    List<StudentGradeVO> selectByCourseName(String courseName) throws SQLException;

    List<StudentGradeVO> selectByStudentId(String studentId) throws SQLException;
    List<StudentGradeVO> selectByStudentName(String studentName) throws SQLException;

    List<StudentGradeVO> selectByTeacherId(String teacherId)throws SQLException;
    List<StudentGradeVO> selectByTeacherName(String teacherName) throws SQLException;


    int insertGrade(Grade grade)throws SQLException;
    int updateGrade(Grade grade) throws SQLException;
    int deleteGrade(int id)throws SQLException;


}
