package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.CalendraEvent.CalendarEventResponse;
import com.stringstack.talentos.dto.calendar.CalendarEventRequest;


import java.time.LocalDateTime;
import java.util.List;

public interface CalendarEventService {

    CalendarEventResponse createEvent(
            CalendarEventRequest request
    );

    List<CalendarEventResponse> getAllEvents();

    CalendarEventResponse getEventById(Long id);

    CalendarEventResponse updateEvent(
            Long id,
            CalendarEventRequest request
    );

    void deleteEvent(Long id);

    List<CalendarEventResponse> getEventsBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<CalendarEventResponse> getEventsByBatch(
            Long batchId
    );

    List<CalendarEventResponse> getEventsByTrainer(
            Long trainerId
    );

    List<CalendarEventResponse> getEventsByCompany(
            Long companyId
    );

    void processUpcomingReminders();

}