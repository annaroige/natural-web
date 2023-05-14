package com.lasalle.naturalweb.controller;

import com.lasalle.naturalweb.dto.Disponibility;
import com.lasalle.naturalweb.dto.DisponibilityInput;
import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.dto.ScheduleInput;
import com.lasalle.naturalweb.entity.Schedule;
import com.lasalle.naturalweb.entity.Therapist;
import com.lasalle.naturalweb.entity.User;
import com.lasalle.naturalweb.service.TherapistService;
import com.lasalle.naturalweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("api/v1/therapist")
public class TherapistController {

    @Autowired
    TherapistService therapistService;

    @GetMapping("/")
    public Object getTherapists () {

        try {
            List<Therapist> therapistList = therapistService.getTherapists();
            return ResponseEntity.status(HttpStatus.OK).body(therapistList);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("create")
    public Object create (@RequestBody Therapist therapist) {

        try {
            therapistService.createTherapist(therapist);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("schedule/create")
    public Object createSchedule (@RequestBody ScheduleInput scheduleInput) {

        try {
            therapistService.createSchedule(scheduleInput);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("{therapistDni}/schedule")
    public Object getSchedule (@PathVariable String therapistDni) {

        try {
            List<Schedule> schedules = therapistService.getSchedule(therapistDni);
            return ResponseEntity.status(HttpStatus.OK).body(schedules);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("{therapistDni}/daysoff")
    public Object getDaysOff (@PathVariable String therapistDni) {

        try {
            List<Integer> daysOff = therapistService.getDaysOff(therapistDni);
            return ResponseEntity.status(HttpStatus.OK).body(daysOff);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("disponibility")
    public Object getDisponibility (@RequestBody DisponibilityInput disponibilityInput) {

        try {
            List<Disponibility> disponibility = therapistService.getDisponibility(disponibilityInput);
            return ResponseEntity.status(HttpStatus.OK).body(disponibility);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("{therapistDni}/reservation")
    public Object getReservation (@PathVariable String therapistDni) {

        try {
            List<ReservationOutput> reservation = therapistService.getReservation(therapistDni);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("{therapistDni}/reservation/historic")
    public Object getHistoricReservation (@PathVariable String therapistDni) {

        try {
            List<ReservationOutput> reservation = therapistService.getHistoricReservation(therapistDni);
            return ResponseEntity.status(HttpStatus.OK).body(reservation);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
