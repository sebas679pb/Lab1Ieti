package edu.escuelaing.ieti.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.security.crypto.bcrypt.BCrypt;

import edu.escuelaing.ieti.entities.User;
import edu.escuelaing.ieti.enums.RoleEnum;

public class UserDto {
    
    private String id;
    private String name;
    private String email;
    private String lastName;
    private String createdAt;
    private String password;
    
    public UserDto() {
    }

    public UserDto(String id, String name, String email, String lastName, String createdAt, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public User toEntity() {
        try {
            return new User(id, name, email, lastName, new SimpleDateFormat("dd/MM/yyyy").parse(createdAt),
                    BCrypt.hashpw(password, BCrypt.gensalt()), RoleEnum.USER);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
