package com.example.testmvvmretrofit.domain.repository

import com.example.testmvvmretrofit.domain.models.NetworkPostDomainModel
import com.example.testmvvmretrofit.domain.models.NetworkUserDomainModel
import com.example.testmvvmretrofit.domain.models.TextDomainModel

interface TextRepository {
    fun saveText(saveText: TextDomainModel): Boolean

    fun getText(): TextDomainModel

    suspend fun fetchUser(): NetworkUserDomainModel

    suspend fun fetchPost(): List<NetworkPostDomainModel>
}
