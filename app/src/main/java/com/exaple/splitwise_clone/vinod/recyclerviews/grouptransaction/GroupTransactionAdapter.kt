package com.exaple.splitwise_clone.vinod.recyclerviews.grouptransaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity

class GroupTransactionAdapter(val itemList: List<GroupEntity>) :
    RecyclerView.Adapter<GroupTransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupTransactionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_contact_item, parent, false)
        return GroupTransactionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: GroupTransactionViewHolder, position: Int) {
        val item = itemList[position]
        holder.setData(item)
    }
}