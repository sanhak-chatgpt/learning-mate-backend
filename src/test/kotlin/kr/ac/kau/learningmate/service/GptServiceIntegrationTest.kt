package kr.ac.kau.learningmate.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestPropertySource(properties = ["learning-mate.openai-api-key=example-key"])
@Disabled("This test requires a valid OpenAI API key. Please change the below property to a valid key.")
internal class GptServiceIntegrationTest {
    @Autowired
    private lateinit var gptService: GptService

    @Test
    fun testCompleteChat() {
        // Arrange
        val prompt = "야!"

        // Act
        val completedChat = gptService.completeChat(prompt)
        println(completedChat)

        // Assert
        Assertions.assertThat(completedChat).isNotEmpty()
    }
}
