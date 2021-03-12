package com.exaple.splitwise_clone.vinod.repositories

import androidx.lifecycle.LiveData
import com.exaple.splitwise_clone.vinod.database.users.UserDAO
import com.exaple.splitwise_clone.vinod.database.users.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val DAO: UserDAO) {

    fun addUser(entity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            DAO.addUser(entity)
        }
    }

    fun getUserList(): LiveData<List<UserEntity>> {
        return DAO.getUserList()
    }

    fun updateUser(entity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            DAO.updateUser(entity)
        }
    }

    fun deleteUser(entity: UserEntity) {
        DAO.deleteUser(entity)
    }
}