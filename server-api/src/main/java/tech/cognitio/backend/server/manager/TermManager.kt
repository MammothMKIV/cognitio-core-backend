package tech.cognitio.backend.server.manager

import org.springframework.stereotype.Component
import tech.cognitio.backend.server.entity.Term
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.repository.TermRepository
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class TermManager(
    private val termRepository: TermRepository,
) {
    fun createTerm(title: String, type: String, slug: String?, author: User?): Term {
        val term = Term()

        term.title = title
        term.type = type
        term.slug = slug
        term.createdAt = LocalDateTime.now(ZoneId.of("UTC"))

        termRepository.saveAndFlush(term)

        return term
    }
}