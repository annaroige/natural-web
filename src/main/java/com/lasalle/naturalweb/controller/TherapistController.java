package com.lasalle.naturalweb.controller;

import com.lasalle.naturalweb.entity.Therapist;
import com.lasalle.naturalweb.entity.User;
import com.lasalle.naturalweb.service.TherapistService;
import com.lasalle.naturalweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class TherapistController {

    @Autowired
    TherapistService therapistService;

    @PostMapping("therapist/create")
    public Object create (Therapist therapist) {

        try {
            therapistService.createTherapist(therapist);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //TODO: get schedule

    //TODO: reservations

}
