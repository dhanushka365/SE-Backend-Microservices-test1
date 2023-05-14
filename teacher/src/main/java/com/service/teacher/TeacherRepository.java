package com.service.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer > {

    Teacher findByEmail(String email);
}
