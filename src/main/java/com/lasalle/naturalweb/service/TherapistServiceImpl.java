package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.Disponibility;
import com.lasalle.naturalweb.dto.DisponibilityInput;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return therapistRepository.getTherapistByDni(dni);
    }

    @Override
    public List<Therapist> getTherapists() {
        return therapistRepository.findAll();
    }

    @Override
    public List<Integer> getDaysOff(String therapistDni) {

        List<Integer> weekDays = Stream.of(1,2,3,4,5).collect(Collectors.toList());
        List<Schedule> schedules = getSchedule(therapistDni);
        List<Integer> daysIn = schedules.stream().map(Schedule -> Schedule.getDayOfWeek()).collect(Collectors.toList());
        List<Integer> daysOff = weekDays.stream().filter(w -> !daysIn.contains(w)).collect(Collectors.toList());

        return daysOff;
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

        Therapist therapist = therapistRepository.getTherapistByDni(thera.getDni());
        therapistRepository.save(therapist);
    }

    @Override
    public void deleteTherapist(String dni) {
        Therapist therapist = therapistRepository.getTherapistByDni(dni);
        therapistRepository.delete(therapist);
    }

    @Override
    public List<ReservationOutput> getReservation(String therapistDni) {
        LocalDateTime date = LocalDateTime.now();

        List<ReservationOutput> reservationOutputList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.getAllByTherapist_DniAndDateAfterOrderByDate(therapistDni, date);
        customReservation(reservationOutputList, reservationList);

        return reservationOutputList;
    }

    @Override
    public List<ReservationOutput> getHistoricReservation(String therapistDni) {
        LocalDateTime date = LocalDateTime.now();

        List<ReservationOutput> reservationOutputList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.getAllByTherapist_DniAndDateBeforeOrderByDate(therapistDni, date);
        customReservation(reservationOutputList, reservationList);

        return reservationOutputList;
    }

    @Override
    public void createSchedule(ScheduleInput scheduleInput) {
        Therapist therapist = therapistRepository.getTherapistByDni(scheduleInput.getTherapistDni());
        for (Schedule schedule : scheduleInput.getScheduleList()) {
            schedule.setTherapist(therapist);
            scheduleRepository.save(schedule);
        }
        therapist.setScheduleList(scheduleInput.getScheduleList());

        therapistRepository.save(therapist);
    }

    @Override
    public List<Schedule> getSchedule(String therapistDni) {

        List<Schedule> scheduleList = scheduleRepository.getAllByTherapistDni(therapistDni);

        return scheduleList;
    }

    @Override
    public List<Disponibility> getDisponibility(DisponibilityInput disponibilityInput) {

        List<Disponibility> disponibilityList = new ArrayList<>();

        List<Schedule> scheduleList = getScheduleList(disponibilityInput);

        List<Reservation> reservationList = getReservationList(disponibilityInput);

        List<Integer> sessions = scheduleList.stream().map(schedule -> schedule.getSession()).collect(Collectors.toList());

        for (Integer session : sessions) {
            Disponibility disponibility = new Disponibility();

            if (!reservationList.stream().anyMatch(rl -> rl.getDate().getHour() == session)) {
                disponibility.setTime(LocalTime.of(session, 0));
                disponibility.setDisponible(true);
            }
            disponibilityList.add(disponibility);
        }

        return disponibilityList;
    }

    private List<Reservation> getReservationList(DisponibilityInput disponibilityInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate now = LocalDate.parse(disponibilityInput.getDate(), formatter);
        LocalTime midnight = LocalTime.of(00, 01);;
        LocalTime otherTime = LocalTime.of(23, 59);
        LocalDateTime from = LocalDateTime.of(now, midnight);
        LocalDateTime todayMidnight = LocalDateTime.of(LocalDate.from(now), otherTime);
        List<Reservation> reservationList = reservationRepository.getAllByTherapist_DniAndDateBetweenOrderByDate(disponibilityInput.getTherapistDNI(), from, todayMidnight);
        return reservationList;
    }

    private List<Schedule> getScheduleList(DisponibilityInput disponibilityInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        int dayOfWeek = (LocalDate.parse(disponibilityInput.getDate(), formatter)).getDayOfWeek().getValue();
        List<Schedule> scheduleList = scheduleRepository.getAllByTherapistDniAndDayOfWeek(disponibilityInput.getTherapistDNI(), dayOfWeek);
        return scheduleList;
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
