package com.service.course;

public record CourseUpdateRequest(
        String courseName,
        String durationTime,
        String description
) {
}
