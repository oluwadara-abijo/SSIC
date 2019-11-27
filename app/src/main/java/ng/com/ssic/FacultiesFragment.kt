package ng.com.ssic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class FacultiesFragment : Fragment(), FacultiesListAdapter.ItemClickListener {

    private val viewModel: MainViewModel by activityViewModels()

    //UI elements
    private lateinit var navController: NavController
    private lateinit var progressBar: View
    private lateinit var recyclerView: RecyclerView

    // List of faculties
    private var faculties: List<Faculty> = ArrayList()

    private val facultiesListAdapter = FacultiesListAdapter(faculties, this)

    private lateinit var networkUtils: NetworkUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faculties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialise UI elements
        navController = findNavController()
        progressBar = view.findViewById(R.id.pb_loading_indicator)
        recyclerView = view.findViewById(R.id.rv_faculties)

        networkUtils = NetworkUtils(activity, progressBar)

        setupRecyclerView()
        getFaculties()
    }

    private fun getFaculties() {
        //Check if network is available
        if (networkUtils.isNetworkAvailable()) {
            networkUtils.showLoading()
            viewModel.getAllFaculties().observe(this, Observer { response ->
                faculties = response
                if (faculties.isEmpty()) {
                    displayMessage("No data to display")
                }
                facultiesListAdapter.setFaculties(faculties)
                networkUtils.hideLoading()
            })
        } else {
            displayMessage("Check your internet connection")
        }
    }

    private fun setupRecyclerView() {
        //Set up recycler view
        val layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = facultiesListAdapter
        recyclerView.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.hasFixedSize()
    }

    private fun displayMessage(text: String) {
        Snackbar.make(progressBar, text, Snackbar.LENGTH_SHORT)
    }

    override fun onItemClick(faculty: Faculty) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
