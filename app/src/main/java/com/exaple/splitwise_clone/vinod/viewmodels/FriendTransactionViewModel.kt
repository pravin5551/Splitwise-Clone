package com.exaple.splitwise_clone.vinod.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.repositories.FriendTransactionRepository

class FriendTransactionViewModel(val repository: FriendTransactionRepository) : ViewModel() {
    fun addFriendTransaction(entity: FriendTransactionEntity) {
        repository.addFriendTransaction(entity)
    }

    fun getFriendTransactionsList(): LiveData<List<FriendTransactionEntity>> {
        return repository.getFriendTransactionsList()
    }

    fun updateFriendTransaction(entity: FriendTransactionEntity) {
        repository.updateFriendTransaction(entity)
    }

    fun deleteFriendTransaction(entity: FriendTransactionEntity) {
        repository.deleteFriendTransaction(entity)
    }
}

class FriendTransactionViewModelFactory(val repository: FriendTransactionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FriendTransactionViewModel(repository) as T
    }
}