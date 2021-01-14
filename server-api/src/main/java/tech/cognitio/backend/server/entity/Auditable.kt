package tech.cognitio.backend.server.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Auditable {
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @ManyToOne
    var createdBy: User? = null

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    @ManyToOne
    var updatedBy: User? = null

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

    @ManyToOne
    var deletedBy: User? = null
}