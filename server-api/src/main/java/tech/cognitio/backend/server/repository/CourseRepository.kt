package tech.cognitio.backend.server.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.cognitio.backend.server.entity.Course

@Repository
interface CourseRepository : JpaRepository<Course, Long> {

}