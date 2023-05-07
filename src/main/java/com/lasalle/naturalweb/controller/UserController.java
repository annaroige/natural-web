package com.lasalle.naturalweb.controller;

import com.lasalle.naturalweb.dto.ReservationInput;
import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.User;
import com.lasalle.naturalweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user/create")
    public Object create (@RequestBody User user) {

        try {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("user/reservation")
    public Object createReservation (@RequestBody ReservationInput reservation) {

        try {
            userService.createReservation(reservation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/user/{userDni}/reservation")
    public Object getReservation (@PathVariable String userDni) {

        try {
            List<ReservationOutput> reservation = userService.getReservation(userDni);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("user/{userDni}/reservation/historic")
    public Object getHistoricReservation (@PathVariable String userDni) {

        try {
            List<ReservationOutput> reservation = userService.getHistoricReservation(userDni);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
