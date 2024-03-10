package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.data.FrogsPhotosRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.amphibiansapp.FrogPhotosApplication
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException

class FrogsViewModel(private val frogsPhotosRepository: FrogsPhotosRepository) : ViewModel() {
    var frogUIState: FrogUIState by mutableStateOf(FrogUIState.Loading)
        private set

    init {
        getFrogPhotos()
    }

    fun getFrogPhotos() {
        viewModelScope.launch {
            frogUIState = FrogUIState.Loading
            frogUIState = try {
                FrogUIState.Successful(frogsPhotosRepository.getFrogsPhotos())
            } catch (e: IOException) {
                FrogUIState.Error
            } catch (e: HttpException) {
                FrogUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application  = (this[APPLICATION_KEY] as FrogPhotosApplication)
                val frogsPhotosRepository = application.container.frogsPhotosRepository
                FrogsViewModel(frogsPhotosRepository = frogsPhotosRepository)
            }
        }
    }
}