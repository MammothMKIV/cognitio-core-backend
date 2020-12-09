package tech.cognitio.backend.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CognitioApplication

fun main(args: Array<String>) {
    SpringApplication.run(CognitioApplication::class.java, *args)
}