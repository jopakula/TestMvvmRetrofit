package com.example.testmvvmretrofit.domain.usecase

import com.example.testmvvmretrofit.domain.models.TextDomainModel
import com.example.testmvvmretrofit.domain.repository.TextRepository

class SaveTextUseCase(private val textRepository: TextRepository) {
    fun execute(text: TextDomainModel): Boolean {
        val oldText = textRepository.getText()
        if (oldText.text == text.text) {
            return false
        }
        return textRepository.saveText(saveText = text)
    }
}
