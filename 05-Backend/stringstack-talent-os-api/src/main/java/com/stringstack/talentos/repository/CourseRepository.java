package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long>{

    boolean existsByCourseCode(String courseCode);

    boolean existsByCourseName(String courseName);

}