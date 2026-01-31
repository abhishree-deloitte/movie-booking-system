package com.deloitte.moviebooking.user.model;

import jakarta.persistence.*;

/**
 * User entity represents an application user.
 *
 * This class is a POJO enhanced with JPA annotations,
 * which allows it to be mapped to a database table.
 *
 * Table name: users
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Primary key for the User table.
     * UUID is auto-generated to avoid manual ID handling.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    /**
     * Unique username used for login.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Email address of the user.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Encrypted password (BCrypt).
     * Plain text passwords are never stored.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Role of the user.
     * Possible values: USER, ADMIN
     */
    @Column(nullable = false)
    private String role;

    /**
     * Default constructor required by JPA.
     */
    protected User() {
    }

    /**
     * Constructor used when creating a new user.
     *
     * @param username unique username
     * @param email user email
     * @param password encrypted password
     * @param role role of the user (USER / ADMIN)
     */
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
