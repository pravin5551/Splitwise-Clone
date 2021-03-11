package com.exaple.splitwise_clone.vinod.repositories

import androidx.lifecycle.LiveData
import com.exaple.splitwise_clone.vinod.database.groups.GroupDAO
import com.exaple.splitwise_clone.vinod.database.groups.GroupEntity
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionDAO
import com.exaple.splitwise_clone.vinod.database.transactions.TransactionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupTransactionRepository(val DAO: TransactionDAO) {

    fun addTransaction(entity: TransactionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            DAO.addTransaction(entity)
        }
    }

    fun getTransactionsList(): LiveData<List<TransactionEntity>> {
        lateinit var x: LiveData<List<TransactionEntity>>
        CoroutineScope(Dispatchers.IO).launch {
            x = DAO.getTransactionList()
        }
        return x
    }

    fun updateTransaction(entity: TransactionEntity) {
        DAO.updateTransaction(entity)
    }

    fun deleteTransaction(entity: TransactionEntity) {
        DAO.deleteTransaction(entity)
    }
}