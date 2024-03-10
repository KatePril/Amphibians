package com.example.amphibiansapp.ui.screens

import com.example.amphibiansapp.model.FrogPhoto

sealed interface FrogUIState {
    data class Successful(
        val photos: List<FrogPhoto>
    ) : FrogUIState

    object Loading : FrogUIState
    object Error : FrogUIState
}