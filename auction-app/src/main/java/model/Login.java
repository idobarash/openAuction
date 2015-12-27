package model;

import entity.User;
import services.AuthServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private String message;

    private User user;

    /**
     * Login to the system
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        user = AuthServices.login(username, password);
        return getUser();
    }

    /**
     * Logout a user
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;

        return "login";
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}