package com.service.course;

public record CourseRegistrationRequest(
        String courseName,
        String durationTime,
        String description
) {
}
