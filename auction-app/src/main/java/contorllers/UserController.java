package contorllers;

import services.MessagesDispatcher;
import services.UserServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 * Controller class which handles the User view (user.xhtml)
 *
 * Author: Ido Barash
 */
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

    private String name;

    private String phone;

    /**
     * Get the title accordinf to the mode
     * @return the title to display
     */
    public String getLegendTitle() {

        String mode = (String) getRequestParameter("mode");
        if ("register".equals(mode.toLowerCase())) {
            return TITLE_REGISTER;
        }

        return TITLE_UPDATE;
    }


    /**
     * Go to server to register a new user.
     */
    public void regiterNewUser() {

        if (username == null || username.isEmpty()) {
            MessagesDispatcher.dispatchMessage("Username is required", "Error");
            return;
        }

        if (name == null || name.isEmpty()) {
            MessagesDispatcher.dispatchMessage("Name is required", "Error");
            return;
        }

        if (email == null || email.isEmpty()) {
            MessagesDispatcher.dispatchMessage("Email is required", "Error");
            return;
        }

        if (phone == null || phone.isEmpty()) {
            MessagesDispatcher.dispatchMessage("Phone is required", "Error");
            return;
        }

        if (password == null || password.isEmpty()) {
            MessagesDispatcher.dispatchMessage("Password is required", "Error");
            return;
        }

        if (passwordConfirm == null || passwordConfirm.isEmpty()) {
            MessagesDispatcher.dispatchMessage("Password confirmation required", "Error");
            return;
        }

        if (password.equals(passwordConfirm) == false) {
            MessagesDispatcher.dispatchMessage("Password and password confirme does not match", "Error");
            return;
        }

        userServices.createUser(username, password, email, name, phone);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
