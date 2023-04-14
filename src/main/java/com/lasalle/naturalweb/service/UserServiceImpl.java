package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.entity.User;
import com.lasalle.naturalweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUser(String dni) {
        return userRepository.getUserByDNI(dni);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String dni) {
        User user = userRepository.getUserByDNI(dni);
        userRepository.delete(user);
    }
}
