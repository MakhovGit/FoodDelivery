package com.example.fooddelivery.data.dto

import com.google.gson.annotations.SerializedName

data class DataModelDTO(
    @field:SerializedName("text")
    val text: String? = null,
    @field:SerializedName("meanings")
    val meanings: List<MeaningsDTO>? = null
)