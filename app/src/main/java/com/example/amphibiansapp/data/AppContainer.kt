package com.example.amphibiansapp.data

import com.example.amphibiansapp.network.FrogsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val frogsPhotosRepository: FrogsPhotosRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: FrogsApiService by lazy {
        retrofit.create(FrogsApiService::class.java)
    }

    override val frogsPhotosRepository: FrogsPhotosRepository by lazy {
        NetworkFrogsPhotosRepository(retrofitService)
    }

}