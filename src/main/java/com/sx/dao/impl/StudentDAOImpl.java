package com.sx.dao.impl;

import com.sx.dao.StudentDAO;
import com.sx.entity.Student;
import com.sx.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public Student getStudentById(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_student WHERE student_id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        Student student = null;
        while (rs.next()) {
            String studentId = rs.getString("student_id");
            String password = rs.getString("password");
            String name = rs.getString("student_name");
            String gender = rs.getString("gender");
            String department = rs.getString("department");
            String avatar = rs.getString("avatar");
            String address = rs.getString("address");
            Date birthday = rs.getDate("birthday");
            Date admissionTime = rs.getDate("admission_time");
            student = new Student();
            student.setId(studentId);
            student.setPassword(password);
            student.setName(name);
            student.setGender(gender);
            student.setDepartment(department);
            student.setAvatar(avatar);
            student.setAddress(address);
            student.setBirthday(birthday);
            student.setAdmissionTime(admissionTime);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return student ;
    }

    @Override
    public List<Student> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_student ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Student> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public List<Student> selectByDepartment(String department) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_student WHERE department = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,department);
        ResultSet rs = pstmt.executeQuery();
        List<Student> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public List<Student> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_student WHERE student_name LIKE ? OR address LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<Student> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public int insertStudent(Student student) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_student VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,student.getId());
        pstmt.setString(2,student.getPassword());
        pstmt.setString(3,student.getName());
        pstmt.setString(4,student.getGender());
        pstmt.setString(5,student.getAvatar());
        pstmt.setString(6,student.getDepartment());
        pstmt.setDate(7,new Date(student.getBirthday().getTime()));
        pstmt.setString(8,student.getAddress());
        pstmt.setDate(9,new Date(student.getAdmissionTime().getTime()));
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deleteStudent(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_student  WHERE student_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int updateStudent(Student student) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_student SET password = ?,student_name = ?,gender = ?," +
                "department = ?,birthday = ?,admission_time = ?,avatar = ? ,address = ? WHERE student_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,student.getPassword());
        pstmt.setString(2,student.getName());
        pstmt.setString(3,student.getGender());
        pstmt.setString(4,student.getDepartment());
        pstmt.setDate(5,new Date(student.getBirthday().getTime()));
        pstmt.setDate(6,new Date(student.getAdmissionTime().getTime()));
        pstmt.setString(7,student.getAvatar());
        pstmt.setString(8,student.getAddress());
        pstmt.setString(9,student.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    private List<Student> convert(ResultSet rs) throws SQLException{
        List<Student> studentList = new ArrayList<>();
        while (rs.next()){
            Student student = new Student();
            student.setId(rs.getString("student_id"));
            student.setPassword(rs.getString("password"));
            student.setName(rs.getString("student_name"));
            student.setGender(rs.getString("gender"));
            student.setDepartment(rs.getString("department"));
            student.setAvatar(rs.getString("avatar"));
            student.setAddress(rs.getString("address"));
            student.setBirthday(rs.getDate("birthday"));
            student.setAdmissionTime(rs.getDate("admission_time"));
            studentList.add(student);
        }
        return studentList;
    }
}
