package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.entity.Therapist;

public interface TherapistService {

    public Therapist getTherapist(String dni);

    public Therapist getTherapistByEmail(String email);

    public void createTherapist(Therapist therapist);

    public void updateTherapist(Therapist therapist);

    public void deleteTherapist(String dni);
}
