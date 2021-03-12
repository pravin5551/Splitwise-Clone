package com.exaple.splitwise_clone.vinod.recyclerviews.friendtransaction

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import kotlinx.android.synthetic.main.individual_transaction_item_layout.view.*

class FriendTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(friendTransactionEntity: FriendTransactionEntity) {
        itemView.apply {
            tvCategory.text = friendTransactionEntity.description
            if (friendTransactionEntity.amount == 0) {
                tvPaidPrice.text = "Settled up"
                tvLentPrice.text = "INR(₹) ${friendTransactionEntity.amount}"
                tvLentPrice.setPaintFlags(tvLentPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            } else {
                tvPaidPrice.text = "You paid INR(₹) ${2 * friendTransactionEntity.amount}"
                tvLentPrice.text = "INR(₹) ${friendTransactionEntity.amount}"
            }
        }
    }
}