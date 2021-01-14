package tech.cognitio.backend.server.service

import io.jsonwebtoken.JwtException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.manager.UserManager
import tech.cognitio.backend.server.provider.TokenManager
import tech.cognitio.backend.server.repository.UserRepository
import java.util.Optional

@Service
class JwtDbUserAuthenticationService(
    private val tokenManager: TokenManager,
    private val userManager: UserManager,
) : UserAuthenticationService {
    override fun login(username: String, password: String): String? {
        val user = userManager.getUserByEmail(username) ?: return null

        if (!userManager.isPasswordMatches(user, password)) {
            return null
        }

        val userData = HashMap<String, String>()

        userData["id"] = user.id.toString()

        return tokenManager.issue(userData, 3600L, "access")
    }

    override fun findByToken(token: String): User? {
        return try {
            val userData = tokenManager.decode(token, "access")

            userManager.getUserById(userData["id"]!!.toLong())
        } catch (e: JwtException) {
            null
        }
    }

    override fun logout(user: User) {
        // nothing
    }
}