package ng.com.ssic.network

import ng.com.ssic.Faculty
import retrofit2.http.GET

/**
 * This interface defines every endpoint in the backend API
 */
interface SSICService {

    @GET("faculties")
    suspend fun getAllFaculties() : List<Faculty>

}