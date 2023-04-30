package kr.ac.kau.learningmate.config

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Configuration {

    @Bean
    fun s3Client(): AmazonS3 {
        return AmazonS3ClientBuilder.standard()
            .withRegion("ap-northeast-2")
            .build()
    }
}
