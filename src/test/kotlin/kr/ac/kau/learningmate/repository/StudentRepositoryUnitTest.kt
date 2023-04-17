package kr.ac.kau.learningmate.repository

import kr.ac.kau.learningmate.domain.Student
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use real database
class StudentRepositoryUnitTest {

    @Autowired
    lateinit var studentRepository: StudentRepository

    private lateinit var students: List<Student>

    @BeforeEach
    fun setup() {
        students = listOf(
            Student(id = 0L, name = "John Doe", studentId = 1),
            Student(id = 0L, name = "Jane Doe", studentId = 2),
        )

        studentRepository.saveAll(students)
    }

    @AfterEach
    fun cleanup() {
        studentRepository.deleteAll()
    }

    @Test
    fun `findAll should return a list of students`() {
        // When
        val result = studentRepository.findAll()

        // Then
        assertEquals(students.size, result.size)
        assertEquals(students[0].name, result[0].name)
        assertEquals(students[0].studentId, result[0].studentId)
        assertEquals(students[1].name, result[1].name)
        assertEquals(students[1].studentId, result[1].studentId)
    }
}
