package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.attendance.AttendanceRequest;
import com.stringstack.talentos.dto.attendance.AttendanceResponse;
import com.stringstack.talentos.entity.Attendance;
import com.stringstack.talentos.entity.Enrollment;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.AttendanceMapper;
import com.stringstack.talentos.repository.AttendanceRepository;
import com.stringstack.talentos.repository.EnrollmentRepository;
import com.stringstack.talentos.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public AttendanceResponse createAttendance(AttendanceRequest request) {

        if (attendanceRepository.existsByAttendanceCode(request.getAttendanceCode())) {
            throw new DuplicateResourceException("Attendance Code already exists.");
        }

        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found."));

        Attendance attendance = AttendanceMapper.toEntity(request);

        attendance.setEnrollment(enrollment);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        return AttendanceMapper.toResponse(savedAttendance);
    }

    @Override
    public List<AttendanceResponse> getAllAttendances() {

        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponse getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found."));

        return AttendanceMapper.toResponse(attendance);
    }

    @Override
    public AttendanceResponse updateAttendance(Long id,
                                               AttendanceRequest request) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found."));

        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found."));

        attendance.setAttendanceCode(request.getAttendanceCode());
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());
        attendance.setActive(request.getActive());
        attendance.setEnrollment(enrollment);

        Attendance updatedAttendance = attendanceRepository.save(attendance);

        return AttendanceMapper.toResponse(updatedAttendance);
    }

    @Override
    public void deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found."));

        attendanceRepository.delete(attendance);
    }
}