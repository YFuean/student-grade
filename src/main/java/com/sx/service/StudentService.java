package com.sx.service;

import com.sx.entity.Student;
import com.sx.utils.ResultEntity;

import java.util.List;

public interface StudentService {

    /**
     * 学生登录
     * @param id
     * @param password
     * @return
     */
    ResultEntity studentLogin(String id, String password);

    List<Student> selectAll();
    List<Student> selectByDepartment(String department);
    List<Student> selectByKeywords(String keywords);
    int insertStudent(Student student);
    int updateStudent(Student student);
    int deleteStudent(String id);
}
