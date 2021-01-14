package tech.cognitio.backend.server.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.NegatedRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import tech.cognitio.backend.server.filter.TokenAuthenticationFilter
import tech.cognitio.backend.server.provider.TokenAuthenticationProvider
import tech.cognitio.backend.server.strategy.NoRedirectStrategy

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val tokenAuthenticationProvider: TokenAuthenticationProvider
) : WebSecurityConfigurerAdapter() {
    private val PUBLIC_URLS: RequestMatcher = OrRequestMatcher(
        AntPathRequestMatcher("/user/register"),
        AntPathRequestMatcher("/user/login")
    )
    private val PROTECTED_URLS: RequestMatcher = NegatedRequestMatcher(PUBLIC_URLS)

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(tokenAuthenticationProvider)
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.requestMatchers(PUBLIC_URLS)
    }

    override fun configure(http: HttpSecurity?) {
        http
            ?.sessionManagement()
            ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()
            ?.exceptionHandling()
            ?.defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
            ?.and()
            ?.authenticationProvider(tokenAuthenticationProvider)
            ?.addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter::class.java)
            ?.authorizeRequests()
            ?.requestMatchers(PROTECTED_URLS)
            ?.authenticated()
            ?.and()
            ?.csrf()?.disable()
            ?.formLogin()?.disable()
            ?.httpBasic()?.disable()
            ?.logout()?.disable()
    }

    @Bean
    fun restAuthenticationFilter(): TokenAuthenticationFilter {
        val filter = TokenAuthenticationFilter(PROTECTED_URLS)
        filter.setAuthenticationManager(authenticationManager())
        filter.setAuthenticationSuccessHandler(successHandler())
        return filter
    }

    @Bean
    fun successHandler(): SimpleUrlAuthenticationSuccessHandler {
        val successHandler = SimpleUrlAuthenticationSuccessHandler()
        successHandler.setRedirectStrategy(NoRedirectStrategy())
        return successHandler
    }

    @Bean
    fun disableAutoRegistration(tokenAuthenticationFilter: TokenAuthenticationFilter): FilterRegistrationBean<TokenAuthenticationFilter> {
        val registration: FilterRegistrationBean<TokenAuthenticationFilter> = FilterRegistrationBean(tokenAuthenticationFilter)
        registration.isEnabled = false
        return registration
    }

    @Bean
    fun forbiddenEntryPoint(): AuthenticationEntryPoint {
        return HttpStatusEntryPoint(HttpStatus.FORBIDDEN)
    }
}