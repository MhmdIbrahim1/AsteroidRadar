package com.udacity.asteroidradar.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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
    private val mAdapter by lazy { AsteroidAdapter(AsteroidAdapter.OnClickListener{
        mainViewModel.displayPropertyDetails(it)
    }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel
        setUpRecyclerView()

        mainViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) { data ->
            if ( null != data ) {
                val action = MainFragmentDirections.actionShowDetail(data)
                this.findNavController().navigate(action)
                mainViewModel.displayPropertyDetailsComplete()
            }
        }


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.week_asteroid -> {
                        mainViewModel.onViewWeekAsteroidsClicked()
                        true
                    }
                    R.id.today_asteroid -> {
                        mainViewModel.onTodayAsteroidsClicked()
                        true
                    }
                    R.id.save_asteroid ->{
                        mainViewModel.onSavedAsteroidsClicked()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    private fun setUpRecyclerView() {
        binding.asteroidRecycler.adapter =mAdapter
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
