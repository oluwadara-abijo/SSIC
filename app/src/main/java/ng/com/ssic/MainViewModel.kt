package ng.com.ssic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ng.com.ssic.model.Course
import ng.com.ssic.model.Department
import ng.com.ssic.model.Faculty

class MainViewModel : ViewModel() {
    private val repository = Repository()

    fun getAllFaculties(): LiveData<List<Faculty>> =
        liveData(Dispatchers.IO) {
            val response = repository.getAllFaculties()
            emit(response)
        }

    fun getDepartmentByFaculty(facultyCode : String): LiveData<List<Department>> =
        liveData(Dispatchers.IO) {
            val response = repository.getDepartmentByFaculty(facultyCode)
            emit(response)
        }

    fun getCoursesByDepartment(departmentCode : String): LiveData<List<Course>> =
        liveData(Dispatchers.IO) {
            val response = repository.getCoursesByDepartment(departmentCode)
            emit(response)
        }
}