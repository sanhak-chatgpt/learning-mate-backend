package kr.ac.kau.learningmate.config
import io.sentry.Sentry
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.spring.web.advice.ProblemHandling

@RestControllerAdvice
class ExceptionHandlingControllerAdvisor : ProblemHandling {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Problem> {
        // Sentry로 예외 전송
        Sentry.captureException(ex)

        val problem = Problem.builder()
            .withStatus(Status.INTERNAL_SERVER_ERROR)
            .withTitle("An error occurred")
            .withDetail("문제가 생겼습니다.")
            .build()

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem)
    }
}
