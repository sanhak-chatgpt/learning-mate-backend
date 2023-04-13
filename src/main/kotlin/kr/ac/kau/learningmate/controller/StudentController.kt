package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.StudentResponseDto
import kr.ac.kau.learningmate.service.StudentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController(
    private val studentService: StudentService,
) {

    @GetMapping("/")
    fun list(): List<StudentResponseDto.List> {
        return studentService
            .findAllStudents()
            .map {
                StudentResponseDto.List(
                    name = it.name,
                    studentId = it.studentId,
                )
            }
    }
}
