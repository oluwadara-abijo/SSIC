package ng.com.ssic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    private val repository = Repository()

    fun getAllFaculties(): LiveData<List<Faculty>> =
        liveData(Dispatchers.IO) {
            val response = repository.getAllFaculties()
            emit(response)
        }
}