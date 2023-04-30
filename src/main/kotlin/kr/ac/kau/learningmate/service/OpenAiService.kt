package kr.ac.kau.learningmate.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class OpenAiService(
    webClientBuilder: WebClient.Builder,
) {

    private val webClient = webClientBuilder
        .baseUrl("https://www.httpbin.org")
        .build()

    data class Response(
        val args: Map<String, String>,
        val headers: Map<String, String>,
        val origin: String,
        val url: String,
    )

    fun getHttpbin(): Response {
        val response = webClient.get()
            .uri("get")
            .retrieve()
            .bodyToMono(Response::class.java)
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
