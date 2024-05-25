package com.example.testmvvmretrofit.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvmretrofit.data.repository.TextRepositoryImpl
import com.example.testmvvmretrofit.data.storage.network.HttpClient
import com.example.testmvvmretrofit.data.storage.network.NetworkStorageImpl
import com.example.testmvvmretrofit.domain.usecase.FetchPostUseCase
import com.example.testmvvmretrofit.domain.usecase.FetchUserUseCase
import com.example.testmvvmretrofit.domain.usecase.GetTextUseCase
import com.example.testmvvmretrofit.domain.usecase.SaveTextUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {
    private val networkStorage by lazy { NetworkStorageImpl(api = HttpClient.api) }
    private val textStorage by lazy { com.example.testmvvmretrofit.data.storage.sharedPrefs.TextStorageImpl(context = context) }
    private val textRepository by lazy { TextRepositoryImpl(textStorage = textStorage, networkStorage = networkStorage) }
    private val getTextUseCase by lazy { GetTextUseCase(textRepository = textRepository) }
    private val saveTextUseCase by lazy { SaveTextUseCase(textRepository = textRepository) }
    private val fetchUserUseCase by lazy { FetchUserUseCase(textRepository = textRepository) }
    private val fetchPostUseCase by lazy { FetchPostUseCase(textRepository = textRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getTextUseCase = getTextUseCase,
            saveTextUseCase = saveTextUseCase,
            fetchUserUseCase = fetchUserUseCase,
            fetchPostUseCase = fetchPostUseCase,
        ) as T
    }
}
