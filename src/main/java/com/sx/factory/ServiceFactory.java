package com.sx.factory;

import com.sx.service.*;
import com.sx.service.impl.*;

public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {return new AdminServiceImpl();}
    public static StudentService getStudentServiceInstance(){return new StudentServiceImpl();}
    public static TeacherService getTeacherServiceInstance(){return new TeacherServiceImpl();}
    public static CourseService getCourseServiceInstance(){return new CourseServiceImpl();}
    public static StuGraVOService getStuGraVOServiceInstance(){return new StuGraVOServiceImpl(); }
}
