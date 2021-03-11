package com.exaple.splitwise_clone.vinod.recyclerviews.contactlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import kotlinx.android.synthetic.main.friend_contact_item.view.*
import kotlin.math.abs

class ContactListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(entry: FriendTransactionEntity) {
        itemView.apply {
            tvFriendContactName.text = entry.name
            when {
                entry.amount == 0 -> {
                    tvSettledText.text = "settled up"
                }
                entry.amount > 0 -> {
                    tvSettledText.text = "owes you ${entry.amount}"
                }
                entry.amount < 0 -> {
                    tvSettledText.text = "you owe ${abs(entry.amount)}"
                }
            }
        }
    }
}