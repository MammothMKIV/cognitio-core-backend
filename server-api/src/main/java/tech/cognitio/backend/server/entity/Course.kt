package tech.cognitio.backend.server.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "courses")
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "title")
    var title: String? = null

    @Column(name = "slug")
    var slug: String? = null

    @ManyToOne
    var category: Term? = null

    @ManyToOne
    var author: User? = null

    @Column(name = "description_short")
    var shortDescription: String? = null

    @Column(name = "description")
    var description: String? = null
}