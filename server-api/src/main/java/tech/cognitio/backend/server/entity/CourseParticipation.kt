package tech.cognitio.backend.server.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "course_participations")
class CourseParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @ManyToOne
    var user: User? = null

    @ManyToOne
    var course: Course? = null

    @Column(name = "enrolled_on")
    var enrolledOn: LocalDateTime? = null

    @Column(name = "completed_on")
    var completedOn: LocalDateTime? = null
}