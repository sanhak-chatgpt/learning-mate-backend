package kr.ac.kau.learningmate.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,

    @Value("\${jwt.issuer}")
    private val jwtIssuer: String,

    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers(
                "/api/v1/ranking/**",
                "/api/v1/users/lectures/**",
                "/api/v1/users/name/**",
                "/api/v1/lecture/**",
            ).authenticated()
            .anyRequest().permitAll()
            .and()
            .csrf().disable()
            .cors().configurationSource {
                val cors = CorsConfiguration()
                cors.allowedOrigins = listOf(
                    "https://www.thelearningmate.com",
                    "http://localhost:3000"
                )
                cors.allowedMethods = listOf("*")
                cors.allowedHeaders = listOf("*")
                cors
            }
    }
}
