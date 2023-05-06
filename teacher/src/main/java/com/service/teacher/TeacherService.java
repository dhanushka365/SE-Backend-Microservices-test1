package com.service.teacher;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public void registerTeacher(TeacherRegistrationRequest request) {
        Teacher teacher = Teacher.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .department(request.department())
                .build();
        //todo: check if email valid
        //todo: check if email not taken
        //todo: store teacher in db
        teacherRepository.saveAndFlush(teacher);

    }

    public void deleteTeacher(Integer teacherId){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacherRepository.delete(teacher);
        } else {
            throw new IllegalArgumentException("Teacher with ID " + teacherId + " does not exist");
        }
    }

    public void updateTeacher( Integer teacherId , TeacherUpdateRequest request){
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with ID " + teacherId + " does not exist"));

        if (request.firstName() != null) {
            teacher.setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            teacher.setLastName(request.lastName());
        }

        if (request.email() != null) {
            teacher.setEmail(request.email());
        }

        if (request.department() != null) {
            teacher.setDepartment(request.department());
        }

        teacherRepository.save(teacher);
    }

    public Page<Teacher> getTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    }
