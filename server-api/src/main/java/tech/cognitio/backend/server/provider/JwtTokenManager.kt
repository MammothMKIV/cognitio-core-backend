package tech.cognitio.backend.server.provider

import io.jsonwebtoken.Clock
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Base64
import java.util.Date

class JwtTokenManager constructor(
    private val issuer: String,
    private val secret: String,
) : TokenManager {
    private val secretKey: String = Base64.getEncoder().encodeToString(secret.encodeToByteArray())
    private val clock: Clock = UtcClock()

    private class UtcClock : Clock {
        override fun now(): Date {
            return Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")))
        }
    }

    override fun issue(data: Map<String, String>, lifetime: Long, subject: String): String {
        val now = LocalDateTime.now(ZoneId.of("UTC"))

        val claims = Jwts
            .claims()
            .setIssuer(issuer)
            .setSubject(subject)
            .setIssuedAt(Timestamp.valueOf(now))
            .setExpiration(Timestamp.valueOf(now.plusSeconds(lifetime)))

        claims.putAll(data)

        return Jwts
            .builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    override fun decode(token: String, subject: String): Map<String, String> {
        val claims = Jwts
            .parserBuilder()
            .setClock(clock)
            .requireIssuer(issuer)
            .requireSubject(subject)
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        val result = HashMap<String, String>()

        for (key in claims.keys) {
            result[key] = claims[key].toString()
        }

        return result
    }
}