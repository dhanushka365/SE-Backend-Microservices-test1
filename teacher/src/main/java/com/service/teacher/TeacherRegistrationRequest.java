package com.service.teacher;

public record TeacherRegistrationRequest (
        String firstName,
        String lastName,
        String email,
        String department
){ }
