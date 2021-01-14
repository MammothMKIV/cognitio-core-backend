package tech.cognitio.backend.server.provider

interface TokenManager {
    fun issue(data: Map<String, String>, lifetime: Long, subject: String): String

    fun decode(token: String, subject: String): Map<String, String>
}