package com.udacity.asteroidradar.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.adapter.AsteroidAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel

//    private val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel
        binding.asteroidRecycler.adapter = AsteroidAdapter(AsteroidAdapter.OnClickListener {
            mainViewModel.displayPropertyDetails(it)

        })

        mainViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it)
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                mainViewModel.displayPropertyDetailsComplete()
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.week_asteroid -> mainViewModel.onViewWeekAsteroidsClicked()
            R.id.today_asteroid -> mainViewModel.onTodayAsteroidsClicked()
            R.id.save_asteroid -> mainViewModel.onSavedAsteroidsClicked()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
