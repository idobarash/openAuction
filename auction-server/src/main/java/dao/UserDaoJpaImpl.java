package dao;


import dao.UserDao;
import dao.base.AbstractJpaDao;
import model.User;

import javax.inject.Named;
import javax.persistence.Query;

@Named
public class UserDaoJpaImpl extends AbstractJpaDao<User> implements UserDao {


    @Override
    public User readUserByUsernameAndPassword(String username, String password) {

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                .setParameter("username", username)
                .setParameter("password", password);

        return (User) query.getSingleResult();
    }
}
