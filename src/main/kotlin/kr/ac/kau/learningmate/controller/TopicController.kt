package kr.ac.kau.learningmate.controller

import kr.ac.kau.learningmate.controller.dto.TopicDto
import kr.ac.kau.learningmate.service.TopicService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/topic")
class TopicController(
    private val topicService: TopicService
) {

    @GetMapping
    fun getTopicsBySubjectId(
        @RequestParam subjectId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<TopicDto.Response> {
        val pageable: Pageable = PageRequest.of(page, size)
        return topicService.getTopic(subjectId = subjectId, pageable = pageable)
            .map {
                TopicDto.Response(
                    id = it.id,
                    topicName = it.topicName,
                    description = it.description
                )
            }
    }
}
