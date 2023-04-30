package kr.ac.kau.learningmate

import kr.ac.kau.learningmate.service.OpenAiService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LearningMateApplicationTests {

    @Autowired
    lateinit var openAiService: OpenAiService

    @Test
    fun contextLoads() {
    }

    // data class Response(
    //     val args: Map<String, String>,
    //     val headers: Map<String, String>,
    //     val origin: String,
    //     val url: String,
    // )

    // @Test
    // fun testWebClient() {
    //     val webClient = webClientBuilder.build()
    //
    //     val response: Response = webClient.get()
    //         .uri("https://www.httpbin.org/get")
    //         .retrieve()
    //         .bodyToMono(Response::class.java)
    //         .block()!!
    //
    //     println(response)
    // }

    @Test
    fun testGetHttpbin() {
        val response = openAiService.getHttpbin()
        println(response)
    }
}
