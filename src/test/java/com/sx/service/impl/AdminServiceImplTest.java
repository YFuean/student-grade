package com.sx.service.impl;

import com.sx.factory.ServiceFactory;
import com.sx.service.AdminService;
import com.sx.utils.ResultEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminServiceImplTest {
    private AdminService adminService = ServiceFactory.getAdminServiceInstance();
    @Test
    public void adminLogin() {
        ResultEntity resultEntity = adminService.adminLogin("yuefan@qq.com", "123456");
        System.out.println(resultEntity);
    }
}