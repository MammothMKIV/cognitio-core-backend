package tech.cognitio.backend.server.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tech.cognitio.backend.server.manager.UserManager
import tech.cognitio.backend.server.request.UserRegistrationRequest
import tech.cognitio.backend.server.service.UserAuthenticationService
import java.lang.RuntimeException
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserAuthenticationController(
    private val userAuthenticationService: UserAuthenticationService,
    private val userManager: UserManager,
) {
    @PostMapping("/register")
    fun register(
        @Valid request: UserRegistrationRequest,
    ): String {
        userManager.createUser(request.name, request.email, request.password, null)

        return login(request.email, request.password)
    }

    @PostMapping("/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String
    ): String {
        return userAuthenticationService
            .login(username, password) ?: throw RuntimeException("invalid login or password")
    }
}