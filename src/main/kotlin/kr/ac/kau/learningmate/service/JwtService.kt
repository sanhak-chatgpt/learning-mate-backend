package kr.ac.kau.learningmate.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kr.ac.kau.learningmate.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.issuer}") private val jwtIssuer: String,
) {
    fun generateJwt(user: User): String {
        val now = Date()
        val expiration = Date(now.time + 31536000000) // 유효 기간 1년 (365 * 24 * 60 * 60 * 1000)

        val claims = Jwts.claims().apply {
            subject = user.id.toString()
            put("name", user.name)
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtIssuer)
            .setIssuedAt(now)
            .setExpiration(expiration)
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
}
