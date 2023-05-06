package com.lasalle.naturalweb.controller;

import com.lasalle.naturalweb.dto.LoginInput;
import com.lasalle.naturalweb.dto.LoginOutput;
import com.lasalle.naturalweb.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@CrossOrigin
public class CredentialsController {

    @Autowired
    CredentialsService credentialsService;

    @PostMapping("login")
    public Object login(@RequestBody LoginInput loginInput) {

        try {
            LoginOutput loginOutput = credentialsService.login(loginInput);
            return loginOutput;
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
