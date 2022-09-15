package edu.escuelaing.ieti.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.entities.User;
import edu.escuelaing.ieti.repository.UserRepository;

@Service
public class UserServiceMongoDB implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, String id) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        return userRepository.findUsersWithNameOrLastNameLike(queryText);
    }

    @Override
    public List<User> findUsersCreatedAfter(Date startDate) {
        return userRepository.findUsersCreatedAfter(startDate);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    
    
}
