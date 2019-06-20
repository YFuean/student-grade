package com.sx.service.impl;

import com.sx.entity.Course;
import com.sx.factory.ServiceFactory;
import com.sx.service.CourseService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseServiceImplTest {
    private CourseService courseService = ServiceFactory.getCourseServiceInstance();
    @Test
    public void selectAll() {
        List<Course> courseList = courseService.selectAll();
        courseList.forEach(course -> System.out.println(course));
    }

    @Test
    public void insertCourse() {
        Course course = new Course();
        course.setName("测试33");
        course.setCredit(1);
        courseService.insertCourse(course);
    }

    @Test
    public void deleteCourse() {
        courseService.deleteCourse(57);
    }
}