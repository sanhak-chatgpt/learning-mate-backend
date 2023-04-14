package kr.ac.kau.learningmate.controller

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kr.ac.kau.learningmate.controller.StudentController
import kr.ac.kau.learningmate.domain.Student
import kr.ac.kau.learningmate.service.StudentService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockKExtension::class)
class StudentControllerUnitTest {

    @MockK
    lateinit var studentService: StudentService

    @InjectMockKs
    lateinit var studentController: StudentController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build()
    }

    @Test
    fun `list returns a list of students`() {
        // Given
        val students = listOf(
            Student(id = 1L, name = "John Doe", studentId = 1),
            Student(id = 2L, name = "Jane Doe", studentId = 2),
        )

        every { studentService.findAllStudents() } returns students

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
