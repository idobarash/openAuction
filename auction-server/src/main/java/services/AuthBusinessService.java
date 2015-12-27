package services;

import dao.UserDao;
import entity.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
public class AuthBusinessService {

    @Inject
    private UserDao userDao;


    @Transactional
    public User loginUser(String username, String password) {
        return userDao.readUserByUsernameAndPassword(username, password);
    }
}
