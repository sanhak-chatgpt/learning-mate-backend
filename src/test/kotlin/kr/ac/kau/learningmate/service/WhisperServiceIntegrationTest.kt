package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.WhisperDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestPropertySource(properties = ["learning-mate.openai-api-key=example-key"])
@Disabled("This test requires a valid OpenAI API key. Please change the below property to a valid key.")
internal class WhisperServiceIntegrationTest {
    @Autowired
    private lateinit var whisperService: WhisperService

    @Test
    fun testTranscribeAudio() {
        // Arrange
        val file: Resource = ClassPathResource("example.m4a")
        val request = WhisperDto.Request(
            model = "whisper-1",
            file = file.inputStream.readAllBytes()
        )

        // Act
        val transcription = whisperService.transcribeAudio(request)
        println(transcription)

        // Assert
        Assertions.assertThat(transcription).isNotEmpty()
    }
}
