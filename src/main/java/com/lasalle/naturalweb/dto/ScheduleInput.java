package com.lasalle.naturalweb.dto;

import com.lasalle.naturalweb.entity.Schedule;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleInput {

    private String therapistDni;
    private List<Schedule> scheduleList;
}
