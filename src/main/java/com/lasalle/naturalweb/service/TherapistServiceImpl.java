package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.Disponibility;
import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.dto.ScheduleInput;
import com.lasalle.naturalweb.entity.Reservation;
import com.lasalle.naturalweb.entity.Schedule;
import com.lasalle.naturalweb.entity.Therapist;
import com.lasalle.naturalweb.repository.ReservationRepository;
import com.lasalle.naturalweb.repository.ScheduleRepository;
import com.lasalle.naturalweb.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
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
    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public Therapist getTherapist(String dni) {
        return therapistRepository.getTherapistByDNI(dni);
    }

    @Override
    public List<Therapist> getTherapists() {
        return therapistRepository.findAll();
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

    @Override
    public void createSchedule(ScheduleInput scheduleInput) {
        Therapist therapist = therapistRepository.getTherapistByDNI(scheduleInput.getTherapistDni());
        therapist.setScheduleList(scheduleInput.getScheduleList());

        therapistRepository.save(therapist);
    }

    @Override
    public List<Schedule> getSchedule(String therapistDni) {
        Therapist therapist = therapistRepository.getTherapistByDNI(therapistDni);
        List<Schedule> scheduleList = scheduleRepository.getAllByTherapistListContains(therapist);

        return scheduleList;
    }

    @Override
    public List<Disponibility> getDisponibility(String therapistDni) {

        LocalDateTime nowRounded = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime inThreeMonths = nowRounded.plusMonths(3);
        List<Disponibility> disponibilityList = new ArrayList<>();
        for (LocalDateTime i = nowRounded; i.isBefore(inThreeMonths); i.plusHours(1L)) {
            if (i.getDayOfWeek() != DayOfWeek.SATURDAY || i.getDayOfWeek() != DayOfWeek.SUNDAY || (i.getHour() >= 9 && i.getHour() < 21)) {
                Disponibility disponibility = new Disponibility();
                disponibility.setDate(i.toLocalDate());
                disponibility.setTime(i.toLocalTime());
                boolean available = checkAvailability(i, therapistDni);
                disponibility.setDisponible(available);
                disponibilityList.add(disponibility);

            }
        }


        return disponibilityList;
    }


    private boolean checkAvailability(LocalDateTime dateTime, String therapistDni) {

        List<Schedule> scheduleList = getSchedule(therapistDni);
        LocalDateTime now = LocalDateTime.now();
        List<Reservation> reservationList = reservationRepository.getAllByTherapist_DNIAndDateAfterOrderByDate(therapistDni, now);
        boolean available = false;
        if (scheduleList.stream().anyMatch(s -> (s.getDayOfWeek() == (dateTime.getDayOfWeek().getValue()) && s.getSession() == dateTime.getHour()))
            && !reservationList.stream().anyMatch(r -> r.getDate().isEqual(dateTime))) {
            available = true;
        }


        return available;

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
