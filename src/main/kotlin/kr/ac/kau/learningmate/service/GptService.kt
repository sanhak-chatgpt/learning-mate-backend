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
            temperature = 0.9,
            messages = listOf(
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
