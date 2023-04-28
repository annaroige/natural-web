package com.lasalle.naturalweb.repository;


import com.lasalle.naturalweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email);
    User getUserByDni(String dni);
}
