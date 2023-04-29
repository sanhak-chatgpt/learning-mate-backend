package kr.ac.kau.learningmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication(
    exclude = [
        UserDetailsServiceAutoConfiguration::class,
        ErrorMvcAutoConfiguration::class,
    ]
)
class LearningMateApplication

fun main(args: Array<String>) {
    runApplication<LearningMateApplication>(*args)
}
