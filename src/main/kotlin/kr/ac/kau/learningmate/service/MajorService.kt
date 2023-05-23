package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.controller.dto.MajorDto
import kr.ac.kau.learningmate.domain.Major
import kr.ac.kau.learningmate.repository.MajorRepository
import org.springframework.boot.actuate.web.mappings.servlet.DispatcherServletsMappingDescriptionProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MajorService(
    private val majorRepository: MajorRepository,
    private val description: DispatcherServletsMappingDescriptionProvider
) {
    fun getAllMajors(pageable: Pageable): Page<Major> {
        return majorRepository.findAll(pageable)
    }

    fun createMajor(majorDto: MajorDto.AdminRequest): Major {
        val major = Major(0L, majorDto.majorName, majorDto.description)
        return majorRepository.save(major)
    }
}
