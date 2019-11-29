package ng.com.ssic.model

/**
 * This models the request body sent to the register student endpoint
 */
data class NewStudent(
    val address: String,
    val age: Int,
    val departmentCode: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: String = "",
    val lastName: String,
    val middleName: String = "",
    val mobile: String,
    val rollNo: String = ""



    )