package kr.ac.kau.learningmate.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.Date
@Service
class JwtService(
    @Value("\${jwt.secret}") private val jwtSecret: String,
) {
    fun generateJwt(userId: Long, expirationHours: Long = 24): String {
        val now = Date()
        val expiration = Date(now.time + expirationHours * 3600 * 1000)

        return Jwts.builder()
            .claim("userId", userId)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact()
    }

    fun verifyJwt(jwt: String): Claims {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(jwt)
            .body
    }

    /*
    Header에서 Authorization 헤더에서 JWT 추출
    @return String
    */
    fun getJwt(): String? {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return request.getHeader("Authorization")?.removePrefix("Bearer ")
    }

    /*
    JWT에서 userId 추출
    */
    fun getUserId(): Long {
        val jwt = getJwt()
        if (jwt.isNullOrEmpty()) {
            throw IllegalArgumentException("Empty JWT")
        }
        try {
            val claims = verifyJwt(jwt)
            return claims["userId"].toString().toLong()
        } catch (ex: Exception) {
            throw IllegalArgumentException("Invalid JWT")
        }
    }
}
