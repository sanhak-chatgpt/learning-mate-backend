package kr.ac.kau.learningmate.config
import org.springframework.web.bind.annotation.ControllerAdvice
import org.zalando.problem.spring.web.advice.ProblemHandling

@ControllerAdvice
class ExceptionHandlingControllerAdvisor : ProblemHandling
