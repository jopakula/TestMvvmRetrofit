package com.example.testmvvmretrofit.data.repository

import com.example.testmvvmretrofit.data.storage.NetworkStorage
import com.example.testmvvmretrofit.data.storage.TextStorage
import com.example.testmvvmretrofit.data.storage.models.NetworkPostDataModel
import com.example.testmvvmretrofit.data.storage.models.NetworkUserDataModel
import com.example.testmvvmretrofit.data.storage.models.TextDataModel
import com.example.testmvvmretrofit.domain.models.NetworkPostDomainModel
import com.example.testmvvmretrofit.domain.models.NetworkUserDomainModel
import com.example.testmvvmretrofit.domain.models.TextDomainModel
import com.example.testmvvmretrofit.domain.repository.TextRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TextRepositoryImpl(private val textStorage: TextStorage, private val networkStorage: NetworkStorage) : TextRepository {
    override fun saveText(saveText: TextDomainModel): Boolean {
        val textData = mapToStorage(saveText)
        return textStorage.save(text = textData)
    }

    override fun getText(): TextDomainModel {
        val textData = textStorage.get()
        return mapToDomain(text = textData)
    }

    override suspend fun fetchUser(): NetworkUserDomainModel {
        return withContext(Dispatchers.IO) {
            val userData = networkStorage.getNetworkUserData()
            mapNetworkUserToDomain(userData)
        }
    }

    override suspend fun fetchPost(): List<NetworkPostDomainModel> {
        return withContext(Dispatchers.IO) {
            val postDataList = networkStorage.getNetworkPostData()
            postDataList.map { mapNetworkPostToDomain(it) }
        }
    }

    private fun mapToStorage(text: TextDomainModel): TextDataModel {
        return TextDataModel(text = text.text)
    }

    private fun mapToDomain(text: TextDataModel): TextDomainModel {
        return TextDomainModel(text = text.text)
    }

    private fun mapNetworkUserToDomain(text: NetworkUserDataModel): NetworkUserDomainModel {
        return NetworkUserDomainModel(
            userId = text.userId,
            id = text.id,
            title = text.title,
            completed = text.completed,
        )
    }

    private fun mapNetworkPostToDomain(text: NetworkPostDataModel): NetworkPostDomainModel {
        return NetworkPostDomainModel(
            userId = text.userId,
            id = text.id,
            title = text.title,
            body = text.body,
        )
    }
}
