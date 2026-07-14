package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.course.CourseRequest;
import com.stringstack.talentos.dto.course.CourseResponse;
import com.stringstack.talentos.entity.Course;

public class CourseMapper {

    private CourseMapper(){}

    public static Course toEntity(CourseRequest request){

        return Course.builder()
                .courseCode(request.getCourseCode())
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .duration(request.getDuration())
                .fee(request.getFee())
                .level(request.getLevel())
                .active(request.getActive())
                .build();
    }

    public static CourseResponse toResponse(Course course){

        return CourseResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .duration(course.getDuration())
                .fee(course.getFee())
                .level(course.getLevel())
                .active(course.getActive())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

}