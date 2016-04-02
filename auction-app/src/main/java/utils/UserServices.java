package utils;

import entity.User;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class UserServices {

    private static final String USER_CREATE = "/users";
    private static final String USER_UPDATE = "/users/{%s}";

    private User generate(String username, String password, String email) {

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        return user;
    }

    public void updateUser(User user) {
        RestUtil.httpPut(USER_CREATE, Boolean.TYPE, user);
    }

    public void createUser(String username, String password, String email) {
        User user = generate(username, password, email);
        RestUtil.httpPost(USER_CREATE, Boolean.TYPE, user);
    }
}
