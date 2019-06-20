package com.sx.dao.impl;

import com.sx.dao.AdminDAO;
import com.sx.entity.Admin;
import com.sx.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDAOImplTest {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();
    @Test
    public void getAdminByAccount() {
        try {
            Admin admin = adminDAO.getAdminByAccount("yuefan@qq.com");
            if (admin != null) {
                System.out.println(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}