package com.exaple.splitwise_clone.vinod.database.friend_non_group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_transaction_table")
data class FriendTransactionEntity(
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "amount") var amount: Int,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "description") var description: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}