package tech.cognitio.backend.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import tech.cognitio.backend.server.provider.JwtTokenManager
import tech.cognitio.backend.server.provider.TokenManager

@Configuration
@PropertySource("classpath:application.properties")
class AppConfig(
    private val env: Environment
) {
    @Bean
    fun tokenManager(): TokenManager {
        return JwtTokenManager(env.getProperty("jwt.issuer"), env.getProperty("jwt.secret"))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }
}