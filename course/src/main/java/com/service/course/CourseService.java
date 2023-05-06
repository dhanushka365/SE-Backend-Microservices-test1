package com.service.course;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {

        private final CourseRepository courseRepository;

        public void registerCourse (CourseRegistrationRequest request){
        Course course = Course.builder()
                .courseName(request.courseName())
                .durationTime(request.durationTime())
                .description(request.description())
                .build();
        courseRepository.saveAndFlush(course);

    }

        public void deleteCourse (Integer courseId){
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            courseRepository.delete(course);
        } else {
            throw new IllegalArgumentException("Course with ID " + courseId + " does not exist");
        }
    }


        public void updateCourse (Integer courseId, CourseUpdateRequest request){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with ID " + courseId + " does not exist"));

        if (request.courseName() != null) {
            course.setCourseName(request.courseName());
        }

        if (request.durationTime() != null) {
            course.setDurationTime(request.durationTime());
        }

        if (request.description() != null) {
            course.setDescription(request.description());
        }

        courseRepository.save(course);
    }

        public Page<Course> getCourses(Pageable pageable){
        return courseRepository.findAll(pageable);
    }

}
