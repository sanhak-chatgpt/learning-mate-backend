package kr.ac.kau.learningmate.student

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController(
    private val studentService: StudentService,
) {

    @GetMapping("/")
    fun list(): List<StudentResponseDto> {
        return studentService
            .findAllStudents()
            .map {
                StudentResponseDto(
                    name = it.name,
                    studentId = it.studentId,
                )
            }
    }
}
