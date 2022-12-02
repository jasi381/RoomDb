package com.jasmeet.roomdb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jasmeet.roomdb.models.PostsItem

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(posts: PostsItem)

    @Delete
    suspend fun delete(posts: PostsItem)

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostsItem>>

}