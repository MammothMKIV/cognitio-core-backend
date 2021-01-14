package tech.cognitio.backend.server.manager

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.repository.UserRepository

@Component
class UserManager(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun getUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun getUserById(userId: Long): User? {
        return userRepository.getOne(userId)
    }

    fun createUser(name: String, email: String, password: String, slug: String?, active: Boolean = true): User {
        val user = User()

        setUserPassword(user, password)
        user.name = name
        user.email = email
        user.slug = slug
        user.active = active

        userRepository.saveAndFlush(user)

        return user
    }

    fun setUserPassword(user: User, password: String) {
        user.passwordHash = passwordEncoder.encode(password)
    }

    fun isPasswordMatches(user: User, password: String): Boolean {
        return passwordEncoder.matches(password, user.passwordHash)
    }
}