package com.example.qiwiapplication.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.qiwiapplication.R
import com.example.qiwiapplication.databinding.FragmentProfileBinding
import com.example.qiwiapplication.utils.SharedPref

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.personInfo.observe(viewLifecycleOwner) {
            with(binding){
                tvFioProfile.text = "${it.lastName} ${it.firstName[0]}. ${it.middleName[0]}."
                tvBirthdayProfile.text = it.birthDate
                tvPasportProfile.text = it.passport
                tvSnilsProfile.text = it.snils
                twNumberProfile.text = "+${it.id}"

            }
        }

        binding.btnLogout.setOnClickListener {
            Log.e("logout", "logout")
            SharedPref.clearPersonId()
            SharedPref.clearToken()

            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
                .navigate(R.id.action_mainFragment_to_splashFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}