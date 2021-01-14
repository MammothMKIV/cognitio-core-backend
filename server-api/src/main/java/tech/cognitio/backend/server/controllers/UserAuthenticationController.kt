package tech.cognitio.backend.server.controllers

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tech.cognitio.backend.server.entity.User
import tech.cognitio.backend.server.repository.UserRepository
import tech.cognitio.backend.server.request.UserRegistrationRequest
import tech.cognitio.backend.server.service.UserAuthenticationService
import java.lang.RuntimeException
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserAuthenticationController(
    private val userRepository: UserRepository,
    private val userAuthenticationService: UserAuthenticationService,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/register")
    fun register(
        @Valid request: UserRegistrationRequest,
    ): String {
        val user = User()
        user.active = true
        user.email = request.email
        user.name = request.name
        user.passwordHash = passwordEncoder.encode(request.password)

        userRepository.saveAndFlush(user)

        return login(request.email, request.password)
    }

    @PostMapping("/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String
    ): String {
        return userAuthenticationService
            .login(username, password)
            .orElseThrow { RuntimeException("invalid login or password") }
    }
}