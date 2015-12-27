package rest;


import exception.AuctionException;
import entity.User;
import services.AuthBusinessService;

import javax.inject.Inject;

public class AuthRestServicesImpl implements AuthRestServices {

    @Inject
    private AuthBusinessService authBusinessService;

    @Override
    public User login(String username, String password) {
        User user = authBusinessService.loginUser(username, password);

        if (user == null) {
            throw new AuctionException("Authetication problem: wrong username or password");
        }

        return user;
    }

    @Override
    public User logout(String username, String password) {
        return null;
    }
}
