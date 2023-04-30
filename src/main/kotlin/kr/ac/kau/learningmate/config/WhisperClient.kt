package kr.ac.kau.learningmate.config

import kr.ac.kau.learningmate.controller.dto.WhisperDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Component
class WhisperClient(
    webClientBuilder: WebClient.Builder,
    @Value("\${learning-mate.openai-api-key}")
    private val openaiApiKey: String
) {

    private val webClient = webClientBuilder
        .baseUrl("https://api.openai.com")
        .build()

    fun transcribeAudio(request: WhisperDto.Request): String {
        val multipartBodyBuilder = MultipartBodyBuilder()
        multipartBodyBuilder.part("file", request.file).filename("file")
        multipartBodyBuilder.part("model", request.model)
        val body = BodyInserters.fromMultipartData(multipartBodyBuilder.build())

        val response = webClient.post()
            .uri("/v1/audio/transcriptions")
            .header(HttpHeaders.AUTHORIZATION, "Bearer $openaiApiKey")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(body)
            .retrieve()
            .bodyToMono(WhisperDto.Response::class.java)
            .block()!!

        return response.data
    }
}
