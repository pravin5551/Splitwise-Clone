package com.exaple.splitwise_clone.vinod.repositories

import androidx.lifecycle.LiveData
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionDAO
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FriendTransactionRepository(val DAO: FriendTransactionDAO) {

    fun addFriendTransaction(entity: FriendTransactionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            DAO.addFriendTransaction(entity)
        }
    }

    fun getFriendTransactionsList(): LiveData<List<FriendTransactionEntity>> {
        lateinit var x: LiveData<List<FriendTransactionEntity>>
        CoroutineScope(Dispatchers.IO).launch {
            x = DAO.getFriendTransactionList()
        }
        return x
    }

    fun updateFriendTransaction(entity: FriendTransactionEntity) {
        DAO.updateFriendTransaction(entity)
    }

    fun deleteFriendTransaction(entity: FriendTransactionEntity) {
        DAO.deleteFriendTransaction(entity)
    }
}