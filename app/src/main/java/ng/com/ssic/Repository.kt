package ng.com.ssic

import ng.com.ssic.model.*
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

    suspend fun getCoursesByDepartment(departmentCode : String): List<Course> =
        service.getCoursesByDepartment(departmentCode)

    suspend fun registerStudent(newStudent: NewStudent): RollNumber =
        service.registerStudent(newStudent)
}