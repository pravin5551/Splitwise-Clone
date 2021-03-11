package com.exaple.splitwise_clone.vinod.recyclerviews.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity

class ContactListAdapter(val friendEntries: List<FriendTransactionEntity>) :
    RecyclerView.Adapter<ContactListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_contact_item, parent, false)
        return ContactListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friendEntries.size
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val temp = friendEntries[position]
        holder.setData(temp)
    }
}