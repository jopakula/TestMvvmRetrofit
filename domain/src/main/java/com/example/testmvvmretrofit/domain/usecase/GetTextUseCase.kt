package com.example.testmvvmretrofit.domain.usecase

import com.example.testmvvmretrofit.domain.models.TextDomainModel
import com.example.testmvvmretrofit.domain.repository.TextRepository

class GetTextUseCase(private val textRepository: TextRepository) {
    fun execute(): TextDomainModel {
        return textRepository.getText()
    }
}
