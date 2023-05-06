package com.service.teacher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/teachers")
@AllArgsConstructor
public class TeacherController {
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
}
