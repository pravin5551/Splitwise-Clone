package com.exaple.splitwise_clone.vinod.repositories

import androidx.lifecycle.LiveData
import com.exaple.splitwise_clone.vinod.database.groups.GroupDAO
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.users.UserDAO
import com.exaple.splitwise_clone.vinod.database.users.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupRepository(val DAO: GroupDAO) {

    fun addGroup(entity: GroupEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            DAO.addGroup(entity)
        }
    }

    fun getGroupList(): LiveData<List<GroupEntity>> {
        lateinit var x: LiveData<List<GroupEntity>>
        CoroutineScope(Dispatchers.IO).launch {
            x = DAO.getGroupList()
        }
        return x
    }

    fun updateGroup(entity: GroupEntity) {
        DAO.updateGroup(entity)
    }

    fun deleteGroup(entity: GroupEntity) {
        DAO.deleteGroup(entity)
    }
}