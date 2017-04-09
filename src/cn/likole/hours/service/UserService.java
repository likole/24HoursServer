package cn.likole.hours.service;

import cn.likole.hours.dao.UserDAO;
import cn.likole.hours.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by likole on 4/8/2017.
 */
@Transactional
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void save(User u)
    {
        userDAO.save(u);
    }
}
