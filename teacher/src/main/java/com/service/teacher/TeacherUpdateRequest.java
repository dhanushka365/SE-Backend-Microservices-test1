package com.service.teacher;

public record TeacherUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String department,
        String roles,
        String  password
) {
}
