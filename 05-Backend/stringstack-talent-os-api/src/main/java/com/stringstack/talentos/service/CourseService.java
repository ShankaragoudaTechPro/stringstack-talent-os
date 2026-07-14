package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.course.CourseRequest;
import com.stringstack.talentos.dto.course.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CourseRequest request);

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    CourseResponse updateCourse(Long id, CourseRequest request);

    void deleteCourse(Long id);

}