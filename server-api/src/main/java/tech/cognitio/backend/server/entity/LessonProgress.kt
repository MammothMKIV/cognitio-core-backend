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
@Table(name = "lesson_progress")
class LessonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0

    @ManyToOne
    var lesson: Lesson? = null

    @ManyToOne
    var user: User? = null

    @Column(name = "completed_on")
    var completedOn: LocalDateTime? = null

    @Column(name = "status")
    var status: String? = null
}