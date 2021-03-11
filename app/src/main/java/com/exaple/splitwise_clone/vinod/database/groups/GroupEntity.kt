package com.exaple.splitwise_clone.vinod.database.groups

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class GroupEntity(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "img") var img: String?,
    @ColumnInfo(name = "contacts") var contactList: String?,
    @ColumnInfo(name = "time") var time: String?,
    @ColumnInfo(name = "creator") var creator: Int
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}