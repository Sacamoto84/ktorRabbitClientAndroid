package com.example.ktorclient

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorclient.data.remote.dto.PostsService
import com.example.ktorclient.data.remote.dto.Rabbit
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    val service = PostsService.create()

    data class RabbitState(
        val rabbit: Rabbit? = null,
        val isLoading: Boolean = false
    )

    private val _state = mutableStateOf(RabbitState())
    val state: State<RabbitState> = _state

    init {
        getRandomRabbit()
    }

    fun getRandomRabbit() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    rabbit = service.getRandomRabbit(),
                    isLoading = false
                )

            } catch (e: java.lang.Exception) {
                Log.e("MainViewModel", "getRandomRabbit", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }




}