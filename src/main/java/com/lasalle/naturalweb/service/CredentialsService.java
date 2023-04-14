package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.LoginInput;
import com.lasalle.naturalweb.dto.LoginOutput;

public interface CredentialsService {

    public LoginOutput login(LoginInput loginInput);
}
