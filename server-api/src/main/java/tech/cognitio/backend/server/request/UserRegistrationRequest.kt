package tech.cognitio.backend.server.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserRegistrationRequest(
    @NotBlank
    @Email
    val email: String,

    @NotBlank
    val name: String,

    @Size(min = 6)
    val password: String
)