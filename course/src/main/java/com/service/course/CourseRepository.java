package com.service.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer > {
    Optional<Course> findById(Integer id);
}
