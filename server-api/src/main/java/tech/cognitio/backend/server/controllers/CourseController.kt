package tech.cognitio.backend.server.controllers

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tech.cognitio.backend.server.entity.Course
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.manager.CourseManager

@RestController
@RequestMapping("/courses")
class CourseController(
    private val courseManager: CourseManager
) {
    @GetMapping("")
    fun getCourseList(@AuthenticationPrincipal user: User, @RequestParam page: Int, @RequestParam perPage: Int): List<Course> {
        return courseManager.getUserCourses(user, page, perPage)
    }
}