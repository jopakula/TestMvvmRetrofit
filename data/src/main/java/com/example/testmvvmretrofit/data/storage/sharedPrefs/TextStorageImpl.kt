package com.example.testmvvmretrofit.data.storage.sharedPrefs

import android.content.Context
import com.example.testmvvmretrofit.data.storage.TextStorage
import com.example.testmvvmretrofit.data.storage.models.TextDataModel

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_TEXT_NAME = "key_first_name"

class TextStorageImpl(context: Context) : TextStorage {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(text: TextDataModel): Boolean {
        sharedPreferences.edit().putString(KEY_TEXT_NAME, text.text).apply()
        return true
    }

    override fun get(): TextDataModel {
        val text = sharedPreferences.getString(KEY_TEXT_NAME, "") ?: ""
        return TextDataModel(text)
    }
}
