package tech.cognitio.backend.server.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "attachments")
class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "target")
    var target: Long = 0

    @Column(name = "target_type")
    var targetType: String? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "location")
    var location: String? = null

    @Column(name = "location_meta")
    var locationMeta: String? = null

    @Column(name = "size")
    var size: Long = 0
}