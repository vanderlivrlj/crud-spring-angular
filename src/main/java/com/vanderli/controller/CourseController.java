package com.vanderli.controller;

import com.vanderli.model.Course;
import com.vanderli.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findByID(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create (@RequestBody Course course){
        return courseRepository.save(course);
//    public ResponseEntity<Course> create(@RequestBody Course course){
//        //System.out.println(course.getName());
//       //return courseRepository.save(course);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(courseRepository.save(course));
    }

}
