package edu.escuelaing.ieti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.entities.User;

@Service
public class UserServiceHashMap implements UserService {
    
    private HashMap<String, User> userHash = new HashMap<>();

    @Override
    public User create(User user) {
        if (userHash.containsKey(user.getId())) {
            return null;
        }
        userHash.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) {
        return userHash.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userHash.values().forEach(users::add);
        return users;
    }

    @Override
    public void deleteById(String id) {
        if (userHash.containsKey(id)) {
            userHash.remove(id);
        }
    }

    @Override
    public User update(User user, String userId) {
        if (!userHash.containsKey(userId)) {
            return null;
        }
        deleteById(userId);
        return create(user);
    }

}
