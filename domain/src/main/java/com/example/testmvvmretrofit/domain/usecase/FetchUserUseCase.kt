package com.example.testmvvmretrofit.domain.usecase

import com.example.testmvvmretrofit.domain.models.NetworkUserDomainModel
import com.example.testmvvmretrofit.domain.repository.TextRepository

class FetchUserUseCase(private val textRepository: TextRepository) {
    suspend fun execute(): NetworkUserDomainModel {
        return textRepository.fetchUser()
    }
}
