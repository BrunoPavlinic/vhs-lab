package com.example.vhs.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "User id must not be null")
    @PositiveOrZero(message = "User id must be a positive integer or 0")
    private int userId;

    @Column(nullable = false, unique = true)
    @NotNull(message = " Username must not be null")
    private String userName;

    @Column(nullable = false)
    @NotNull(message = "Password must not be null")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Active must not be null")
    private boolean active;

    private String roles;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + userId;
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + String.valueOf(active).hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return user.userId == userId &&
                user.userName.equals(userName) &&
                user.password.equals((password)) &&
                String.valueOf(user.active).equals(String.valueOf(active)) &&
                user.roles.equals(roles);
    }

    @Override
    public String toString() {
        return String.format("User id - %d, Username: %s, Password: %s, Is active - %s, Roles - %s", userId, userName, password, String.valueOf(active), roles);
    }
}