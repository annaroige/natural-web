package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.ReservationInput;
import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.entity.PersonalCredentials;
import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.Therapist;
import com.lasalle.naturalweb.entity.User;
import com.lasalle.naturalweb.repository.PersonalCredentialsRepository;
import com.lasalle.naturalweb.repository.ReservationRepository;
import com.lasalle.naturalweb.repository.TherapistRepository;
import com.lasalle.naturalweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonalCredentialsRepository credentialsRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TherapistRepository therapistRepository;


    @Override
    public User getUser(String dni) {
        return userRepository.getUserByDni(dni);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void createUser(User user) {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new NullPointerException("Password error");
        }
        PersonalCredentials personalCredentials = new PersonalCredentials();
        personalCredentials.setDNI(user.getDni());
        personalCredentials.setRole("user");
        personalCredentials.setUsername(user.getEmail());
        personalCredentials.setPassword(user.getPassword());

        credentialsRepository.save(personalCredentials);
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String dni) {
        User user = userRepository.getUserByDni(dni);
        userRepository.delete(user);
    }

    @Override
    public void createReservation(ReservationInput reservationInput) {
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Reservation reservation = new Reservation();
        User user = userRepository.getUserByDni(reservationInput.getUserDNI());
        Therapist therapist = therapistRepository.getTherapistByDni(reservationInput.getTherapistDNI());
        LocalDateTime localDateTime = LocalDateTime.parse(reservationInput.getDate(), CUSTOM_FORMATTER);
        reservation.setUser(user);
        reservation.setTherapist(therapist);
        reservation.setDate(localDateTime);

        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationOutput> getReservation(String userDni) {
        LocalDateTime date = LocalDateTime.now();

        List<ReservationOutput> reservationOutputList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.getAllByUser_DniAndDateAfterOrderByDate(userDni, date);
        customReservation(reservationOutputList, reservationList);

        return reservationOutputList;
    }

    @Override
    public List<ReservationOutput> getHistoricReservation(String userDni) {
        LocalDateTime date = LocalDateTime.now();

        List<ReservationOutput> reservationOutputList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.getAllByUser_DniAndDateBeforeOrderByDate(userDni, date);
        customReservation(reservationOutputList, reservationList);

        return reservationOutputList;
    }

    private void customReservation(List<ReservationOutput> reservationOutputList, List<Reservation> reservationList) {
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (Reservation reservation : reservationList) {
            ReservationOutput reservationOutput = new ReservationOutput();
            reservationOutput.setUserName(reservation.getUser().getName() + " " + reservation.getUser().getSurname1());
            reservationOutput.setTherapistName(reservation.getTherapist().getName() + " " + reservation.getTherapist().getSurname1());
            LocalDateTime dateInput = reservation.getDate();
            String formattedString = dateInput.format(CUSTOM_FORMATTER);
            reservationOutput.setDate(formattedString);

            reservationOutputList.add(reservationOutput);
        }
    }
}
