package dto;

import entity.User;

public class UserDto {

    private Integer userId;

    private String username;

    private String photoPath;

    private Boolean isAdmin;

    public UserDto() {

    }

    public UserDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.photoPath = user.getPhotoPath();
        this.isAdmin = user.getAdmin();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
