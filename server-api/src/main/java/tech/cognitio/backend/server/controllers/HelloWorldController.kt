package tech.cognitio.backend.server.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import tech.cognitio.backend.server.entity.Course
import tech.cognitio.backend.server.repository.CourseRepository

@RestController
class HelloWorldController @Autowired constructor(private val courseRepository: CourseRepository) {
    @GetMapping("/hello")
    fun hello(): String {
        val course = Course()

        course.description = "1231231231"
        course.shortDescription = "sdada"
        course.slug = "23423-34342"
        course.title = "Test Course"

        courseRepository.save(course)
        courseRepository.flush()

        return "Hello World 12312312"
    }
}