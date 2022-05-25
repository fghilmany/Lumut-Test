package com.fghilmany.mvvmstarterproject.core.data

import com.fghilmany.mvvmstarterproject.core.data.local.LocalDatasource
import com.fghilmany.mvvmstarterproject.core.data.local.entity.TodosEntity
import com.fghilmany.mvvmstarterproject.core.data.remote.RemoteDatasource
import com.fghilmany.mvvmstarterproject.core.data.remote.network.ApiResponse
import com.fghilmany.mvvmstarterproject.core.data.remote.response.TodosResponse
import com.fghilmany.mvvmstarterproject.core.data.remote.response.TodosResponseItem
import com.fghilmany.mvvmstarterproject.core.utils.PreferenceProvider
import kotlinx.coroutines.flow.Flow

class DataRepository (
    private val remoteDatasource: RemoteDatasource,
    private val localDatasource: LocalDatasource,
    private val preferenceProvider: PreferenceProvider
): IDataRepository {

    override fun getTodos(): Flow<Resource<List<TodosEntity>>> {
        return object : NetworkBoundResource<List<TodosEntity>, List<TodosResponseItem>>(){
            override fun loadFromDB(): Flow<List<TodosEntity>> {
                return localDatasource.getTodos()
            }

            override fun shouldFetch(data: List<TodosEntity>?): Boolean {
                return data == null || data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TodosResponseItem>>> {
                return remoteDatasource.getTodos()
            }

            override suspend fun saveCallResult(data: List<TodosResponseItem>) {
                val map = data.map {
                    with(it){
                        TodosEntity(
                            id?:0, completed, title, userId
                        )
                    }
                }
                localDatasource.insertTodos(map)
            }

        }.asFlow()
    }


    override fun getDetailTodos(id: String): Flow<Resource<TodosResponseItem>> {
        return object : OnlineBoundResource<TodosResponseItem>(){
            override suspend fun createCall(): Flow<ApiResponse<TodosResponseItem>> {
                return remoteDatasource.getDetailTodos(id)
            }

            override fun getResponse(data: TodosResponseItem) {

            }

        }.asFlow()
    }


}