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
@Table(name = "task_solutions")
class TaskSolution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0

    @ManyToOne
    var task: Task? = null

    @ManyToOne
    var author: User? = null

    @Column(name = "accepted")
    var accepted: Boolean = false

    @Column(name = "accepted_on")
    var acceptedOn: LocalDateTime? = null

    @Column(name = "content")
    var content: String? = null

    @Column(name = "submission_number")
    var submissionNumber: Int? = 0
}