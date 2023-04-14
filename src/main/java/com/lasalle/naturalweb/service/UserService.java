package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.entity.User;

public interface UserService {

    public User getUser(String dni);

    public User getUserByEmail(String email);

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(String dni);
}
