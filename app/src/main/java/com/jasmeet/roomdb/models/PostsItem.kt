package com.jasmeet.roomdb.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")

data class PostsItem(
    val body: String?,
    @PrimaryKey
    val id: Int,
    val title: String?,
    val userId: Int?
)