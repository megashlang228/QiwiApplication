package com.example.qiwiapplication.ui.login

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.qiwiapplication.R
import com.example.qiwiapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.personInfo.observe(viewLifecycleOwner){
            Log.d("PersonInfo", it.toString())
            val result = findNavController().popBackStack(R.id.navigation_auth, true)
            if (result.not()) {
                findNavController().navigate(R.id.mainFragment)
            }
        }


        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "ошибка", Toast.LENGTH_LONG).show()
        }


        binding.btnLogin.setOnClickListener {
            val number = binding.etNumberPhone.text.toString()
            val token = binding.etToken.text.toString()
            if(validateInput(number, token)){
                viewModel.getPersonInfo(number, token)
            }else{
                Toast.makeText(requireContext(), "некорректный номер телефона", Toast.LENGTH_SHORT).show()
            }
        }
        binding.getToken.setOnClickListener {
            val address: Uri = Uri.parse("https://qiwi.com/api")
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)

            if (openLinkIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(openLinkIntent)
            } else {
                Log.d("Intent", "Не получается обработать намерение!")
            }
        }
    }

    private fun validateInput(number: String, token: String): Boolean {
        if(number.length != 11) { return false }
        try { number.toLong() }
        catch (e: Exception) { return false }

        return true
    }


}