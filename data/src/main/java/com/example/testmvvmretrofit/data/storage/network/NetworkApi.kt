package com.example.testmvvmretrofit.data.storage.network

import com.example.testmvvmretrofit.data.storage.models.NetworkPostDataModel
import com.example.testmvvmretrofit.data.storage.models.NetworkUserDataModel
import retrofit2.http.GET

interface NetworkApi {
    @GET("todos/1")
    suspend fun getUser(): NetworkUserDataModel

    @GET("posts")
    suspend fun getPosts(): List<NetworkPostDataModel>
}
