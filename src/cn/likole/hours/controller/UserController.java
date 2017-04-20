package cn.likole.hours.controller;

import cn.likole.hours.domain.User;
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

    Map<String, Object> dataMap = new HashMap<String, Object>();

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public String register() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        dataMap.put("status", userService.register(user));
        return SUCCESS;
    }

    public String login() {
        int rsCode = userService.login(user);
        dataMap.put("status", rsCode);
        if (rsCode == 0) {
            dataMap.put("token", userService.getToken(user));
        }
        return SUCCESS;
    }


}
