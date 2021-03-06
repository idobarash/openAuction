package contorllers;

import dto.LoginRequest;
import dto.UserDto;
import services.MessagesDispatcher;
import services.NavigationUtil;
import services.RestUtil;
import services.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Controller class which handles the login view (login.xhtml)
 *
 * Author: Ido Barash
 */
@SessionScoped
@ManagedBean(name = "loginController", eager = true)
public class LoginController extends BasicController {

    private static final String AUTHENTICATE_USER_URL = "/auth/login";

    private String username;

    private String password;

    private String message = "";


    /**
     * Login user
     */
    public void login() {

        if (username != null && password != null) {

            LoginRequest request = new LoginRequest(username, password);

            try {

                UserDto userData = RestUtil.httpPost(AUTHENTICATE_USER_URL, UserDto.class, request);
                if (userData != null) {
                    SessionUtil.getSession().setAttribute(SessionUtil.USERNAME, userData.getUsername());
                    SessionUtil.getSession().setAttribute(SessionUtil.USER_IS_ADMIN, userData.getAdmin());
                    SessionUtil.getSession().setAttribute(SessionUtil.USER_PHOTO_PATH, userData.getPhotoPath());
                    SessionUtil.getSession().setAttribute(SessionUtil.USER_ID, userData.getUserId());

                    NavigationUtil.navigateToRoot();
                }

            } catch (Exception e) {
                e.printStackTrace();
                MessagesDispatcher.dispatchMessage(e.getMessage(), "Login Error");
            }
        }
    }

    /**
     * Logout user
     */
    public void logout() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect("/auction-app");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Username label data
     * @return the username if was enterd, "Username" if not.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Check if user is logged in.
     * @return true if user is logged in
     */
    public boolean isLoggedIn() {
        return SessionUtil.getSessionAttribute(SessionUtil.USERNAME) != null;
    }

    /**
     * Check if user is administrator
     * @return true if user is administrator
     */
    public boolean isAdmin() {

        if (SessionUtil.getSession().getAttribute(SessionUtil.USER_IS_ADMIN) == null) {
            return false;
        }

        return (Boolean) SessionUtil.getSession().getAttribute(SessionUtil.USER_IS_ADMIN);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
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
}
