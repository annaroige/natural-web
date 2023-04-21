package com.lasalle.naturalweb.repository;


import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> getAllByTherapist_DNIAndDateAfterOrderByDate(String dni, LocalDateTime date);

    List<Reservation> getAllByUser_DNIAndDateAfterOrderByDate(String dni, LocalDateTime date);

    List<Reservation> getAllByTherapist_DNIAndDateBeforeOrderByDate(String dni, LocalDateTime date);

    List<Reservation> getAllByUser_DNIAndDateBeforeOrderByDate(String dni, LocalDateTime date);
}
