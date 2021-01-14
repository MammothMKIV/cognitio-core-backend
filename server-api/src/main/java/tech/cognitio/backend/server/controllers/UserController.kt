package tech.cognitio.backend.server.controllers

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.cognitio.backend.server.entity.User

@RestController
@RequestMapping("/user")
class UserController() {
    @GetMapping("")
    fun currentUser(
        @AuthenticationPrincipal user: User,
    ): User {
        return user
    }
}