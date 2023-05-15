package kr.ac.kau.learningmate.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kr.ac.kau.learningmate.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.Date

@Service
class JwtService(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.issuer}") private val jwtIssuer: String,
) {
    fun generateJwt(user: User): String {
        val now = Date()
        // 유효기간은 존재하지 않는다.

        val claims = Jwts.claims().apply {
            subject = user.id.toString()
            put("name", user.name)
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtIssuer)
            .setIssuedAt(now)
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact()
    }

    fun verifyJwt(jwt: String): Claims {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .requireIssuer(jwtIssuer)
            .parseClaimsJws(jwt)
            .body
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
    */
    fun getJwt(): String {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return request.getHeader("Authorization")
    }

    /*
    JWT에서 userId 추출
    */
    fun getMemberId(): Long {
        val jwt = getJwt()
        if (jwt.isNullOrEmpty()) {
            throw IllegalArgumentException("Empty JWT")
        }

        try {
            val claims = verifyJwt(jwt)
            return claims.subject.toLong()
        } catch (ex: Exception) {
            throw IllegalArgumentException("Invalid JWT")
        }
    }
}
