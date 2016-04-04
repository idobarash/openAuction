package services;

import dao.UserDao;
import entity.Item;
import entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Service class to handle all the logic
 * regarding the Authentication.
 *
 * Author: Ido Barash
 */
@Named
@Stateless
public class AuthBusinessService {

    @Inject
    private UserDao userDao;


    /**
     * Login user to the system
     *
     * @param username username
     * @param password password
     * @return User if exists, throws excpetion if not.
     */
    @Transactional
    public User loginUser(String username, String password) {

        User user =  userDao.readUserByUsernameAndPassword(username, password);
        user.setItems(new ArrayList<Item>());

        return user;
    }
}
