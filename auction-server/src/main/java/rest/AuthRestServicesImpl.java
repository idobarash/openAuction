package rest;


import dto.LoginRequest;
import dto.LogoutRequest;
import dto.UserDto;
import exception.AuctionException;
import entity.User;
import utils.AuthBusinessService;

import javax.inject.Inject;

public class AuthRestServicesImpl implements AuthRestServices {

    @Inject
    private AuthBusinessService authBusinessService;

    @Override
    public UserDto login(LoginRequest loginRequest) {
        User user = authBusinessService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null) {
            throw new AuctionException("Authetication problem: wrong username or password");
        }

        return new UserDto(user);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        return ;
    }
}
