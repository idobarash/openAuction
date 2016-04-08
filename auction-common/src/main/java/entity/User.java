package entity;

import javax.persistence.*;
import java.util.List;

/**
 * User entity
 *
 * Author: Ido Barash
 */
@Entity
@Table(name = "users")
public class User extends AbstractSoftDeletedEntity {

    private String username;

    private String password;

    private String email;

    private String name;

    private String phone;

    @Column(name = "photo_path")
    private String photoPath;

    @OneToMany()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Item> items;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
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
