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
        val prompt = """
            OSI 7 계층(Open Systems Interconnection Reference Model 7 Layer)은 네트워크 프로토콜을 기능별로 7개의 계층으로 분리하여 각 계층 간 상호 작동하는 방식을 정해 놓은 것입니다.
            통신이 일어나는 과정을 7단계로 구분하여 과정을 단계별로 파악할 수 있습니다.            
            1 계층 ~ 7 계층 순서대로 물리계층, 데이터링크 계층, 네트워크 계층, 전송 계층, 세션 계층, 표현 계층, 응용계층이라 부르고
            5~7 계층을 응용 계층으로 통합하여 OSI 5계층이라고 부르기도 합니다.
            각 계층은 서로 독립적이며 하위 계층의 기능만을 이용할 수 있고 상위 계층에게 기능을 제공합니다.
        """.trimIndent()

        // Act
        val completedChat = gptService.completeChat(prompt)
        println(completedChat)

        // Assert
        Assertions.assertThat(completedChat).isNotEmpty()
    }
}
