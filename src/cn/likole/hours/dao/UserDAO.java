package cn.likole.hours.dao;

import cn.likole.hours.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by likole on 4/8/2017.
 */
@Repository
public class UserDAO extends HibernateDaoSupport {

    UserDAO(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void save(User u) {
        this.getHibernateTemplate().save(u);
    }

    public User getByPhone(String phone) {
        List<User> rs = (List<User>) this.getHibernateTemplate().find("from User where phone=?", phone);
        if (rs.size() > 0) {
            return rs.get(0);
        } else {
            return null;
        }
    }

    public User getByEmail(String email) {
        List<User> rs = (List<User>) this.getHibernateTemplate().find("from User where email=?", email);
        if (rs.size() > 0) {
            return rs.get(0);
        } else {
            return null;
        }
    }

    public User getByUsernameAndPassword(String username, String password) {
        List<User> rs = (List<User>) this.getHibernateTemplate().find("from User where (email=? or phone =?)and password=?", username, username, password);
        if (rs.size() > 0) {
            return rs.get(0);
        } else {
            return null;
        }
    }

    public String getTokenByUsername(String username) {
        List<User> rs = (List<User>) this.getHibernateTemplate().find("from User where (email=? or phone =?)", username, username);
        if (rs.size() > 0) {
            return rs.get(0).getToken();
        } else {
            return null;
        }
    }

}
