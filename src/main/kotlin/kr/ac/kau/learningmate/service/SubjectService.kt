package kr.ac.kau.learningmate.service

import kr.ac.kau.learningmate.domain.Subject
import kr.ac.kau.learningmate.repository.MajorRepository
import kr.ac.kau.learningmate.repository.SubjectRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository,
    private val majorRepository: MajorRepository
) {

    fun getSubject(majorId: Long, pageable: Pageable): Page<Subject> {
        return subjectRepository.findAllByMajorId(majorId = majorId, pageable = pageable)
    }

    fun createSubject(majorId: Long, subjectName: String, description: String): Subject {
        val major = majorRepository.findById(majorId)
            .orElseThrow { NoSuchElementException("Major not found with ID: $majorId") }

        val subject = Subject(id = 0, major = major, subjectName = subjectName, description = description)
        return subjectRepository.save(subject)
    }
}
