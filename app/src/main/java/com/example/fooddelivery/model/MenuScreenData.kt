package com.example.fooddelivery.model

data class MenuScreenData(
    val listCities: List<String>,
    val listBanners: List<Int>,
    val listMenu: List<String>,
    val translations: List<WordData>
)