package com.fghilmany.mvvmstarterproject.core.data.local

import com.fghilmany.mvvmstarterproject.core.data.local.entity.TodosEntity
import com.fghilmany.mvvmstarterproject.core.data.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDatasource (
    private val dao: Dao
) {
    fun getTodos(): Flow<List<TodosEntity>> = dao.getTodos()

    suspend fun insertTodos(emailEntity: List<TodosEntity>) {
        return dao.insertTodos(emailEntity)
    }
}