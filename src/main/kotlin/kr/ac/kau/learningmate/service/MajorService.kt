package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.Major
import kr.ac.kau.learningmate.repository.MajorRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MajorService(private val majorRepository: MajorRepository) {
    fun getAllMajors(pageable: Pageable): Page<Major> {
        return majorRepository.findAll(pageable)
    }
}
