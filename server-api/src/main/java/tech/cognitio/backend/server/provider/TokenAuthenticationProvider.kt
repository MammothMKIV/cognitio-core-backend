package tech.cognitio.backend.server.provider

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import tech.cognitio.backend.server.service.UserAuthenticationService
import java.util.Optional

@Service
class TokenAuthenticationProvider(
    val userAuthenticationService: UserAuthenticationService
) : AbstractUserDetailsAuthenticationProvider() {
    override fun additionalAuthenticationChecks(
        userDetails: UserDetails?,
        authentication: UsernamePasswordAuthenticationToken?
    ) {

    }

    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        val token: Any? = authentication?.credentials

        return Optional
            .ofNullable(token)
            .map(Any::toString)
            .map(userAuthenticationService::findByToken)
            .orElse(null) ?: throw UsernameNotFoundException("Cannot find user with authentication token=$token")
    }
}