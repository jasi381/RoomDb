package com.jasmeet.roomdb.api

import com.jasmeet.roomdb.models.PostsItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface ApiInterface {

    @GET("/posts")
    fun getPost() : Call<List<PostsItem>>
}

object RetrofitService{
    val retrofitInstance : ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInstance = retrofit.create(ApiInterface::class.java)
    }
}