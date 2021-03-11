package com.exaple.splitwise_clone.vinod.database.friend_non_group

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FriendTransactionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFriendTransaction(friendTransactionEntity: FriendTransactionEntity)

    @Query("select * from friend_transaction_table")
    fun getFriendTransactionList(): LiveData<List<FriendTransactionEntity>>

    @Update
    fun updateFriendTransaction(friendTransactionEntity: FriendTransactionEntity)

    @Delete
    fun deleteFriendTransaction(friendTransactionEntity: FriendTransactionEntity)
}