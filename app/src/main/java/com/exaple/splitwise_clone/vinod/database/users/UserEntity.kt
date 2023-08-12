package com.exaple.splitwise_clone.vinod.database.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: String?,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "gender") var gender: String,
    @ColumnInfo(name = "owes") var owes: String,
    @ColumnInfo(name = "owe") var owe: String,
    @ColumnInfo(name = "user_id") var user_id: Int

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}