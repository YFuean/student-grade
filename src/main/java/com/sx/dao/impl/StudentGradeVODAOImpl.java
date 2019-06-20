package com.sx.dao.impl;

import com.sx.dao.StudentGradeVODAO;
import com.sx.entity.Grade;
import com.sx.entity.StudentGradeVO;
import com.sx.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeVODAOImpl implements StudentGradeVODAO {

    @Override
    public List<StudentGradeVO> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByCourseId(int courseId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`" +
                "WHERE t4.course_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,courseId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByCourseName(String courseName) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`" +
                "WHERE t1.name = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,courseName);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByStudentId(String studentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`" +
                "WHERE t4.student_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,studentId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByStudentName(String studentName) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`" +
                "WHERE t2.student_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,studentName);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByTeacherId(String teacherId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`" +
                "WHERE t4.teacher_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teacherId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public List<StudentGradeVO> selectByTeacherName(String teacherName) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.name,t1.credit,t2.student_name,t3.teacher_name,t4.*\n" +
                "FROM t_grade t4\n" +
                "LEFT JOIN t_course t1\n" +
                "ON t4.`course_id` = t1.`id`\n" +
                "LEFT JOIN t_student t2\n" +
                "ON t4.`student_id` = t2.`student_id`\n" +
                "LEFT JOIN t_teacher t3\n" +
                "ON t4.`teacher_id` = t3.`id`" +
                "WHERE t3.teacher_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teacherName);
        ResultSet rs = pstmt.executeQuery();
        List<StudentGradeVO> studentGradeVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentGradeVOList;
    }

    @Override
    public int insertGrade(Grade grade) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_grade (student_id,course_id,teacher_id,test_date,score) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,grade.getStudentId());
        pstmt.setInt(2,grade.getCourseId());
        pstmt.setString(3,grade.getTeacherId());
        pstmt.setDate(4,new Date(grade.getTestDate().getTime()));
        pstmt.setInt(5,grade.getScore());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int updateGrade(Grade grade) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_grade SET score = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,grade.getScore());
        pstmt.setInt(2,grade.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deleteGrade(int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_grade WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    private List<StudentGradeVO> convert(ResultSet rs) throws SQLException{
        List<StudentGradeVO> studentGradeVOList = new ArrayList<>();
        while (rs.next()){
            StudentGradeVO sgVO = new StudentGradeVO();
            sgVO.setGradeId(rs.getInt("id"));
            sgVO.setStudentId(rs.getString("student_id"));
            sgVO.setStudentName(rs.getString("student_name"));
            sgVO.setCourseId(rs.getInt("course_id"));
            sgVO.setCourseName(rs.getString("name"));
            sgVO.setCredit(rs.getInt("credit"));
            sgVO.setTeacherId(rs.getString("teacher_id"));
            sgVO.setTeacherName(rs.getString("teacher_name"));
            sgVO.setScore(rs.getInt("score"));
            sgVO.setTestDate(rs.getDate("test_date"));
            studentGradeVOList.add(sgVO);
        }
        return studentGradeVOList;
    }
}
