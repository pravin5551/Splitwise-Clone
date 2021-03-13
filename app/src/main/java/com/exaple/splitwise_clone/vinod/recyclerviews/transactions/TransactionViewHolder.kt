package com.exaple.splitwise_clone.vinod.recyclerviews.transactions

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import kotlinx.android.synthetic.main.individual_transaction_item_layout.view.*

class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(transactio: TransactionEntity) {
        itemView.apply {
            tvCategory.text = transactio.description
            if (transactio.split_amt == 0) {
                tvPaidPrice.text = "Settled up"
                tvLentPrice.text = "INR(₹) ${transactio.split_amt}"
                tvLentPrice.setPaintFlags(tvLentPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            } else {
                tvPaidPrice.text = "You paid INR(₹) ${transactio.total_amt}"
                tvLentPrice.text = "INR(₹) ${transactio.split_amt}"
            }
        }
    }
}