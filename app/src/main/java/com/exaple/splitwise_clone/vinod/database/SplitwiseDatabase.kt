package com.exaple.splitwise_clone.vinod.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionDAO
import com.exaple.splitwise_clone.vinod.database.friend_non_group.FriendTransactionEntity
import com.exaple.splitwise_clone.vinod.database.groups.GroupDAO
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionDAO
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import com.exaple.splitwise_clone.vinod.database.users.UserDAO
import com.exaple.splitwise_clone.vinod.database.users.UserEntity


@Database(entities = arrayOf(UserEntity::class,GroupEntity::class,
    TransactionEntity::class, FriendTransactionEntity::class), version = 1)
abstract class SplitwiseDatabase() : RoomDatabase() {
    abstract fun getMyUserEntries(): UserDAO
    abstract fun getMyGroupEntries(): GroupDAO
    abstract fun getMyTransactionEntries(): TransactionDAO
    abstract fun getMyFriendTransactionEntries(): FriendTransactionDAO

    companion object {
        private var INSTANCE: SplitwiseDatabase? = null
        fun getDatabase(context: Context): SplitwiseDatabase {
            if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    SplitwiseDatabase::class.java,
                    "splitwise_db"
                )
                builder.fallbackToDestructiveMigration()
                return builder.build()
            } else {
                return INSTANCE!!
            }
        }
    }
}