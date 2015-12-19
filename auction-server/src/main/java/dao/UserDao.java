package dao;

import dao.base.GenericDao;
import model.User;

public interface UserDao extends GenericDao<User> {

    User readUserByUsernameAndPassword(String userName, String password);
}
