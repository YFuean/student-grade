package com.sx.service.impl;

import com.sx.dao.TeacherDAO;
import com.sx.entity.Teacher;
import com.sx.factory.DAOFactory;
import com.sx.service.TeacherService;
import com.sx.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();
    @Override
    public ResultEntity teacherLogin(String id, String password) {
        ResultEntity resultEntity = new ResultEntity();
        Teacher teacher = null;
        try {
            teacher = teacherDAO.getTeacherById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据账号查找到了记录
        if (teacher != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(teacher.getPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(teacher);
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
    public List<Teacher> selectAll() {
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    @Override
    public List<Teacher> selectByDepartment(String department) {
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherDAO.selectByDepartment(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        int count = 0;
        try {
            count = teacherDAO.insertTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        int count = 0;
        try {
            count = teacherDAO.updateTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteTeacher(String id) {
        int count = 0;
        try {
            count = teacherDAO.deleteTeacher(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
