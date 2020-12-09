package tech.cognitio.backend.server.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tasks")
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0

    @Column(name = "description_short")
    var descriptionShort: String? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "title")
    var title: String? = null

    @ManyToOne
    var author: User? = null

    @Column(name = "attachment_type")
    var attachmentType: String? = null
}