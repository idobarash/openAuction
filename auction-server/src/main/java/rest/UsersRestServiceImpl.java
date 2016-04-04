package rest;


import entity.User;
import services.UsersBusinessService;

import javax.inject.Inject;

/**
 * User data REST service implementation.
 * Handles request-response operations for User data
 *
 * Author: Ido Barash
 */
public class UsersRestServiceImpl implements UsersRestService {

    @Inject
    private UsersBusinessService usersBusinessService;


    @Override
    public User get(Integer id) {
        return usersBusinessService.getUserById(id);
    }

    @Override
    public Boolean saveNewUser(User user) {
        usersBusinessService.saveUser(user);
        return true;
    }

    @Override
    public User updateUser(User user) {
        return usersBusinessService.updateUser(user);
    }

    @Override
    public Boolean deleteUser(User user) {

        try {
            usersBusinessService.deleteUser(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
