package kr.ac.kau.learningmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication(
    exclude = [
        UserDetailsServiceAutoConfiguration::class,
    ]
)
class LearningMateApplication

fun main(args: Array<String>) {
    runApplication<LearningMateApplication>(*args)
}
