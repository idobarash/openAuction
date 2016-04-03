package services;

import dao.UserDao;
import entity.Item;
import entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Named
@Stateless
public class AuthBusinessService {

    @Inject
    private UserDao userDao;


    @Transactional
    public User loginUser(String username, String password) {

        User user =  userDao.readUserByUsernameAndPassword(username, password);
        user.setItems(new ArrayList<Item>());

        return user;
    }
}
