package com.stringstack.talentos.dto.course;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {

    @NotBlank
    private String courseCode;

    @NotBlank
    private String courseName;

    private String description;

    @NotNull
    private Integer duration;

    @NotNull
    private Double fee;

    @NotBlank
    private String level;

    @NotNull
    private Boolean active;
}