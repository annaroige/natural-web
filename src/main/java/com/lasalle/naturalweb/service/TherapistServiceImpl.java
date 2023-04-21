package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.Therapist;
import com.lasalle.naturalweb.repository.ReservationRepository;
import com.lasalle.naturalweb.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TherapistServiceImpl implements TherapistService {

    @Autowired
    private TherapistRepository therapistRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Therapist getTherapist(String dni) {
        return therapistRepository.getTherapistByDNI(dni);
    }

    @Override
    public Therapist getTherapistByEmail(String email) {
        return therapistRepository.getTherapistByEmail(email);
    }

    @Override
    public void createTherapist(Therapist therapist) {
        therapistRepository.save(therapist);
    }

    @Override
    public void updateTherapist(Therapist thera) {

        Therapist therapist = therapistRepository.getTherapistByDNI(thera.getDNI());
        therapistRepository.save(therapist);
    }

    @Override
    public void deleteTherapist(String dni) {
        Therapist therapist = therapistRepository.getTherapistByDNI(dni);
        therapistRepository.delete(therapist);
    }

    @Override
    public List<ReservationOutput> getReservation(String therapistDni) {
        LocalDateTime date = LocalDateTime.now();

        List<ReservationOutput> reservationOutputList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.getAllByTherapist_DNIAndDateAfterOrderByDate(therapistDni, date);
        customReservation(reservationOutputList, reservationList);

        return reservationOutputList;
    }

    @Override
    public List<ReservationOutput> getHistoricReservation(String therapistDni) {
        LocalDateTime date = LocalDateTime.now();

        List<ReservationOutput> reservationOutputList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.getAllByTherapist_DNIAndDateBeforeOrderByDate(therapistDni, date);
        customReservation(reservationOutputList, reservationList);

        return reservationOutputList;
    }

    private void customReservation(List<ReservationOutput> reservationOutputList, List<Reservation> reservationList) {
        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
