package com.service.course;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    @PostMapping("/registration/")
    public void registerCourse (@RequestBody CourseRegistrationRequest courseRegistrationRequest){
        log.info("new course registration {}", courseRegistrationRequest);
        courseService.registerCourse(courseRegistrationRequest);
    }

    //localhost:8080/api/v1/courses/view/?page=1&size=10
    @GetMapping("/view/")
    public Page<Course> getCourses(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return courseService.getCourses(PageRequest.of(page, size));
    }

    @DeleteMapping("/delete/{courseId}")
    public void deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
    }

    @PutMapping("/update/{courseId}")
    public  void updateCourse(@PathVariable Integer courseId, @RequestBody CourseUpdateRequest courseUpdateRequest ){
        courseService.updateCourse(courseId ,courseUpdateRequest);
    }
}
