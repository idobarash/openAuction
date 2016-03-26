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
            if (userData != null) {
                SessionService.getSession().setAttribute(SessionService.USERNAME, userData.getUsername());
                SessionService.getSession().setAttribute(SessionService.USER_IS_ADMIN, userData.getAdmin());
                SessionService.getSession().setAttribute(SessionService.USER_PHOTO_PATH, userData.getPhotoPath());
            }

        } catch (Exception e) {
            return e.getMessage();
        }

        return "";
    }

    public void logoutCurrentUser() {
        SessionService.getSession().setAttribute(SessionService.USER_IS_ADMIN, null);
        SessionService.getSession().setAttribute(SessionService.USERNAME, null);
        SessionService.getSession().setAttribute(SessionService.USER_PHOTO_PATH, null);
    }

}
