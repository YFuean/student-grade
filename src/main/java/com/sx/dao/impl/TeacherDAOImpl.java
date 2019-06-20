package com.sx.dao.impl;

import com.sx.dao.TeacherDAO;
import com.sx.entity.Teacher;
import com.sx.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public Teacher getTeacherById(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher WHERE id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Teacher teacher = null;
        while (rs.next()) {
            String teacherId = rs.getString("id");
            String password = rs.getString("password");
            String name = rs.getString("teacher_name");
            String gender = rs.getString("gender");
            String department = rs.getString("department");
            String jobTitle = rs.getString("job_title");
            String education = rs.getString("education");
            String avatar = rs.getString("avatar");
            teacher = new Teacher();
            teacher.setId(teacherId);
            teacher.setPassword(password);
            teacher.setName(name);
            teacher.setGender(gender);
            teacher.setDepartment(department);
            teacher.setJobTitle(jobTitle);
            teacher.setEducation(education);
            teacher.setAvatar(avatar);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teacher ;
    }

    @Override
    public List<Teacher> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Teacher> teacherList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teacherList;
    }

    @Override
    public List<Teacher> selectByDepartment(String department) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher WHERE department = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,department);
        ResultSet rs = pstmt.executeQuery();
        List<Teacher> teacherList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teacherList;
    }

    @Override
    public int insertTeacher(Teacher teacher) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_teacher VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teacher.getId());
        pstmt.setString(2,teacher.getPassword());
        pstmt.setString(3,teacher.getName());
        pstmt.setString(4,teacher.getGender());
        pstmt.setString(5,teacher.getDepartment());
        pstmt.setString(6,teacher.getJobTitle());
        pstmt.setString(7,teacher.getEducation());
        pstmt.setString(8,teacher.getAvatar());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deleteTeacher(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_teacher  WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int updateTeacher(Teacher teacher) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_teacher SET password = ?,teacher_name = ?,gender = ?," +
                "department = ?,job_title = ?,education = ?,avatar = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teacher.getPassword());
        pstmt.setString(2,teacher.getName());
        pstmt.setString(3,teacher.getGender());
        pstmt.setString(4,teacher.getDepartment());
        pstmt.setString(5,teacher.getJobTitle());
        pstmt.setString(6,teacher.getEducation());
        pstmt.setString(7,teacher.getAvatar());
        pstmt.setString(8,teacher.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    private List<Teacher> convert(ResultSet rs) throws SQLException{
        List<Teacher> teacherList = new ArrayList<>();
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setPassword(rs.getString("password"));
            teacher.setName(rs.getString("teacher_name"));
            teacher.setGender(rs.getString("gender"));
            teacher.setDepartment(rs.getString("department"));
            teacher.setJobTitle(rs.getString("job_title"));
            teacher.setEducation(rs.getString("education"));
            teacher.setAvatar(rs.getString("avatar"));
            teacherList.add(teacher);
        }
        return teacherList;
    }
}
