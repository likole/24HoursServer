package cn.likole.hours.controller;

import cn.likole.hours.domain.User;
import cn.likole.hours.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by likole on 4/8/2017.
 */
@Controller
@Scope("prototype")
public class UserController extends ActionSupport implements ModelDriven<User>{

    @Autowired
    UserService userService;

    User user=new User();
    @Override
    public User getModel() {
        return user;
    }
    public void save()
    {
       userService.save(user);
    }


}
