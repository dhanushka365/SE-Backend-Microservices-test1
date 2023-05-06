package com.service.student;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/registration/")
     public void registerStudent (@RequestBody StudentRegistrationRequest studentRegistrationRequest){
        log.info("new student registration {}", studentRegistrationRequest);
        studentService.registerStudent(studentRegistrationRequest);
    }

    //localhost:8080/api/v1/students/view/?page=1&size=10
    @GetMapping("/view/")
    public Page<Student> getStudents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return studentService.getStudents(PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public  void updateStudent(@PathVariable Integer studentId, @RequestBody StudentUpdateRequest studentUpdateRequest ){
        studentService.updateStudent(studentId ,studentUpdateRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody LoginRequest loginRequest) {
        log.info("student login request for email: {}", loginRequest.getEmail());
        boolean isAuthenticated = studentService.authenticateStudent(loginRequest.getEmail(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

}
