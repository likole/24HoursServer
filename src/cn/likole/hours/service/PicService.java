package cn.likole.hours.service;

import cn.likole.hours.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by likole on 6/4/2017.
 */
@Service
@Transactional
public class PicService {

    @Autowired
    UserDAO userDAO;

    public int setAvatar(String token,String avatar){
        userDAO.getByToken(token).setAvatar(avatar);
        return 0;
    }

}
