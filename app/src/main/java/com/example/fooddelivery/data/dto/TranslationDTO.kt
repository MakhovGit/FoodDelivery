package com.example.fooddelivery.data.dto

import com.google.gson.annotations.SerializedName

data class TranslationDTO(
    @field:SerializedName("text")
    val text: String? = null,
    @field:SerializedName("note")
    val note: String? = null
)