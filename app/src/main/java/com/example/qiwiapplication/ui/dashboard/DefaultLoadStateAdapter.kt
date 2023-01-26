package com.example.qiwiapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.qiwiapplication.databinding.DefaultLoadStateBinding
import com.example.qiwiapplication.databinding.ItemTransactionBinding

typealias TryAgainAction = () -> Unit

class DefaultLoadStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<DefaultLoadStateAdapter.Holder>() {


    class Holder(
        private val binding: DefaultLoadStateBinding,
        private val swipeRefreshLayout: SwipeRefreshLayout?,
        private val tryAgainAction: TryAgainAction
    ): RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnTryAgain.setOnClickListener{tryAgainAction()}
        }
        fun bind(loadState: LoadState) = with(binding){
            tvMessage.isVisible = loadState is LoadState.Error
            btnTryAgain.isVisible = loadState is LoadState.Error
            if(swipeRefreshLayout != null){
                swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
                progressBar.isVisible = false
            }else{
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DefaultLoadStateBinding.inflate(inflater, parent, false)
        return Holder(binding, null, tryAgainAction)
    }
}