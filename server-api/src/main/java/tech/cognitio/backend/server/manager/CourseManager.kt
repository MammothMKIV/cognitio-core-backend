package tech.cognitio.backend.server.manager

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import tech.cognitio.backend.server.entity.Course
import tech.cognitio.backend.server.entity.Term
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.repository.CourseRepository
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class CourseManager(
    private val courseRepository: CourseRepository
) {
    fun createCourse(title: String, shortDescription: String, description: String, author: User, category: Term): Course {
        val course = Course()

        course.title = title
        course.shortDescription = shortDescription
        course.description = description
        course.author = author
        course.category = category
        course.createdBy = author
        course.createdAt = LocalDateTime.now(ZoneId.of("UTC"))

        courseRepository.saveAndFlush(course)

        return course
    }

    fun getUserCourses(user: User, page: Int, perPage: Int): List<Course> {
        return courseRepository.getUserCoursesWithPagination(user, PageRequest.of(page, perPage))
    }
}