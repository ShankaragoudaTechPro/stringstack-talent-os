package com.stringstack.talentos.dto.CalendraEvent;

import com.stringstack.talentos.constants.EventStatus;
import com.stringstack.talentos.constants.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CalendarEventResponse {

    private Long id;

    private String title;

    private String description;

    private EventType eventType;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String location;

    private Long batchId;

    private Long trainerId;

    private Long companyId;

    private EventStatus status;

    private Boolean reminderEnabled;

    private Integer reminderMinutesBefore;
}