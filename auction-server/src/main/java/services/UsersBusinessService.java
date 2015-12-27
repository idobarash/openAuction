package services;

import dao.UserDao;
import entity.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
public class UsersBusinessService {

    @Inject
    private UserDao userDao;

    @Transactional
    public User getUserById(int id) {
        return userDao.read(id, User.class);
    }

    @Transactional
    public User saveUser(User user) {
        userDao.create(user);
        return user;
    }

    @Transactional
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
