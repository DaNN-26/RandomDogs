package com.example.dogs.repository

import com.example.dogs.model.ApiConst
import com.example.dogs.model.DogResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RepositoryImpl(private val apiClient: HttpClient) : Repository {
    override suspend fun getDogsPictures(): DogResponse =
        apiClient.get(ApiConst.DOGS_URL).body()
}