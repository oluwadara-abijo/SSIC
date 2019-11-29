package ng.com.ssic.register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ng.com.ssic.MainViewModel
import ng.com.ssic.R
import ng.com.ssic.network.NetworkUtils

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    //UI elements
    private lateinit var navController: NavController
    private lateinit var progressBar: View

    private lateinit var networkUtils: NetworkUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialise UI elements
        navController = findNavController()
        progressBar = view.findViewById(R.id.pb_loading_indicator)

        networkUtils = NetworkUtils(activity, progressBar)

    }

    private fun displayMessage(text: String) {
        Snackbar.make(progressBar, text, Snackbar.LENGTH_SHORT).show()
    }


}
