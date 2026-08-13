package com.stringstack.talentos.controller;
import com.stringstack.talentos.dto.CalendraEvent.CalendarEventResponse;
import com.stringstack.talentos.dto.calendar.CalendarEventRequest;
import com.stringstack.talentos.service.CalendarEventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/calendar-events")
@RequiredArgsConstructor
public class CalendarEventController {

    private final CalendarEventService calendarEventService;

    // CREATE
    @PostMapping
    public ResponseEntity<CalendarEventResponse> createEvent(
            @Valid @RequestBody CalendarEventRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(calendarEventService.createEvent(request));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CalendarEventResponse>> getAllEvents() {

        return ResponseEntity.ok(
                calendarEventService.getAllEvents()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CalendarEventResponse> getEventById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                calendarEventService.getEventById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CalendarEventResponse> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody CalendarEventRequest request) {

        return ResponseEntity.ok(
                calendarEventService.updateEvent(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long id) {

        calendarEventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }

    // GET EVENTS BETWEEN DATES
    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarEventResponse>> getEventsBetween(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {

        return ResponseEntity.ok(
                calendarEventService.getEventsBetween(
                        start,
                        end
                )
        );
    }

    // GET EVENTS BY BATCH
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<CalendarEventResponse>>
    getEventsByBatch(
            @PathVariable Long batchId) {

        return ResponseEntity.ok(
                calendarEventService.getEventsByBatch(batchId)
        );
    }

    // GET EVENTS BY TRAINER
    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<CalendarEventResponse>>
    getEventsByTrainer(
            @PathVariable Long trainerId) {

        return ResponseEntity.ok(
                calendarEventService.getEventsByTrainer(trainerId)
        );
    }

    // GET EVENTS BY COMPANY
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<CalendarEventResponse>>
    getEventsByCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                calendarEventService.getEventsByCompany(companyId)
        );
    }
}