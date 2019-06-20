package com.sx.dao;

import com.sx.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    /**
     * 根据账号查找学生
     * @param id
     * @return Student
     * @throws SQLException
     */
    Student getStudentById(String id) throws SQLException;

    List<Student> selectAll()throws SQLException;
    List<Student> selectByDepartment(String department) throws SQLException;
    List<Student> selectByKeywords(String keywords) throws SQLException;
    int insertStudent(Student student) throws SQLException;
    int deleteStudent(String id) throws SQLException;
    int updateStudent(Student student) throws SQLException;
}
