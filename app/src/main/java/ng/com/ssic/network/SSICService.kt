package ng.com.ssic.network

import ng.com.ssic.model.Department
import ng.com.ssic.model.Faculty
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This interface defines every endpoint in the backend API
 */
interface SSICService {

    @GET("faculties")
    suspend fun getAllFaculties(): List<Faculty>

    @GET("faculties/{code}/departments")
    suspend fun getDepartmentByFaculty(@Path("code") facultyCode: String): List<Department>
}