package com.example.qiwiapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qiwiapplication.databinding.ItemTransactionBinding
import com.example.qiwiapplication.model.Transactoin

class TransactionAdapter: PagingDataAdapter<Transactoin, TransactionAdapter.Holder>(TransactionDiffCallback()) {
    class Holder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = getItem(position) ?: return
        with(holder.binding){
            if(user.providerIcon != null){
                Glide
                    .with(imgProvider.context)
                    .load(user.providerIcon)
                    .into(imgProvider)
            }
            tvDate.text = user.date
            tvProvider.text = user.providerName
            tvSum.text = user.sum.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }


}
class TransactionDiffCallback : DiffUtil.ItemCallback<Transactoin>(){
    override fun areItemsTheSame(oldItem: Transactoin, newItem: Transactoin): Boolean {
        return oldItem.tnxId == newItem.tnxId
    }

    override fun areContentsTheSame(oldItem: Transactoin, newItem: Transactoin): Boolean {
        return oldItem == newItem
    }

}