package com.example.dogs.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor() : ViewModel() {
    private val _dogImages = MutableStateFlow<List<String>>(emptyList())
    val dogImages = _dogImages
    private val repository = AppModule.providesRepository(AppModule.providesApiClient())

    init {
        getDogsPictures()
    }

    private fun getDogsPictures() {
        viewModelScope.launch {
            try {
                _dogImages.value = repository.getDogsPictures().message
            } catch(e: Exception) {
                Log.d("Error", "ОШИБКА")
            }
        }
    }

    fun refreshDogsPictures() {
        getDogsPictures()
    }
}