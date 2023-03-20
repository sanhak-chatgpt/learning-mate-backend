package kr.ac.kau.learningmate.student

import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
) {

    fun findAllStudents(): List<Student> {
        return studentRepository.findAll()
    }
}
