package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.FrogPhoto
import com.example.amphibiansapp.network.FrogsApiService

interface FrogsPhotosRepository {
    suspend fun getFrogsPhotos(): List<FrogPhoto>
}

class NetworkFrogsPhotosRepository(
    private val frogsApiService: FrogsApiService
) : FrogsPhotosRepository {
    override suspend fun getFrogsPhotos(): List<FrogPhoto> = frogsApiService.getPhotos()
}