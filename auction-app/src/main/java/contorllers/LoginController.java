package contorllers;

import services.AuthService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@ManagedBean(name = "loginController", eager = true)
public class LoginController {

    @Inject
    private AuthService authService;

    private static final String USERNAME_INITIAL = "Username";
    private static final String PASSWORD_INITIAL = "Password";

    private String username;

    private String password;

    private String message = "";


    public void login() {

        System.out.println("");

        if (username != null && password != null) {
            message = authService.login(username, password);
            if ("".equals(message)) {

            }
        }

    }

    public void logout() {
        System.out.println("");
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
}
