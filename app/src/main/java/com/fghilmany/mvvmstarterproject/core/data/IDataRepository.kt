package com.fghilmany.mvvmstarterproject.core.data

import com.fghilmany.mvvmstarterproject.core.data.local.entity.TodosEntity
import com.fghilmany.mvvmstarterproject.core.data.remote.response.TodosResponseItem
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun getTodos(): Flow<Resource<List<TodosEntity>>>

    fun getDetailTodos(id: String): Flow<Resource<TodosResponseItem>>



}