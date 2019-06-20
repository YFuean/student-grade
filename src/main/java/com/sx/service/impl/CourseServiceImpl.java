package com.sx.service.impl;

import com.sx.dao.CourseDAO;
import com.sx.entity.Course;
import com.sx.factory.DAOFactory;
import com.sx.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO = DAOFactory.getCourseDAOInstance();
    @Override
    public List<Course> selectAll() {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    @Override
    public int insertCourse(Course course) {
        int count = 0;
        try {
            count = courseDAO.insertCourse(course);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteCourse(int id) {
        int count = 0;
        try {
            count = courseDAO.deleteCourse(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
