package kr.ac.kau.learningmate.integration

import kr.ac.kau.learningmate.domain.Student
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class StudentIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var studentRepository: StudentRepository

    private lateinit var students: List<Student>

    @BeforeEach
    fun setup() {
        students = listOf(
            Student(id = 0L, name = "John Doe", studentId = 1),
            Student(id = 0L, name = "Jane Doe", studentId = 2)
        )

        studentRepository.saveAll(students)
    }

    @AfterEach
    fun cleanup() {
        studentRepository.deleteAll()
    }

    @Test
    fun `findAllStudents should return a list of students`() {
        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].name").value("John Doe"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].studentId").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].name").value("Jane Doe"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].studentId").value(2))
    }
}
