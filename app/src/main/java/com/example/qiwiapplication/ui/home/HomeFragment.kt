package com.example.qiwiapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.qiwiapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        viewModel.text.observe(viewLifecycleOwner) {
            Log.e("qqqqqqqqqqqqqq", it.toString())
            if (it.accounts.isNotEmpty()){
                if (it.accounts.first().balance != null) {
                    val amount = it.accounts.first().balance?.amount.toString()
                    val currency = when(it.accounts.first().balance?.currency.toString()){
                        "643" -> "₽"
                        "840" -> "$"
                        "978" -> "€"
                        else -> "valuta"
                    }

                    binding.refreshBalance.isRefreshing = false
                    binding.tvBalanceHome.text = "$amount $currency"
                }

            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchPayments()
        binding.refreshBalance.setOnRefreshListener {
            viewModel.fetchPayments()
        }
    }
}