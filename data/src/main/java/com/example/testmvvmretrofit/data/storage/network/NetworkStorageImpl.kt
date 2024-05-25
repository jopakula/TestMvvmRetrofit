package com.example.testmvvmretrofit.data.storage.network

import com.example.testmvvmretrofit.data.storage.NetworkStorage
import com.example.testmvvmretrofit.data.storage.models.NetworkPostDataModel
import com.example.testmvvmretrofit.data.storage.models.NetworkUserDataModel

class NetworkStorageImpl(private val api: NetworkApi) : NetworkStorage {
    override suspend fun getNetworkUserData(): NetworkUserDataModel {
        return api.getUser()
    }

    override suspend fun getNetworkPostData(): List<NetworkPostDataModel> {
        return api.getPosts()
    }
}
