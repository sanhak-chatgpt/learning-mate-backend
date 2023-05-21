package kr.ac.kau.learningmate.config

import kr.ac.kau.learningmate.service.JwtService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*
* 클라이언트에서 전송된 JWT를 검증하고, 유효한 경우 해당 JWT에서 추출된 사용자 정보를 사용하여 SecurityContextHolder에 인증 객체를 저장
* */
@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val jwt = try {
            jwtService.getJwt()
        } catch (e: Exception) {
            ""
        }

        if (!jwt.isNullOrEmpty()) {
            try {
                val userId = jwtService.getUserId()

                val authentication = UsernamePasswordAuthenticationToken(userId, null, null)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (ex: Exception) {
                logger.error("Failed to authenticate JWT: ${ex.message}")
            }
        }
        filterChain.doFilter(request, response)
    }
}
