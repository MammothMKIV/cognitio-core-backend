package tech.cognitio.backend.server.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "modules")
class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "title")
    var title: String? = null

    @Column(name = "number")
    var number: Int = 0

    @ManyToOne
    var course: Course? = null
}