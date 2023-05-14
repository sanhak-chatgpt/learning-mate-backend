package kr.ac.kau.learningmate.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*
* 클라이언트에서 전송된 JWT를 검증하고, 유효한 경우 해당 JWT에서 추출된 사용자 정보를 사용하여 SecurityContextHolder에 인증 객체를 저장
* */
class JwtAuthenticationFilter(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.issuer}") private val jwtIssuer: String

) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)?.removePrefix("Bearer ")
        val token = authHeader?.replace("Bearer ", "")
        try {
            val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .requireIssuer(jwtIssuer)
                .parseClaimsJws(token)
                .body
            val authentication = UsernamePasswordAuthenticationToken(
                claims.subject,
                null,
                emptyList()
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: Exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT")
            return
        }
        if (request.requestURI == "/users/issue/token") {
            val jwt = Jwts.builder()
                .setIssuer(jwtIssuer)
                .setSubject("randomUser")
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact()
            response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer $jwt")
            response.sendRedirect("/")
            return
        }
        filterChain.doFilter(request, response)
    }
}
