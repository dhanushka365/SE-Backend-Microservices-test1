package com.service.teacher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController {

    @Autowired
    private final RestTemplate restTemplate;
    private final TeacherService teacherService;
    @PostMapping("/registration/")
    public void registerTeacher (@RequestBody TeacherRegistrationRequest teacherRegistrationRequest){
        log.info("new teacher registration {}", teacherRegistrationRequest);
        teacherService.registerTeacher(teacherRegistrationRequest);
    }

    //localhost:8080/api/v1/teachers/view/?page=1&size=10
    @GetMapping("/view/")
    public Page<Teacher> getTeachers(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return teacherService.getTeachers(PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{teacherId}")
    public void deleteTeacher(@PathVariable Integer teacherId){
        teacherService.deleteTeacher(teacherId);
    }

    @PutMapping("/update/{teacherId}")
    public  void updateTeacher(@PathVariable Integer teacherId, @RequestBody TeacherUpdateRequest teacherUpdateRequest ){
        teacherService.updateTeacher(teacherId ,teacherUpdateRequest);
    }

    @GetMapping("/searchTeacher/{email}")
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email) {
        try {
            Teacher teacherDetails = teacherService.findTeacherByEmail(email);
            return new ResponseEntity<>(teacherDetails, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/searchCourse/{courseId}")
    public Course getCourse(@PathVariable Integer courseId){
        String url = "http://COURSE/api/v1/courses/view/" + courseId;
        ResponseEntity<Course> response = restTemplate.getForEntity(url, Course.class);
        return response.getBody();
    }
}
