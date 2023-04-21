package com.lasalle.naturalweb.service;

import com.lasalle.naturalweb.entity.Therapist;
import com.lasalle.naturalweb.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TherapistServiceImpl implements TherapistService {

    @Autowired
    private TherapistRepository therapistRepository;


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
}
