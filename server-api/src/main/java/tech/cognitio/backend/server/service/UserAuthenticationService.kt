package tech.cognitio.backend.server.service

import tech.cognitio.backend.server.entity.User
import java.util.Optional

interface UserAuthenticationService {
    fun login(username: String, password: String) : String?

    fun findByToken(token: String) : User?

    fun logout(user: User)
}