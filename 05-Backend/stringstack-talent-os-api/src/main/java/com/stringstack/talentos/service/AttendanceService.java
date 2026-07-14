package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.attendance.AttendanceRequest;
import com.stringstack.talentos.dto.attendance.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse createAttendance(AttendanceRequest request);

    List<AttendanceResponse> getAllAttendances();

    AttendanceResponse getAttendanceById(Long id);

    AttendanceResponse updateAttendance(Long id,
                                        AttendanceRequest request);

    void deleteAttendance(Long id);

}