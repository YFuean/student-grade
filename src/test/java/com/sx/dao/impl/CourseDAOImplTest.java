package com.sx.dao.impl;

import com.sx.dao.CourseDAO;
import com.sx.entity.Course;
import com.sx.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CourseDAOImplTest {
    private CourseDAO courseDAO = DAOFactory.getCourseDAOInstance();
    @Test
    public void selectAll() {
        List<Course> courseList = null;
        try {
            courseList = courseDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void insertCourse() {
        Course course = new Course();
        course.setName("测试7");
        course.setCredit(8);
        try {
            courseDAO.insertCourse(course);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCourse() {
        try {
            courseDAO.deleteCourse(59);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}