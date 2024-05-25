package com.example.testmvvmretrofit.data.storage

import com.example.testmvvmretrofit.data.storage.models.NetworkPostDataModel
import com.example.testmvvmretrofit.data.storage.models.NetworkUserDataModel

interface NetworkStorage {
    suspend fun getNetworkUserData(): NetworkUserDataModel

    suspend fun getNetworkPostData(): List<NetworkPostDataModel>
}
