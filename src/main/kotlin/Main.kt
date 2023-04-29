import java.time.LocalDate

enum class Level { BASIC, INTERMEDIATE, DIFFICULT }

class User(val name: String)

data class EducationalContent(val name: String, val durationInMinutes: Int = 60, val description: String)

class Education(val name: String, var level: Level, var contents: List<EducationalContent>,
                var startDate: LocalDate, var endDate: LocalDate, var instructor: String, var contactInfo: String) {

    val enrolledUsers = mutableListOf<User>()

    fun enroll(user: User) {
        enrolledUsers.add(user)
        println("User ${user.name} enrolled in education $name")
    }

    fun unenroll(user: User) {
        enrolledUsers.remove(user)
        println("User ${user.name} unenrolled from education $name")
    }

    fun listEnrolledUsers(): List<User> {
        return enrolledUsers
    }

    fun getTotalDuration(): Int {
        return contents.sumOf { it.durationInMinutes }
    }

    fun getAverageDuration(): Double {
        return contents.map { it.durationInMinutes }.average()
    }

    override fun toString(): String {
        return "Education(name='$name', level=$level, startDate=$startDate, endDate=$endDate, instructor='$instructor', contactInfo='$contactInfo')"
    }
}

fun main() {
    val user1 = User("Dane Hartman")
    val user2 = User("Robin Berry")
    val user3 = User("Morgan Chaney")

    val content1 = EducationalContent("Introduction to Kotlin",
        120,
        "Learn the basics of Kotlin programming language"
    )
    val content2 = EducationalContent(
        "Object-Oriented Programming in Kotlin",
        description = "Understand the principles of OOP in Kotlin"
    )
    val content3 = EducationalContent("Android Development with Kotlin",
        180,
        "Build Android apps using Kotlin"
    )

    val startDate = LocalDate.of(2023, 5, 1)
    val endDate = LocalDate.of(2023, 5, 31)

    val education1 = Education(
        "Kotlin For Beginners",
        Level.BASIC,
        listOf(content1, content2),
        startDate,
        endDate,
        "John Doe",
        "john.doe@dio.me"
    )
    val education2 = Education(
        "Android Development with Kotlin",
        Level.DIFFICULT,
        listOf(content1, content2, content3),
        startDate,
        endDate,
        "Alisha Wood",
        "alisha.wood@dio.me"
    )

    education1.enroll(user1)
    education1.enroll(user2)
    education2.enroll(user2)
    education2.enroll(user3)

    println(
        "Enrolled users in education '${education1.name}': ${
            education1.listEnrolledUsers().joinToString(", ") { it.name }
        }"
    )
    println(
        "Enrolled users in education '${education2.name}': ${
            education2.listEnrolledUsers().joinToString(", ") { it.name }
        }"
    )
    println("Total duration of education '${education1.name}': ${education1.getTotalDuration()} minutes")
    println("Total duration of education '${education2.name}': ${education2.getTotalDuration()} minutes")
    println("Average duration of education '${education1.name}': ${education1.getAverageDuration()} minutes")
}
   
