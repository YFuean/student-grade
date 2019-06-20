package com.sx.service;

import com.sx.entity.Teacher;
import com.sx.utils.ResultEntity;

import java.util.List;

public interface TeacherService {
    /**
     * 教师登录
     * @param id
     * @param password
     * @return
     */
    ResultEntity teacherLogin(String id, String password);

    List<Teacher> selectAll();
    List<Teacher> selectByDepartment(String department);
    int insertTeacher(Teacher teacher);
    int updateTeacher(Teacher teacher);
    int deleteTeacher(String id);
}
