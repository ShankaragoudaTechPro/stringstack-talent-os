package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.course.CourseRequest;
import com.stringstack.talentos.dto.course.CourseResponse;
import com.stringstack.talentos.entity.Course;
import com.stringstack.talentos.exception.DuplicateResourceException;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.CourseMapper;
import com.stringstack.talentos.repository.CourseRepository;
import com.stringstack.talentos.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse createCourse(CourseRequest request) {

        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new DuplicateResourceException("Course Code already exists.");
        }

        if (courseRepository.existsByCourseName(request.getCourseName())) {
            throw new DuplicateResourceException("Course Name already exists.");
        }

        Course course = CourseMapper.toEntity(request);

        Course savedCourse = courseRepository.save(course);

        return CourseMapper.toResponse(savedCourse);
    }

    @Override
    public List<CourseResponse> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        return CourseMapper.toResponse(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest request) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setFee(request.getFee());
        course.setLevel(request.getLevel());
        course.setActive(request.getActive());

        Course updatedCourse = courseRepository.save(course);

        return CourseMapper.toResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found."));

        courseRepository.delete(course);
    }
}