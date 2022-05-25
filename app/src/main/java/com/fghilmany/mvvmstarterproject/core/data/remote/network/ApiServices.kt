package com.fghilmany.mvvmstarterproject.core.data.remote.network

import com.fghilmany.mvvmstarterproject.core.data.remote.response.TodosResponse
import com.fghilmany.mvvmstarterproject.core.data.remote.response.TodosResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("todos")
    suspend fun getTodos(): List<TodosResponseItem>

    @GET("todos/{id}")
    suspend fun getDetailTodos(
        @Path("id")
        id: String
    ): TodosResponseItem

}