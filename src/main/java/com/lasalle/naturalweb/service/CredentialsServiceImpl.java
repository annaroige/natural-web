package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.LoginInput;
import com.lasalle.naturalweb.dto.LoginOutput;
import com.lasalle.naturalweb.entity.PersonalCredentials;
import com.lasalle.naturalweb.repository.PersonalCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private PersonalCredentialsRepository personalCredentialsRepository;
    @Override
    public LoginOutput login(LoginInput loginInput) {

        LoginOutput loginOutput = new LoginOutput();
        // buscar a la taula personalCredentials per mail
        PersonalCredentials credentials = personalCredentialsRepository
                .getPersonalCredentialsByUsername(loginInput.getUserName());
        if (null == credentials) {
            loginOutput.setAllow(Boolean.FALSE);
        } else {
            loginOutput.setAllow(Boolean.TRUE);
            loginOutput.setRole(credentials.getRole());
        }

        return loginOutput;
    }
}
