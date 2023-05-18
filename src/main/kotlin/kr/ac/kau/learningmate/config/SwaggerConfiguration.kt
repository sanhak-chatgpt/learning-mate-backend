package kr.ac.kau.learningmate.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.core.jackson.TypeNameResolver
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class SwaggerConfiguration(private val objectMapper: ObjectMapper) {

    @PostConstruct
    fun initialize() {
        val innerClassAwareTypeNameResolver = object : TypeNameResolver() {
            override fun getNameOfClass(cls: Class<*>): String {
                return cls.name
                    .substringAfterLast(".")
                    .replace("$", "")
            }
        }

        ModelConverters
            .getInstance()
            .addConverter(ModelResolver(objectMapper, innerClassAwareTypeNameResolver))
    }

    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI().components(
            Components().addSecuritySchemes(
                "bearer-key",
                SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
            )
        )
    }
}
