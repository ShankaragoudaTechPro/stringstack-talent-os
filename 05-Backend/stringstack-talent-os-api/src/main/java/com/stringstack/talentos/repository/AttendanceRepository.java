package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsByAttendanceCode(String attendanceCode);

    Optional<Attendance> findByAttendanceCode(String attendanceCode);

}