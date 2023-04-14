package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.Student
import kr.ac.kau.learningmate.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
) {

    fun findAllStudents(): List<Student> {
        return studentRepository.findAll()
    }
}
