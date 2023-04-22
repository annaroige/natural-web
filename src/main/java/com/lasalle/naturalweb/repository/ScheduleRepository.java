package com.lasalle.naturalweb.repository;


import com.lasalle.naturalweb.entity.Schedule;
import com.lasalle.naturalweb.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> getAllByTherapistListContains(Therapist therapist);

}
