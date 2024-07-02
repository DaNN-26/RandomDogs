package com.example.dogs.model

import kotlinx.serialization.Serializable

@Serializable
data class DogResponse(
    val message: List<String>,
    val status: String
)