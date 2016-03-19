package services;

import dto.LoginRequest;
import dto.UserDto;
import entity.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final String AUTHENTICATE_USER = "/auth/login";

    public String login(String username, String password) {

        LoginRequest request = new LoginRequest(username, password);

        try {

            UserDto userData = RestUtil.httpPost(AUTHENTICATE_USER, UserDto.class, request);
            if (userData == null) {

            }

        } catch (Exception e) {
            return e.getMessage();
        }

        return "";
    }

}
