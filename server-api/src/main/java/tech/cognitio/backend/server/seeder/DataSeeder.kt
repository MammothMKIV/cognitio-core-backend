package tech.cognitio.backend.server.seeder

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import tech.cognitio.backend.server.manager.CourseManager
import tech.cognitio.backend.server.manager.TermManager
import tech.cognitio.backend.server.manager.UserManager

@Component
class DataSeeder(
    private val userManager: UserManager,
    private val courseManager: CourseManager,
    private val termManager: TermManager,
) {
    private fun seedUsers() {
        userManager.createUser(
            "Admin",
            "admin@example.com",
            "test1234",
            null
        )
    }

    private fun seedCourses() {
        val author = userManager.createUser(
            "Author",
            "author@example.com",
            "test1234",
            null
        )

        for (i in 1..3) {
            val term = termManager.createTerm(
                "Category $i",
                "category",
                null,
                author
            )

            for (j in 1..5) {
                val course = courseManager.createCourse(
                    "Course $j",
                    "Short Description $j",
                    "Description $j",
                    author,
                    term
                )
            }
        }
    }

    @EventListener
    fun seed(event: ContextRefreshedEvent) {
        seedUsers()
        seedCourses()
    }
}