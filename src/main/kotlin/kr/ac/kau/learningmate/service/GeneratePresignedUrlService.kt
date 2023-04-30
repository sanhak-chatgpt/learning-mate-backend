package kr.ac.kau.learningmate.service

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Service
class GeneratePresignedUrlService(
    private val s3Client: AmazonS3,
    @Value("\${learning-mate.bucket-name}")
    private val bucketName: String,
) {

    companion object {
        private const val PRESIGNED_URL_EXPIRATION_IN_SECONDS = 10 * 60
    }

    fun generatePresignedUrl(userId: Long): String {
        val expiration = Date(System.currentTimeMillis() + PRESIGNED_URL_EXPIRATION_IN_SECONDS * 1000)

        val now = LocalDateTime.now()

        val objectKey = "${now.year}/${now.monthValue}/${now.dayOfMonth}/$userId/${UUID.randomUUID()}"

        val generatePresignedUrlRequest = GeneratePresignedUrlRequest(bucketName, objectKey)
            .withMethod(HttpMethod.PUT)
            .withExpiration(expiration)

        val presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest)
        return presignedUrl.toString()
    }
}
