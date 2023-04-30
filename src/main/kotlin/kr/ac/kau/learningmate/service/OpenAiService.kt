package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.OpenAiDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class OpenAiService(
    webClientBuilder: WebClient.Builder,
) {

    private val webClient = webClientBuilder
        .baseUrl("https://www.httpbin.org")
        .build()

    fun getHttpbin(): OpenAiDto.Response {
        val response = webClient.get()
            .uri("get")
            .retrieve()
            .bodyToMono(OpenAiDto.Response::class.java)
            .block()!!

        return response
    }
}

/**
 *
 * class OpenAiService {
 * // private val 쓰면
 *    private WebClient.Builder builder;
 *    private val webClient;
 *
 *     public OpenAiService(WebClient.Builder builder) {
 *
 *         this.webClient = webClientBuilder
 *             .baseUrl("https://www.httpbin.org")
 *             .build()
 *         this.builder = builder;
 *     }
 * }
 *
 */
