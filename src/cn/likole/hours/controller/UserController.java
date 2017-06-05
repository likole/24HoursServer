package cn.likole.hours.controller;

import cn.likole.hours.entity.User;
import cn.likole.hours.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likole on 4/8/2017.
 */
@Controller
@Scope("prototype")
public class UserController extends ActionSupport implements ModelDriven<User> {

    @Autowired
    UserService userService;

    User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    Map<String, Object> map = new HashMap<String, Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public String register() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int rsCode = userService.register(user);
        map.put("status", rsCode);
        if(rsCode==0)map.put("token", userService.getToken(user));
        setMessage(rsCode);
        return SUCCESS;
    }

    public String login() {
        int rsCode = userService.login(user);
        map.put("status", rsCode);
        if(rsCode==0) map.put("token", userService.getToken(user));
        setMessage(rsCode);
        return SUCCESS;
    }

    public String getInfo() {
        if (user.getToken() == null || user.getToken().length() != 32) {
            map.put("status", 105);
            map.put("message", "token值无效！");
        } else {
            map.put("status", 0);
            User rs=userService.getInfo(user);
            rs.setPassword("*");
            if(rs.getAvatar()==null)rs.setAvatar("24hours.png");
            map.put("data", rs);
        }
        return SUCCESS;
    }

    public String setInfo() {
        int rsCode=userService.updateInfo(user);
        map.put("status",rsCode);
        setMessage(rsCode);
        return SUCCESS;
    }

    public String changePassword()
    {
        int rsCode=userService.changePassword(user);
        map.put("status",rsCode);
        setMessage(rsCode);
        return SUCCESS;
    }

    private void setMessage(int rsCode)
    {
        switch (rsCode) {
            case 101:
                map.put("message", "手机号或邮箱为空!");
                break;
            case 102:
                map.put("message", "密码为空或不符合要求!");
                break;
            case 103:
                map.put("message", "此手机号或邮箱已被注册!");
                break;
            case 104:
                map.put("message", "用户名或密码错误!");
                break;
            case 105:
                map.put("message", "token值无效！");
                break;
            case 106:
                map.put("message", "昵称不能为空！");
                break;
        }
    }

}
