package tech.cognitio.backend.server.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import tech.cognitio.backend.server.entity.Course
import tech.cognitio.backend.server.entity.User

@Repository
interface CourseRepository : JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c WHERE c.author = :user")
    fun getUserCoursesWithPagination(@Param("user") user: User, pageable: Pageable): List<Course>
}