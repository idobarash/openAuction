package dao;

import dao.base.GenericDao;
import entity.User;

/**
 * Data access object for User
 * Handles special category oriented DB operations
 *
 * Author: Ido Barash
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Read a user by it's username and password
     *
     * @param userName the username
     * @param password the password
     * @return A user if exists
     */
    User readUserByUsernameAndPassword(String userName, String password);

    /**
     * Find a user by an a published auction.
     *
     * @param itemId
     * @return the owner user of the item.
     */
    User findOwnerOfItem(Integer itemId);
}
