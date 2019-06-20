package com.sx.service;

import com.sx.entity.Grade;
import com.sx.entity.StudentGradeVO;
import java.util.List;

public interface StuGraVOService {
    List<StudentGradeVO> selectAll();

    List<StudentGradeVO> selectByCourseId(int courseId);
    List<StudentGradeVO> selectByCourseName(String courseName);

    List<StudentGradeVO> selectByStudentId(String studentId);
    List<StudentGradeVO> selectByStudentName(String studentName);

    List<StudentGradeVO> selectByTeacherId(String teacherId);
    List<StudentGradeVO> selectByTeacherName(String teacherName);


    int insertGrade(Grade grade);
    int updateGrade(Grade grade);
    int deleteGrade(int id);
}
