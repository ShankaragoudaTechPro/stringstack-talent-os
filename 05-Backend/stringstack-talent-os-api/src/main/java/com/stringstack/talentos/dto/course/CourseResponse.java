package com.stringstack.talentos.dto.course;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private Long id;

    private String courseCode;

    private String courseName;

    private String description;

    private Integer duration;

    private Double fee;

    private String level;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}