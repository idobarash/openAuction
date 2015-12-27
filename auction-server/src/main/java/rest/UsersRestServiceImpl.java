package rest;


import entity.User;
import services.UsersBusinessService;

import javax.inject.Inject;


public class UsersRestServiceImpl implements UsersRestService {

    @Inject
    private UsersBusinessService usersBusinessService;


    @Override
    public User get(Integer id) {
        return usersBusinessService.getUserById(id);
    }

    @Override
    public User saveNewUser(User user) {
        return usersBusinessService.saveUser(user);
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
