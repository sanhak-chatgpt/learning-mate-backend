package kr.ac.kau.learningmate.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .and()
            .csrf()
            .disable()
            .cors().configurationSource {
                val cors = CorsConfiguration()
                cors.allowedOrigins = listOf("https://www.thelearningmate.com", "http://localhost:3000")
                cors.allowedMethods = listOf("*")
                cors.allowedHeaders = listOf("*")
                cors
            }
            .and()
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .and()
            .httpBasic()
    }
}
