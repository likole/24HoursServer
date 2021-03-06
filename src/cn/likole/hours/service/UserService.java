package cn.likole.hours.service;

import cn.likole.hours.dao.UserDAO;
import cn.likole.hours.entity.User;
import cn.likole.hours.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Created by likole on 4/8/2017.
 */
@Transactional
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public int register(User u) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Long time = Calendar.getInstance().getTimeInMillis();
        if (u.getPassword() == null || u.getPassword().length() != 32) return 102;
        if(u.getName()==null) u.setName("24Hours用户");
        if (u.getPhone() != null) {
            //通过手机号注册
            if (userDAO.getByPhone(u.getPhone()) != null) return 103;
            u.setToken(MD5Util.MD5encode(time + u.getPhone() + u.getPassword()));
            userDAO.save(u);
        } else if (u.getEmail() != null) {
            //通过邮箱注册
            if (userDAO.getByEmail(u.getEmail()) != null) return 103;
            u.setToken(MD5Util.MD5encode(time + u.getEmail() + u.getPassword()));
            userDAO.save(u);
        } else {
            return 101;
        }
        return 0;
    }

    public int login(User user) {
        if (user.getPassword() == null || user.getPassword().length() != 32) return 102;
        if (user.getPhone() != null) {
            if (userDAO.getByUsernameAndPassword(user.getPhone(), user.getPassword()) == null) return 104;
        } else if (user.getEmail() != null) {
            if (userDAO.getByUsernameAndPassword(user.getEmail(), user.getPassword()) == null) return 104;
        } else {
            return 101;
        }
        return 0;
    }

    public String getToken(User user) {
        if (user.getPhone() != null) {
            return userDAO.getByPhone(user.getPhone()).getToken();
        } else {
            return userDAO.getByEmail(user.getEmail()).getToken();
        }
    }

    public User getInfo(User user) {
        User rs= userDAO.getByToken(user.getToken());
        return rs;
    }

    public int updateInfo(User user) {
        if(user.getToken()==null||user.getToken().length()!=32) return 105;
        if(user.getName()==null) return 106;
        userDAO.updateBasicInfo(user.getToken(),user.getName(),user.getGender());
        return 0;
    }

    public int changePassword(User user) {
        if(user.getToken()==null||user.getToken().length()!=32) return 105;
        User rs=userDAO.getByToken(user.getToken());
        if (user.getPassword() == null || user.getPassword().length() != 32) return 102;
        rs.setPassword(user.getPassword());
        return 0;
    }
}
