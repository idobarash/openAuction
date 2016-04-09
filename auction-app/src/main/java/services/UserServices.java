package services;

import entity.User;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 * Service class that handles all the communication with server
 * in regard to user data.
 *
 * Author: Ido Barash
 */
@Named
@ApplicationScoped
public class UserServices {

    private static final String USER_CREATE = "/users";
    private static final String USER_UPDATE = "/users/{%s}";

    private User generate(String username, String password, String email, String name, String phone) {

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setPhone(phone);
        user.setName(name);

        return user;
    }

    public void updateUser(User user) {
        RestUtil.httpPut(USER_CREATE, Boolean.TYPE, user);
    }

    /**
     * Create a new user
     *
     * @param username
     * @param password
     * @param email
     * @param name
     * @param phone
     */
    public void createUser(String username, String password, String email, String name, String phone) {

        try {
            User user = generate(username, password, email, name, phone);
            RestUtil.httpPost(USER_CREATE, Boolean.TYPE, user);

            NavigationUtil.navigateToRoot();
        } catch (Exception e) {

        }
    }
}
