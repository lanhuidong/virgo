package com.nexusy.virgo.data.dao.hibernate;

import com.nexusy.virgo.data.dao.UserDao;
import com.nexusy.virgo.data.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * @author lan
 * @since 2013-11-15
 */
@Repository
public class UserDaoHibernate extends BaseDaoHibernate<User, Long> implements UserDao {

    public UserDaoHibernate() {
        super(User.class);
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = getSession().createQuery("from User where username=:username");
        query.setString("username", username);
        return (User) query.uniqueResult();
    }
}
