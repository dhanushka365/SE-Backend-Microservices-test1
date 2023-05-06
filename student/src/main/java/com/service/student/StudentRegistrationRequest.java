package com.service.student;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Integer course_Id) {
}
