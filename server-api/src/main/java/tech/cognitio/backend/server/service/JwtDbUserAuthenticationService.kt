package tech.cognitio.backend.server.service

import io.jsonwebtoken.JwtException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.provider.TokenManager
import tech.cognitio.backend.server.repository.UserRepository
import java.util.Optional

@Service
class JwtDbUserAuthenticationService(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
    private val passwordEncoder: PasswordEncoder,
) : UserAuthenticationService {
    override fun login(username: String, password: String): Optional<String> {
        val user = userRepository.findByEmail(username) ?: return Optional.empty()

        if (!passwordEncoder.matches(password, user.passwordHash)) {
            return Optional.empty()
        }

        val userData = HashMap<String, String>()

        userData["id"] = user.id.toString()

        return Optional.of(tokenManager.issue(userData, 3600L, "access"))
    }

    override fun findByToken(token: String): Optional<User> {
        return try {
            val userData = tokenManager.decode(token, "access")

            userRepository.findById(userData["id"]!!.toLong())
        } catch (e: JwtException) {
            Optional.empty()
        }
    }

    override fun logout(user: User) {
        // nothing
    }
}