package com.exaple.splitwise_clone.vinod.recyclerviews.grouptransaction

import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exaple.splitwise_clone.R
import com.exaple.splitwise_clone.sanjoy.GroupTransactionActivity
import com.exaple.splitwise_clone.sanjoy.SettleUpActivity
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import kotlinx.android.synthetic.main.friend_contact_item.view.*
import kotlinx.android.synthetic.main.individual_transaction_item_layout.view.*
import kotlin.math.abs

class GroupTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(entry: GroupEntity) {
        itemView.apply {
            tvFriendContactName.text = entry.name
            when {
                entry.amt == 0 -> {
                    tvSettledText.text = "settled up"
                }
                entry.amt > 0 -> {
                    tvSettledText.text = "owes you ${entry.amt}"
                }
                entry.amt < 0 -> {
                    tvSettledText.text = "you owe ${abs(entry.amt)}"
                }
            }
            itemView.ivFriendContact.setImageResource(R.drawable.ic_baseline_groups_24)
            tvFriendContactName.setOnClickListener {
                val intent2 = Intent(itemView.context, GroupTransactionActivity::class.java)
                intent2.putExtra("name", entry.name)
                intent2.putExtra("id", entry.id?.toInt())
                intent2.putExtra("amt", entry.amt)
                itemView.context.startActivity(intent2)
            }
        }
    }
}