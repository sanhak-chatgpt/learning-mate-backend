package kr.ac.kau.learningmate.config

import com.fasterxml.jackson.databind.ObjectMapper
import kr.ac.kau.learningmate.controller.dto.ChatGptDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Component
class GptClient(
    private val webClientBuilder: WebClient.Builder,
    private val objectMapper: ObjectMapper,
    @Value("\${learning-mate.openai-api-key}")
    private val openaiApiKey: String
) {
    private val webClient = webClientBuilder.baseUrl("https://api.openai.com").build()

    fun askQuestion(prompt: String): String {
        val input = ChatGptDto.Request(prompt, 100, 1, 0.5)
        val requestBody = objectMapper.writeValueAsString(input)

        val response = webClient.post()
            .uri("/v1/engines/davinci-codex/completions")
            .header("Authorization", "Bearer $openaiApiKey")
            .body(BodyInserters.fromValue(requestBody))
            .retrieve()
            .bodyToMono(ChatGptDto.Response::class.java)
            .block()!!

        return response.choices
    }
}
