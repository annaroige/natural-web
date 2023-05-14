package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.dto.Disponibility;
import com.lasalle.naturalweb.dto.DisponibilityInput;
import com.lasalle.naturalweb.dto.ReservationOutput;
import com.lasalle.naturalweb.dto.ScheduleInput;
import com.lasalle.naturalweb.entity.Schedule;
import com.lasalle.naturalweb.entity.Therapist;

import java.util.List;

public interface TherapistService {

    public Therapist getTherapist(String dni);

    public Therapist getTherapistByEmail(String email);

    public void createTherapist(Therapist therapist);

    public void updateTherapist(Therapist therapist);

    public void deleteTherapist(String dni);

    List<ReservationOutput> getReservation(String therapistDni);

    List<ReservationOutput> getHistoricReservation(String therapistDni);

    void createSchedule(ScheduleInput scheduleInput);

    List<Schedule> getSchedule(String therapistDni);

    List<Disponibility> getDisponibility(DisponibilityInput disponibilityInput);

    List<Therapist> getTherapists();

    List<Integer> getDaysOff(String therapistDni);

}
