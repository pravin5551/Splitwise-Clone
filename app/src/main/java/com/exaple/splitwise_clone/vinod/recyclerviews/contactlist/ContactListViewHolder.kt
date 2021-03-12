package com.exaple.splitwise_clone.vinod.recyclerviews.contactlist

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.sanjoy.SettleUpActivity
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
            tvFriendContactName.setOnClickListener {
                val intent2 = Intent(itemView.context, SettleUpActivity::class.java)
                intent2.putExtra("name", entry.name)
                intent2.putExtra("id", entry.id?.toInt())
                intent2.putExtra("amt", entry.amount)
                intent2.putExtra("number", entry.number)
                itemView.context.startActivity(intent2)
            }
        }
    }
}