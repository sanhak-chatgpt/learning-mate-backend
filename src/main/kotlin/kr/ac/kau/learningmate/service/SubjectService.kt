package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.Subject
import kr.ac.kau.learningmate.repository.SubjectRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SubjectService(private val subjectRepository: SubjectRepository) {

    fun getSubject(majorId: Long, pageable: Pageable): Page<Subject> {
        return subjectRepository.findAllByMajorId(majorId = majorId, pageable = pageable)
    }
}
