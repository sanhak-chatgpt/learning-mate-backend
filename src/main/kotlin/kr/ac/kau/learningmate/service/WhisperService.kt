package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.WhisperDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.lang.IllegalStateException

@Service
class WhisperService(
    webClientBuilder: WebClient.Builder,
    @Value("\${learning-mate.openai-api-key}")
    private val openaiApiKey: String
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val webClient = webClientBuilder
        .baseUrl("https://api.openai.com")
        .build()

    fun transcribeAudio(request: WhisperDto.Request): String {
        val multipartBodyBuilder = MultipartBodyBuilder()
        multipartBodyBuilder.part("file", request.file).filename("file.wav")
        multipartBodyBuilder.part("model", request.model)
        multipartBodyBuilder.part("language", "ko")
        val body = BodyInserters.fromMultipartData(multipartBodyBuilder.build())

        try {
            return webClient.post()
                .uri("/v1/audio/transcriptions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer $openaiApiKey")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .bodyToMono(WhisperDto.Response::class.java)
                .block()!!.text
        } catch (e: WebClientResponseException) {
            log.error("Error occurred while transcribing audio - ${e.responseBodyAsString}", e)
            throw IllegalStateException("Error occurred while transcribing audio", e)
        }
    }
}
