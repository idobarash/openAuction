package rest;


import dto.LoginRequest;
import dto.LogoutRequest;
import dto.UserDto;
import entity.User;
import exception.AuctionException;
import services.AuthBusinessService;

import javax.inject.Inject;

/**
 * Authentication REST service implementation.
 * Handles request-response operations for Authentication
 *
 * Author: Ido Barash
 */
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
