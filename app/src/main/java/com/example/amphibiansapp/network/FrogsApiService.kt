package com.example.amphibiansapp.network

import com.example.amphibiansapp.model.FrogPhoto
import retrofit2.http.GET

interface FrogsApiService {
    @GET("amphibians")
    suspend fun getPhotos(): List<FrogPhoto>
}