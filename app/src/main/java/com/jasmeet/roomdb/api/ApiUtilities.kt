package com.jasmeet.roomdb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiUtilities {
    fun getInstance() : Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }
}