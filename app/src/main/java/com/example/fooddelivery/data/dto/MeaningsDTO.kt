package com.example.fooddelivery.data.dto

import com.google.gson.annotations.SerializedName

data class MeaningsDTO(
    @field:SerializedName("translation")
    val translationDTO: TranslationDTO? = null,
    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,
)