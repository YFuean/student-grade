package com.sx.service.impl;

import com.sx.dao.StudentDAO;
import com.sx.entity.Admin;
import com.sx.entity.Student;
import com.sx.factory.DAOFactory;
import com.sx.service.StudentService;
import com.sx.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Override
    public ResultEntity studentLogin(String id, String password) {
        ResultEntity resultEntity = new ResultEntity();
        Student student = null;
        try {
            student = studentDAO.getStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据账号查找到了记录
        if (student != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(student.getPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(student);
            } else {  //记录存在，密码输入错误
                resultEntity.setCode(1);
                resultEntity.setMessage("密码错误");
            }
        } else {  //账号不存在
            resultEntity.setCode(2);
            resultEntity.setMessage("账号不存在");
        }
        return resultEntity;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = null;
        try {
            studentList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> selectByDepartment(String department) {
        List<Student> studentList = null;
        try {
            studentList = studentDAO.selectByDepartment(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> selectByKeywords(String keywords) {
        List<Student> studentList = null;
        try {
            studentList = studentDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public int insertStudent(Student student) {
        int count = 0;
        try {
            count = studentDAO.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int updateStudent(Student student) {
        int count = 0;
        try {
            count = studentDAO.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteStudent(String id) {
        int count = 0;
        try {
            count = studentDAO.deleteStudent(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
