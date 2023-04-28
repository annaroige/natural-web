package com.lasalle.naturalweb.repository;


import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> getAllByTherapist_DniAndDateAfterOrderByDate(String dni, LocalDateTime date);

    List<Reservation> getAllByUser_DniAndDateAfterOrderByDate(String dni, LocalDateTime date);

    List<Reservation> getAllByTherapist_DniAndDateBeforeOrderByDate(String dni, LocalDateTime date);

    List<Reservation> getAllByUser_DniAndDateBeforeOrderByDate(String dni, LocalDateTime date);
}
