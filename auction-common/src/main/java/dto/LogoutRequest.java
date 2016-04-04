package dto;

/**
 * Wrapper class for Logout request
 *
 * Contains the username and the authtoken provided to him.
 * The auth is used for security.
 *
 * Author: Ido Barash
 */
public class LogoutRequest {

    private String username;

    private String authToken;

    public LogoutRequest(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
