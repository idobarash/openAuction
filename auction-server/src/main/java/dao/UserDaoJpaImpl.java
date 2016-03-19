package dao;


import dao.base.AbstractSoftDeletedJpaDao;
import entity.User;

import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * A JPA implementation of User DAO.
 */
@Named
public class UserDaoJpaImpl extends AbstractSoftDeletedJpaDao<User> implements UserDao {

    @Override
    public User readUserByUsernameAndPassword(String username, String password) {

        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password);

            return (User) query.getSingleResult();
        } catch (PersistenceException exception) {
            return null;
        }
    }
}
