package contorllers;

import dto.LoginRequest;
import dto.UserDto;
import services.RestUtil;
import services.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@SessionScoped
@ManagedBean(name = "loginController", eager = true)
public class LoginController {

    private static final String AUTHENTICATE_USER_URL = "/auth/login";

    public static final String USERNAME_INITIAL = "Username";
    public static final String PASSWORD_INITIAL = "Password";

    private String username;

    private String password;

    private String message = "";


    public void login() {

        System.out.println("");

        if (username != null && password != null) {

            LoginRequest request = new LoginRequest(username, password);

            try {

                UserDto userData = RestUtil.httpPost(AUTHENTICATE_USER_URL, UserDto.class, request);
                if (userData != null) {
                    SessionUtil.getSession().setAttribute(SessionUtil.USERNAME, userData.getUsername());
                    SessionUtil.getSession().setAttribute(SessionUtil.USER_IS_ADMIN, userData.getAdmin());
                    SessionUtil.getSession().setAttribute(SessionUtil.USER_PHOTO_PATH, userData.getPhotoPath());
                    SessionUtil.getSession().setAttribute(SessionUtil.USER_ID, userData.getUserId());

                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    ExternalContext externalContext = facesContext.getExternalContext();
                    externalContext.redirect("/auction-app");
                }

            } catch (Exception e) {
                e.printStackTrace();
                message = e.getMessage();
            }
        }
    }

    public void logout() {

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect("/auction-app");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        if (username == null) {
            return USERNAME_INITIAL;
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if (password == null) {
            return PASSWORD_INITIAL;
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLoggedIn() {
        return SessionUtil.getSessionAttribute(SessionUtil.USERNAME) != null;
    }

    public boolean isAdmin() {
        if (SessionUtil.getSession().getAttribute(SessionUtil.USER_IS_ADMIN) == null) {
           return false;
        }

        return (Boolean) SessionUtil.getSession().getAttribute(SessionUtil.USER_IS_ADMIN);
    }
}
