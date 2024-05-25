package com.example.testmvvmretrofit.data.storage

import com.example.testmvvmretrofit.data.storage.models.TextDataModel

interface TextStorage {
    fun save(text: TextDataModel): Boolean

    fun get(): TextDataModel
}
