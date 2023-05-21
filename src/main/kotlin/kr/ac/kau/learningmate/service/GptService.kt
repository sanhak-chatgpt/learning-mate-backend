package kr.ac.kau.learningmate.service

import com.fasterxml.jackson.databind.ObjectMapper
import kr.ac.kau.learningmate.controller.dto.GptDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Component
class GptService(
    webClientBuilder: WebClient.Builder,
    private val objectMapper: ObjectMapper,
    @Value("\${learning-mate.openai-api-key}")
    private val openaiApiKey: String
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val webClient = webClientBuilder
        .baseUrl("https://api.openai.com")
        .build()

    fun completeChat(prompt: String): String {
        val input = GptDto.Request(
            model = "gpt-4",
            temperature = 1.15,
            messages = listOf(
                GptDto.Request.Message(
                    role = "user",
                    content = """
                         아래의 형식으로 데이터를 JSON 으로 만들어줘. 설명하지마. 마크다운으로 주지 마. 오로지 JSON 형태로만 줘.
                         {"score":"/* 0~100사이의 강의에 대한 점수 정확도를 기준으로 측정*/","strength":"/* 200자 내외의 내용의 정확도를 좋았던 점*/","weakness":"/* 200자 내외의 정확도를 기준으로 추가해야 할 내용*/"}
                    """.trimIndent()
                ),
                GptDto.Request.Message(
                    role = "user",
                    content = prompt,
                )
            ),
        )

        try {
            return webClient.post()
                .uri("/v1/chat/completions")
                .header("Authorization", "Bearer $openaiApiKey")
                .bodyValue(input)
                .retrieve()
                .bodyToMono(GptDto.Response::class.java)
                .block()!!
                .choices
                .first()
                .message
                .content
        } catch (e: WebClientResponseException) {
            log.error("Error occurred while completing chat - ${e.responseBodyAsString}", e)
            throw IllegalStateException("Error occurred while completing chat", e)
        }
    }
}
