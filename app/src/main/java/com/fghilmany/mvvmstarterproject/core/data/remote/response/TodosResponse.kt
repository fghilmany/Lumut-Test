package com.fghilmany.mvvmstarterproject.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TodosResponse(

	@field:SerializedName("TodosResponse")
	val todosResponse: List<TodosResponseItem?>? = null
)

data class TodosResponseItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("completed")
	val completed: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
