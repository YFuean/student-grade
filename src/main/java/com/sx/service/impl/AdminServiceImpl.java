package com.sx.service.impl;

import com.sx.dao.AdminDAO;
import com.sx.entity.Admin;
import com.sx.factory.DAOFactory;
import com.sx.service.AdminService;
import com.sx.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();

    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity = new ResultEntity();
        Admin admin = null;
        try {
            admin = adminDAO.getAdminByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据账号查找到了记录
        if (admin != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(admin);
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
}
