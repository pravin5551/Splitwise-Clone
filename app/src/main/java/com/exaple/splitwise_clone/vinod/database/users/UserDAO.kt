package com.exaple.splitwise_clone.vinod.database.users

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntity: UserEntity)

    @Query("select * from user_table")
    fun getUserList(): LiveData<List<UserEntity>>

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

}

