package rest;


import dto.LoginRequest;
import dto.LogoutRequest;
import exception.AuctionException;
import entity.User;
import services.AuthBusinessService;

import javax.inject.Inject;

public class AuthRestServicesImpl implements AuthRestServices {

    @Inject
    private AuthBusinessService authBusinessService;

    @Override
    public User login(LoginRequest loginRequest) {
        User user = authBusinessService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null) {
            throw new AuctionException("Authetication problem: wrong username or password");
        }

        return user;
    }

    @Override
    public User logout(LogoutRequest logoutRequest) {
        return null;
    }
}
