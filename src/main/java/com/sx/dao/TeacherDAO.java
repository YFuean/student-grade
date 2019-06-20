package com.sx.dao;

import com.sx.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    /**
     * 根据账号查找任课老师
     * @param id
     * @return
     * @throws SQLException
     */
    Teacher getTeacherById(String id) throws SQLException;


    List<Teacher> selectAll()throws SQLException;
    List<Teacher> selectByDepartment(String department) throws SQLException;
    int insertTeacher(Teacher teacher) throws SQLException;
    int deleteTeacher(String id) throws SQLException;
    int updateTeacher(Teacher teacher) throws SQLException;
}
