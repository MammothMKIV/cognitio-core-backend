package tech.cognitio.backend.server.service

import tech.cognitio.backend.server.entity.User
import java.util.Optional

interface UserAuthenticationService {
    fun login(username: String, password: String) : Optional<String>

    fun findByToken(token: String) : Optional<User>

    fun logout(user: User)
}