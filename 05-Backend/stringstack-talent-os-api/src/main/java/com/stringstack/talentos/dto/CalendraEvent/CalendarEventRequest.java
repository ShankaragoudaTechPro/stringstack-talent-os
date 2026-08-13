package com.stringstack.talentos.dto.calendar;

import com.stringstack.talentos.constants.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalendarEventRequest {

    @NotBlank(message = "Event title is required")
    private String title;

    private String description;

    @NotNull(message = "Event type is required")
    private EventType eventType;

    @NotNull(message = "Start date and time is required")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date and time is required")
    private LocalDateTime endDateTime;

    private String location;

    private Long batchId;

    private Long trainerId;

    private Long companyId;

    private Boolean reminderEnabled;

    private Integer reminderMinutesBefore;
}