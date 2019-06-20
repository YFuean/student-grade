package com.sx.factory;

import com.sx.dao.*;
import com.sx.dao.impl.*;

public class DAOFactory {
    public static AdminDAO getAdminDAOInstance(){return new AdminDAOImpl();}
    public static StudentDAO getStudentDAOInstance(){return new StudentDAOImpl();}
    public static TeacherDAO getTeacherDAOInstance(){return new TeacherDAOImpl();}
    public static CourseDAO getCourseDAOInstance(){return new CourseDAOImpl();}
    public static StudentGradeVODAO getStudentGradeVODAOInstance(){return new StudentGradeVODAOImpl(); }
}
