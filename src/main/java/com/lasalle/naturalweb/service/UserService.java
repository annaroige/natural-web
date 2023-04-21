package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.ReservationInput;
import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.User;

import java.util.List;

public interface UserService {

    public User getUser(String dni);

    public User getUserByEmail(String email);

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(String dni);

    public void createReservation(ReservationInput reservation);


    List<ReservationOutput> getReservation(String userDni);

    List<ReservationOutput> getHistoricReservation(String userDni);
}
