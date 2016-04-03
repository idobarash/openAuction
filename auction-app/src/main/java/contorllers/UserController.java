package contorllers;

import services.UserServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@RequestScoped
@ManagedBean(name = "userController", eager = false)
public class UserController extends BasicController {

    private static final String TITLE_REGISTER = "Regsiter a new user";
    private static final String TITLE_UPDATE = "Update user properties";

    @Inject
    private UserServices userServices;

    private String username;

    private String password;

    private String passwordConfirm;

    private String email;

    public String getLegendTitle() {

        String mode = (String) getRequestParameter("mode");
        if ("register".equals(mode.toLowerCase())) {
            return TITLE_REGISTER;
        }

        return TITLE_UPDATE;
    }


    public void regiterNewUser() {

        userServices.createUser(username, password, email);

    }

    public String getUsername() {
        return username;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
