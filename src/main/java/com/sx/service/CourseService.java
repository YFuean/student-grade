package com.sx.service;

import com.sx.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> selectAll();
    int insertCourse(Course course);
    int deleteCourse(int id);
}
