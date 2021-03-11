package com.exaple.splitwise_clone.vinod.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import com.exaple.splitwise_clone.vinod.repositories.GroupRepository
import com.exaple.splitwise_clone.vinod.repositories.UserRepository

class GroupViewModel(val repository: GroupRepository) : ViewModel() {
    fun addGroup(entity: GroupEntity) {
        repository.addGroup(entity)
    }

    fun getGroupList(): LiveData<List<GroupEntity>> {
        return repository.getGroupList()
    }

    fun updateGroup(entity: GroupEntity) {
        repository.updateGroup(entity)
    }

    fun deleteGroup(entity: GroupEntity) {
        repository.deleteGroup(entity)
    }
}

class GroupViewModelFactory(val repository: GroupRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroupViewModel(repository) as T
    }
}