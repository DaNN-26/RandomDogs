package com.example.dogs.repository

import com.example.dogs.model.DogResponse

interface Repository {
    suspend fun getDogsPictures() : DogResponse
}