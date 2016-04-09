package services;

import dao.UserDao;
import entity.User;
import exception.AuctionException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Service class to handle all the logic
 * regarding the Authentication.
 *
 * Author: Ido Barash
 */
@Named
@Stateless
public class UsersBusinessService {

    @Inject
    private UserDao userDao;

    /**
     * Load user by ID
     * @param id the id to load by
     * @return the user if exists
     */
    @Transactional
    public User getUserById(int id) {
        return userDao.read(id, User.class);
    }

    public User saveUser(User user) {

        User persistedUserWithUsername = userDao.readUserByUsername(user.getUsername());
        if (user != null) {
            throw new AuctionException("User with this username already exists");
        }

        user.setIsActive(true);
        userDao.create(user);
        return user;
    }

    /**
     * Update user data
     * @param user the user to update
     * @return the updated user
     */
    @Transactional
    public User updateUser(User user) {
        return userDao.update(user);
    }

    /**
     * Delete a user
     * This is a soft delete -> set user to disabled!
     *
     * @param user the user to delete
     */
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
