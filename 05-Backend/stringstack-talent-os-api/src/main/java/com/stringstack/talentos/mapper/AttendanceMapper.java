package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.attendance.AttendanceRequest;
import com.stringstack.talentos.dto.attendance.AttendanceResponse;
import com.stringstack.talentos.entity.Attendance;

public class AttendanceMapper {

    private AttendanceMapper() {
    }

    public static Attendance toEntity(AttendanceRequest request) {

        if (request == null) {
            return null;
        }

        return Attendance.builder()
                .attendanceCode(request.getAttendanceCode())
                .attendanceDate(request.getAttendanceDate())
                .status(request.getStatus())
                .remarks(request.getRemarks())
                .active(request.getActive())
                .build();
    }

    public static AttendanceResponse toResponse(Attendance attendance) {

        if (attendance == null) {
            return null;
        }

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .attendanceCode(attendance.getAttendanceCode())
                .enrollmentId(attendance.getEnrollment().getId())
                .studentName(
                        attendance.getEnrollment()
                                .getStudent()
                                .getFirstName()
                                + " "
                                + attendance.getEnrollment()
                                .getStudent()
                                .getLastName())
                .batchName(
                        attendance.getEnrollment()
                                .getBatch()
                                .getBatchName())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus())
                .remarks(attendance.getRemarks())
                .active(attendance.getActive())
                .createdAt(attendance.getCreatedAt())
                .updatedAt(attendance.getUpdatedAt())
                .build();
    }

}