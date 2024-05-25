package com.example.testmvvmretrofit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmvvmretrofit.domain.models.NetworkPostDomainModel
import com.example.testmvvmretrofit.domain.models.NetworkUserDomainModel
import com.example.testmvvmretrofit.domain.models.TextDomainModel
import com.example.testmvvmretrofit.domain.usecase.FetchPostUseCase
import com.example.testmvvmretrofit.domain.usecase.FetchUserUseCase
import com.example.testmvvmretrofit.domain.usecase.GetTextUseCase
import com.example.testmvvmretrofit.domain.usecase.SaveTextUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getTextUseCase: GetTextUseCase,
    private val saveTextUseCase: SaveTextUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchPostUseCase: FetchPostUseCase,
) : ViewModel() {
    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive
    private val _listLive = MutableLiveData<List<NetworkPostDomainModel>>()
    val listLive: LiveData<List<NetworkPostDomainModel>> = _listLive

    fun save(text: String) {
        val textParam = TextDomainModel(text)
        val result: Boolean = saveTextUseCase.execute(text = textParam)
        _resultLive.value = "Save result: $result"
    }

    fun load() {
        val text: TextDomainModel = getTextUseCase.execute()
        _resultLive.value = text.text
    }

    fun fetchUser() {
        try {
            viewModelScope.launch {
                val userData: NetworkUserDomainModel = fetchUserUseCase.execute()
                _resultLive.value = userData.title
            }
        } catch (e: Exception) {
            _resultLive.value = "Error: ${e.message}"
        }
    }

    fun fetchPost() {
        try {
            viewModelScope.launch {
                val postData = fetchPostUseCase.execute()
                _listLive.value = postData
            }
        } catch (e: Exception) {
            _resultLive.value = "Error: ${e.message}"
        }
    }

    override fun onCleared() {
        Log.wtf("AAA", "VM Cleared")
        super.onCleared()
    }
}
