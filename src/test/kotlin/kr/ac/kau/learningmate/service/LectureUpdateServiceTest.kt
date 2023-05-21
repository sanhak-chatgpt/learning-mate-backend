package kr.ac.kau.learningmate.service

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kr.ac.kau.learningmate.domain.Lecture
import kr.ac.kau.learningmate.domain.Major
import kr.ac.kau.learningmate.domain.Subject
import kr.ac.kau.learningmate.domain.Topic
import kr.ac.kau.learningmate.domain.User
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestPropertySource(properties = ["learning-mate.openai-api-key=example-key"])
@Disabled("This test requires a valid OpenAI API key. Please change the below property to a valid key.")
internal class LectureUpdateServiceTest {

    @MockK
    lateinit var gptService: GptService

    @MockK
    lateinit var whisperService: WhisperService

    @InjectMockKs
    lateinit var lectureService: LectureService

    @Test
    fun lectureUpdateServiceTest() {
        // Given
        val user = User(1, "구영민", "auth_token_example")
        val major = Major(1, "소프트웨어", "컴퓨터와 소프트웨어학에 대해 배우는 과목")
        val subject = Subject(1, major, "컴퓨터 네트워크", "네트워크적인 프레임워크에 대해 배우는 과목")
        val topic = Topic(1, subject, "TCP/IP", "통신에 대해 배우는 내용")

        val lecture = Lecture(
            id = 0L,
            user = user,
            topic = topic,
            audioUrl = "https://learning-mate-content-prod.s3.ap-northeast-2.amazonaws.com/example.m4a",
            transcribed = null,
            score = null,
            strength = null,
            weakness = null,
            status = Lecture.Status.IN_PROGRESS,
            helpfulnessRating = null,
        )

        // When
        lectureService.updateLectureAsync(lecture)

        // Then
        println(lecture.transcribed)
    }
}
