package dao;


import dao.base.AbstractSoftDeletedJpaDao;
import entity.User;

import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * JPA DAO implementation to handle Items entities.
 *
 * Author: Ido Barash
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

    @Override
    public User findOwnerOfItem(Integer itemId) {

        try {
            Query query = entityManager.createQuery("SELECT u FROM User u JOIN u.items i WHERE i.id = :itemId")
                    .setParameter("itemId", itemId);

            return (User) query.getSingleResult();

        } catch (PersistenceException exception) {
            return null;
        }
    }

    @Override
    public User readUserByUsername(String username) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username")
                    .setParameter("username", username);

            return (User) query.getSingleResult();

        } catch (PersistenceException exception) {
            return null;
        }
    }
}
