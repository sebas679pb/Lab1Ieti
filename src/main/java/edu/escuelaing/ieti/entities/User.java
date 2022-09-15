package edu.escuelaing.ieti.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.escuelaing.ieti.dto.UserDto;
import edu.escuelaing.ieti.enums.RoleEnum;

@Document
public class User {
    
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String lastName;
    private Date createdAt;
    private String passwordHash;
    private List<RoleEnum> roles = new ArrayList<RoleEnum>();
    
    public User() {
    }

    public User(String id, String name, String email, String lastName, Date createdAt, String passwordHash,
            RoleEnum rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.createdAt = createdAt;
        this.passwordHash = passwordHash;
        this.roles.add(rol);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto toDTO() {
        return new UserDto(id, name, email, lastName, createdAt.toString(), passwordHash);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

}
