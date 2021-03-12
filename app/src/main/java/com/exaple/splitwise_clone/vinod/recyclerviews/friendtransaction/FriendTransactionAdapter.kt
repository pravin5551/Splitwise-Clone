package com.exaple.splitwise_clone.vinod.recyclerviews.friendtransaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity

class FriendTransactionAdapter(val itemList: List<FriendTransactionEntity>) :
    RecyclerView.Adapter<FriendTransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendTransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_transaction_item_layout, parent, false)
        return FriendTransactionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: FriendTransactionViewHolder, position: Int) {
        val item = itemList[position]
        holder.setData(item)
    }

}