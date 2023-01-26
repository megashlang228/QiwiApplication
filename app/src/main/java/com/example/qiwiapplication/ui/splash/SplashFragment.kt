package com.example.qiwiapplication.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.qiwiapplication.R


class SplashFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        viewModel.splashNavCommand.observe(viewLifecycleOwner){

            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)

            val mainGraph = navController.navInflater.inflate(R.navigation.login_navigation)

            val frag = when(it){
                SplashNavCommand.NAVIGATE_TO_AUTH -> R.id.navigation_auth
                SplashNavCommand.NAVIGATE_TO_MAIN -> R.id.mainFragment
                else -> throw RuntimeException("Unknown parameter SplashNamCommand: ${it.toString()}")
            }

            mainGraph.setStartDestination(frag)

            navController.graph = mainGraph



//            when(it){
//                SplashNavCommand.NAVIGATE_TO_AUTH -> findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
//                SplashNavCommand.NAVIGATE_TO_MAIN -> findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
//                else -> throw RuntimeException("Unknown parameter SplashNamCommand: ${it.toString()}")
//            }
        }
    }
}