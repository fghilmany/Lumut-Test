package com.fghilmany.mvvmstarterproject.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fghilmany.mvvmstarterproject.core.data.local.entity.TodosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM todos")
    fun getTodos(): Flow<List<TodosEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(emailEntity: List<TodosEntity>)

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getDetailTodos(id: String): Flow<TodosEntity>
}