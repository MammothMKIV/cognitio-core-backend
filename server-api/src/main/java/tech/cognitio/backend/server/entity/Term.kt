package tech.cognitio.backend.server.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "terms")
class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "type")
    var type: String? = null

    @Column(name = "title")
    var title: String? = null

    @Column(name = "slug")
    var slug: String? = null
}