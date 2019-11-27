package ng.com.ssic

import ng.com.ssic.model.Department
import ng.com.ssic.model.Faculty
import ng.com.ssic.network.SSICClient
import ng.com.ssic.network.SSICService

/**
 * This class handles data operations
 */
class Repository {

    private var service: SSICService = SSICClient.getService()

    suspend fun getAllFaculties(): List<Faculty> =
        service.getAllFaculties()

    suspend fun getDepartmentByFaculty(facultyCode : String): List<Department> =
        service.getDepartmentByFaculty(facultyCode)
}