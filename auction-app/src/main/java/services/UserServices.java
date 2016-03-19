package services;

import entity.Item;
import entity.ItemCategory;
import entity.User;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped
public class UserServices {

    private static final String USER_CREATE = "/users";
    private static final String USER_UPDATE = "/users/{%s}";

    public void createUser(User user) {
        RestUtil.httpPost(USER_CREATE, Boolean.TYPE, user);
    }

}
