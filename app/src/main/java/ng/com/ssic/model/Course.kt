package ng.com.ssic.model

/**
 * This models the course object returned from the server
 */
data class Course(
    val name: String,
    val unit: Int,
    val description: String,
    val code: String
)