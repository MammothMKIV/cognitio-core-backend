package tech.cognitio.backend.server.filter

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationFilter(
    private val requestMatcher: RequestMatcher
) : AbstractAuthenticationProcessingFilter(requestMatcher) {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val param: String? = Optional.ofNullable(request?.getHeader("authorization")).orElse(null)

        val token: String = Optional.ofNullable(param)
            .map { value -> value.removePrefix("Bearer") }
            .map(String::trim)
            .orElseThrow { BadCredentialsException("Missing authentication token") }

        val auth: Authentication = UsernamePasswordAuthenticationToken(token, token)

        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain?.doFilter(request, response)
    }
}