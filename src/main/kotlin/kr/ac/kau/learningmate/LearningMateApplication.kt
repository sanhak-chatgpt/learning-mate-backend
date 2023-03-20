package kr.ac.kau.learningmate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
	exclude = [
		UserDetailsServiceAutoConfiguration::class,
	]
)
class LearningMateApplication

fun main(args: Array<String>) {
	runApplication<LearningMateApplication>(*args)
}
