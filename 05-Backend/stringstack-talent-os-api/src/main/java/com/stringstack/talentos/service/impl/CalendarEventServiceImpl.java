package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.constants.EventStatus;
import com.stringstack.talentos.dto.CalendraEvent.CalendarEventResponse;
import com.stringstack.talentos.dto.calendar.CalendarEventRequest;
import com.stringstack.talentos.entity.Batch;
import com.stringstack.talentos.entity.CalendarEvent;
import com.stringstack.talentos.entity.Company;
import com.stringstack.talentos.entity.Trainer;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.BatchRepository;
import com.stringstack.talentos.repository.CalendarEventRepository;
import com.stringstack.talentos.repository.CompanyRepository;
import com.stringstack.talentos.repository.TrainerRepository;
import com.stringstack.talentos.service.CalendarEventService;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarEventServiceImpl
        implements CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    private final BatchRepository batchRepository;

    private final TrainerRepository trainerRepository;

    private final CompanyRepository companyRepository;


    // =====================================================
    // CREATE EVENT
    // =====================================================

    @Override
    public CalendarEventResponse createEvent(
            CalendarEventRequest request) {

        // 1. Validate date/time FIRST
        validateDateTime(
                request.getStartDateTime(),
                request.getEndDateTime()
        );

        // 2. Validate reminder
        validateReminder(request);

        // 3. Check trainer conflict
        if (request.getTrainerId() != null &&
                calendarEventRepository.existsTrainerConflict(
                        request.getTrainerId(),
                        request.getStartDateTime(),
                        request.getEndDateTime(),
                        null)) {

            throw new IllegalStateException(
                    "Trainer already has another event during this time"
            );
        }

        // 4. Check batch conflict
        if (request.getBatchId() != null &&
                calendarEventRepository.existsBatchConflict(
                        request.getBatchId(),
                        request.getStartDateTime(),
                        request.getEndDateTime(),
                        null)) {

            throw new IllegalStateException(
                    "Batch already has another event during this time"
            );
        }

        // 5. Get Batch
        Batch batch = null;

        if (request.getBatchId() != null) {

            batch =
                    batchRepository.findById(
                                    request.getBatchId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Batch not found with id: "
                                                    + request.getBatchId()
                                    ));
        }

        // 6. Get Trainer
        Trainer trainer = null;

        if (request.getTrainerId() != null) {

            trainer =
                    trainerRepository.findById(
                                    request.getTrainerId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Trainer not found with id: "
                                                    + request.getTrainerId()
                                    ));
        }

        // 7. Get Company
        Company company = null;

        if (request.getCompanyId() != null) {

            company =
                    companyRepository.findById(
                                    request.getCompanyId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Company not found with id: "
                                                    + request.getCompanyId()
                                    ));
        }

        // 8. Create event
        CalendarEvent event =
                CalendarEvent.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .eventType(request.getEventType())
                        .startDateTime(
                                request.getStartDateTime())
                        .endDateTime(
                                request.getEndDateTime())
                        .location(request.getLocation())
                        .batch(batch)
                        .trainer(trainer)
                        .company(company)
                        .status(EventStatus.SCHEDULED)
                        .reminderEnabled(
                                request.getReminderEnabled() != null
                                        ? request.getReminderEnabled()
                                        : false
                        )
                        .reminderMinutesBefore(
                                request.getReminderMinutesBefore())
                        .build();

        CalendarEvent saved =
                calendarEventRepository.save(event);

        return mapToResponse(saved);
    }


    // =====================================================
    // GET ALL EVENTS
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<CalendarEventResponse> getAllEvents() {

        return calendarEventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =====================================================
    // GET EVENT BY ID
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public CalendarEventResponse getEventById(
            Long id) {

        CalendarEvent event =
                calendarEventRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Calendar event not found with id: "
                                                + id
                                ));

        return mapToResponse(event);
    }


    // =====================================================
    // UPDATE EVENT
    // =====================================================

    @Override
    public CalendarEventResponse updateEvent(
            Long id,
            CalendarEventRequest request) {

        // 1. Validate date/time
        validateDateTime(
                request.getStartDateTime(),
                request.getEndDateTime()
        );

        // 2. Validate reminder
        validateReminder(request);

        // 3. Get existing event
        CalendarEvent event =
                calendarEventRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Calendar event not found with id: "
                                                + id
                                ));

        // 4. Check trainer conflict
        if (request.getTrainerId() != null &&
                calendarEventRepository.existsTrainerConflict(
                        request.getTrainerId(),
                        request.getStartDateTime(),
                        request.getEndDateTime(),
                        id)) {

            throw new IllegalStateException(
                    "Trainer already has another event during this time"
            );
        }

        // 5. Check batch conflict
        if (request.getBatchId() != null &&
                calendarEventRepository.existsBatchConflict(
                        request.getBatchId(),
                        request.getStartDateTime(),
                        request.getEndDateTime(),
                        id)) {

            throw new IllegalStateException(
                    "Batch already has another event during this time"
            );
        }

        // 6. Get Batch
        Batch batch = null;

        if (request.getBatchId() != null) {

            batch =
                    batchRepository.findById(
                                    request.getBatchId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Batch not found with id: "
                                                    + request.getBatchId()
                                    ));
        }

        // 7. Get Trainer
        Trainer trainer = null;

        if (request.getTrainerId() != null) {

            trainer =
                    trainerRepository.findById(
                                    request.getTrainerId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Trainer not found with id: "
                                                    + request.getTrainerId()
                                    ));
        }

        // 8. Get Company
        Company company = null;

        if (request.getCompanyId() != null) {

            company =
                    companyRepository.findById(
                                    request.getCompanyId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Company not found with id: "
                                                    + request.getCompanyId()
                                    ));
        }

        // 9. Update fields
        event.setTitle(request.getTitle());

        event.setDescription(
                request.getDescription());

        event.setEventType(
                request.getEventType());

        event.setStartDateTime(
                request.getStartDateTime());

        event.setEndDateTime(
                request.getEndDateTime());

        event.setLocation(
                request.getLocation());

        event.setBatch(batch);

        event.setTrainer(trainer);

        event.setCompany(company);

        event.setReminderEnabled(
                request.getReminderEnabled() != null
                        ? request.getReminderEnabled()
                        : false
        );

        event.setReminderMinutesBefore(
                request.getReminderMinutesBefore());

        CalendarEvent updated =
                calendarEventRepository.save(event);

        return mapToResponse(updated);
    }


    // =====================================================
    // DELETE EVENT
    // =====================================================

    @Override
    public void deleteEvent(Long id) {

        CalendarEvent event =
                calendarEventRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Calendar event not found with id: "
                                                + id
                                ));

        calendarEventRepository.delete(event);
    }


    // =====================================================
    // GET EVENTS BETWEEN DATES
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<CalendarEventResponse> getEventsBetween(
            LocalDateTime start,
            LocalDateTime end) {

        validateDateTime(start, end);

        return calendarEventRepository
                .findByStartDateTimeBetweenOrderByStartDateTimeAsc(
                        start,
                        end
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =====================================================
    // GET EVENTS BY BATCH
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<CalendarEventResponse> getEventsByBatch(
            Long batchId) {

        if (!batchRepository.existsById(batchId)) {

            throw new ResourceNotFoundException(
                    "Batch not found with id: "
                            + batchId
            );
        }

        return calendarEventRepository
                .findByBatchIdOrderByStartDateTimeAsc(batchId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =====================================================
    // GET EVENTS BY TRAINER
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<CalendarEventResponse> getEventsByTrainer(
            Long trainerId) {

        if (!trainerRepository.existsById(trainerId)) {

            throw new ResourceNotFoundException(
                    "Trainer not found with id: "
                            + trainerId
            );
        }

        return calendarEventRepository
                .findByTrainerIdOrderByStartDateTimeAsc(
                        trainerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =====================================================
    // GET EVENTS BY COMPANY
    // =====================================================

    @Override
    @Transactional(readOnly = true)
    public List<CalendarEventResponse> getEventsByCompany(
            Long companyId) {

        if (!companyRepository.existsById(companyId)) {

            throw new ResourceNotFoundException(
                    "Company not found with id: "
                            + companyId
            );
        }

        return calendarEventRepository
                .findByCompanyIdOrderByStartDateTimeAsc(
                        companyId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void processUpcomingReminders() {

        LocalDateTime now =
                LocalDateTime.now();

        LocalDateTime reminderWindow =
                now.plusMinutes(60);

        List<CalendarEvent> events =
                calendarEventRepository
                        .findEventsForReminder(
                                now,
                                reminderWindow
                        );

        for (CalendarEvent event : events) {

            if (event.getReminderMinutesBefore()
                    == null) {
                continue;
            }

            LocalDateTime reminderTime =
                    event.getStartDateTime()
                            .minusMinutes(
                                    event.getReminderMinutesBefore()
                            );

            /*
             * Only process when the reminder time
             * has arrived.
             */
            if (!now.isBefore(reminderTime)) {

                System.out.println(
                        "CALENDAR REMINDER: "
                                + event.getTitle()
                                + " starts at "
                                + event.getStartDateTime()
                );

                event.setReminderSent(true);

                calendarEventRepository.save(event);
            }
        }
    }


    // =====================================================
    // DATE VALIDATION
    // =====================================================

    private void validateDateTime(
            LocalDateTime start,
            LocalDateTime end) {

        if (start == null || end == null) {

            throw new IllegalArgumentException(
                    "Start date and end date are required"
            );
        }

        if (!end.isAfter(start)) {

            throw new IllegalArgumentException(
                    "End date and time must be after start date and time"
            );
        }
    }


    // =====================================================
    // REMINDER VALIDATION
    // =====================================================

    private void validateReminder(
            CalendarEventRequest request) {

        Boolean reminderEnabled =
                request.getReminderEnabled();

        Integer reminderMinutes =
                request.getReminderMinutesBefore();

        if (Boolean.TRUE.equals(reminderEnabled)) {

            if (reminderMinutes == null ||
                    reminderMinutes <= 0) {

                throw new IllegalArgumentException(
                        "Reminder minutes must be greater than zero when reminder is enabled"
                );
            }
        }

        if (Boolean.FALSE.equals(reminderEnabled)
                && reminderMinutes != null
                && reminderMinutes < 0) {

            throw new IllegalArgumentException(
                    "Reminder minutes cannot be negative"
            );
        }
    }


    // =====================================================
    // MAP ENTITY TO RESPONSE
    // =====================================================

    private CalendarEventResponse mapToResponse(
            CalendarEvent event) {

        return CalendarEventResponse.builder()

                .id(event.getId())

                .title(event.getTitle())

                .description(event.getDescription())

                .eventType(event.getEventType())

                .startDateTime(
                        event.getStartDateTime())

                .endDateTime(
                        event.getEndDateTime())

                .location(event.getLocation())

                .batchId(
                        event.getBatch() != null
                                ? event.getBatch().getId()
                                : null
                )

                .trainerId(
                        event.getTrainer() != null
                                ? event.getTrainer().getId()
                                : null
                )

                .companyId(
                        event.getCompany() != null
                                ? event.getCompany().getId()
                                : null
                )

                .status(event.getStatus())

                .reminderEnabled(
                        event.getReminderEnabled())

                .reminderMinutesBefore(
                        event.getReminderMinutesBefore())

                .build();
    }
}