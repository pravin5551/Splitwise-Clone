package com.exaple.splitwise_clone.vinod.database.groups

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroupDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGroup(groupEntity: GroupEntity)

    @Query("select * from group_table")
    fun getGroupList(): LiveData<List<GroupEntity>>

    @Update
    fun updateGroup(groupEntity: GroupEntity)

    @Delete
    fun deleteGroup(groupEntity: GroupEntity)
}