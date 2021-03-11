package com.exaple.splitwise_clone.vinod.database.transactions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @ColumnInfo(name = "group_id") var group_id: Int,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "split_amt") var split_amt: Int,
    @ColumnInfo(name = "total_amt") var total_amt: Int,
    @ColumnInfo(name = "cur") var cur: String,
    @ColumnInfo(name = "description") var description: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}