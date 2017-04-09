package cn.likole.hours.dao;

import cn.likole.hours.domain.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * Created by likole on 4/8/2017.
 */
public class UserDAO extends HibernateDaoSupport{

    public void save(User u)
    {
        this.getHibernateTemplate().save(u);
    }
}
