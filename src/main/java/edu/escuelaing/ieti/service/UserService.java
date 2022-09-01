package edu.escuelaing.ieti.service;

import java.util.List;

import edu.escuelaing.ieti.entities.User;

public interface UserService {
    
    User create(User user);

    User findById(String id);

    List<User> getAll();

    void deleteById(String id);

    User update(User user, String userId);
    
}
