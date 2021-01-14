package tech.cognitio.backend.server.seeder

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import tech.cognitio.backend.server.manager.UserManager

@Component
class DataSeeder(
    private val userManager: UserManager,
) {
    private fun seedUsers() {
        userManager.createUser("Admin", "admin@example.com", "test1234", null)
    }

    @EventListener
    fun seed(event: ContextRefreshedEvent) {
        seedUsers()
    }
}