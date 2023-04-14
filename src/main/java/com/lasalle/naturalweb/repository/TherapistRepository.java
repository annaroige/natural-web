package com.lasalle.naturalweb.repository;


import com.lasalle.naturalweb.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistRepository extends JpaRepository<Therapist, String> {

    Therapist getTherapistByEmail(String email);
    Therapist getTherapistByDNI(String dni);
}
