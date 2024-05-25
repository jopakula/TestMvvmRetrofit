package com.example.testmvvmretrofit.domain.usecase

import com.example.testmvvmretrofit.domain.models.NetworkPostDomainModel
import com.example.testmvvmretrofit.domain.repository.TextRepository

class FetchPostUseCase(private val textRepository: TextRepository) {
    suspend fun execute(): List<NetworkPostDomainModel> {
        return textRepository.fetchPost()
    }
}
