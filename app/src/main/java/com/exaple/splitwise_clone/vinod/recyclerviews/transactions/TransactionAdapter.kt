package com.exaple.splitwise_clone.vinod.recyclerviews.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity

class TransactionAdapter(val itemList: List<TransactionEntity>) :
    RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_transaction_item_layout, parent, false)
        return TransactionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = itemList[position]
        holder.setData(item)
    }

}