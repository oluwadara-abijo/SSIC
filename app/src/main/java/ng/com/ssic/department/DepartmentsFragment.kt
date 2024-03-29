package ng.com.ssic.department


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ng.com.ssic.MainViewModel
import ng.com.ssic.R
import ng.com.ssic.faculty.FacultiesFragmentDirections
import ng.com.ssic.model.Department
import ng.com.ssic.network.NetworkUtils
import ng.com.ssic.network.hideLoading
import ng.com.ssic.network.isNetworkAvailable
import ng.com.ssic.network.showLoading

/**
 * A simple [Fragment] subclass.
 */
class DepartmentsFragment : Fragment(),
    DepartmentsListAdapter.ItemClickListener {

    private val viewModel: MainViewModel by activityViewModels()
    private val args: DepartmentsFragmentArgs by navArgs()

    //UI elements
    private lateinit var navController: NavController
    private lateinit var progressBar: View
    private lateinit var recyclerView: RecyclerView

    // List of Departments
    private var departments: List<Department> = ArrayList()

    private lateinit var facultyCode : String

    private val departmentsListAdapter =
        DepartmentsListAdapter(departments, this)

    private lateinit var networkUtils: NetworkUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_departments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialise UI elements
        navController = findNavController()
        progressBar = view.findViewById(R.id.pb_loading_indicator)
        recyclerView = view.findViewById(R.id.rv_departments)

        networkUtils = NetworkUtils(activity, progressBar)

        facultyCode = args.facultyCode

        setupRecyclerView()
        getDepartments()
    }

    private fun getDepartments() {
        //Check if network is available
        if (networkUtils.isNetworkAvailable()) {
            networkUtils.showLoading()
            viewModel.getDepartmentByFaculty(facultyCode).observe(this, Observer { response ->
                departments = response
                if (departments.isEmpty()) {
                    displayMessage("No data to display")
                }
                departmentsListAdapter.setDepartments(departments)
                networkUtils.hideLoading()
            })
        } else {
            displayMessage("Check your internet connection")
        }
    }

    private fun setupRecyclerView() {
        //Set up recycler view
        val layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = departmentsListAdapter
        recyclerView.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.hasFixedSize()
    }

    private fun displayMessage(text: String) {
        Snackbar.make(progressBar, text, Snackbar.LENGTH_SHORT).show()
    }

    override fun onItemClick(department: Department) {
        val action = DepartmentsFragmentDirections.actionDepartmentsFragmentToCoursesFragment(department.code)
        navController.navigate(action)
    }


}
