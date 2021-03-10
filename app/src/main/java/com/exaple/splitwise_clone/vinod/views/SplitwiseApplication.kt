package com.exaple.splitwise_clone.vinod.views

import android.app.Application
import com.exaple.splitwise_clone.vinod.database.SplitwiseDatabase
import com.exaple.splitwise_clone.vinod.repositories.FriendTransactionRepository
import com.exaple.splitwise_clone.vinod.repositories.GroupRepository
import com.exaple.splitwise_clone.vinod.repositories.GroupTransactionRepository
import com.exaple.splitwise_clone.vinod.repositories.UserRepository

class SplitwiseApplication : Application() {
    val userDAO by lazy {
        val roomDatabase = SplitwiseDatabase.getDatabase(this)
        roomDatabase.getMyUserEntries()
    }
    val groupDAO by lazy {
        val roomDatabase = SplitwiseDatabase.getDatabase(this)
        roomDatabase.getMyGroupEntries()
    }
    val friendsDAO by lazy {
        val roomDatabase = SplitwiseDatabase.getDatabase(this)
        roomDatabase.getMyFriendTransactionEntries()
    }
    val transactionDAO by lazy {
        val roomDatabase = SplitwiseDatabase.getDatabase(this)
        roomDatabase.getMyTransactionEntries()
    }

    val userRepository by lazy {
        UserRepository(userDAO)
    }
    val groupRepository by lazy {
        GroupRepository(groupDAO)
    }
    val transactionRepository by lazy {
        GroupTransactionRepository(transactionDAO)
    }
    val friendsRepository by lazy {
        FriendTransactionRepository(friendsDAO)
    }


}