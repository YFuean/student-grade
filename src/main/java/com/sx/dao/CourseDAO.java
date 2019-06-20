package com.sx.dao;

import com.sx.entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    List<Course> selectAll() throws SQLException;
    int insertCourse(Course course) throws SQLException;
    int deleteCourse(int id) throws SQLException;
}
