package ng.com.ssic.model

/**
 * This models the department object returned from the server
 */
data class Department(
    val code: String,
    val name: String,
    val faculty: Faculty,
    val description: String
)