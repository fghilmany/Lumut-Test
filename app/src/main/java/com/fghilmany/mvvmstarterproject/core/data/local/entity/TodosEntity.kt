package com.fghilmany.mvvmstarterproject.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodosEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "completed")
    val completed: Boolean? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "userId")
    val userId: Int? = null
)