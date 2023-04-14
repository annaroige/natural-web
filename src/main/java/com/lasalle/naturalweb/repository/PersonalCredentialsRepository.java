package com.lasalle.naturalweb.repository;

import com.lasalle.naturalweb.entity.PersonalCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalCredentialsRepository extends JpaRepository<PersonalCredentials, String> {

    PersonalCredentials getPersonalCredentialsByUsername(String username);
}
